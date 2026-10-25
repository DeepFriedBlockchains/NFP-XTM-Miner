package com.thecryptosteer.xmrminer.ui

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.thecryptosteer.xmrminer.domain.LogEntry
import com.thecryptosteer.xmrminer.domain.LogSanitizer
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/** A view over the existing bounded native/service event stream. No command input. */
@Composable
fun LogsScreen(logs: List<LogEntry>, clear: () -> Unit) {
    var autoScroll by rememberSaveable { mutableStateOf(true) }
    var query by rememberSaveable { mutableStateOf("") }
    var exportSnapshot by remember { mutableStateOf("") }
    val context = LocalContext.current
    val clipboard = LocalClipboardManager.current
    val scope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    val dateFormat = remember { SimpleDateFormat("HH:mm:ss.SSS", Locale.ROOT) }
    val entries = remember(logs, query) {
        logs.map { it.copy(message = LogSanitizer.sanitize(it.message), level = LogSanitizer.sanitize(it.level)) }
            .filter { query.isBlank() || it.message.contains(query, true) || it.level.contains(query, true) }
    }
    val text = remember(entries) {
        entries.joinToString("\n") { "${Date(it.timeMillis)} [${it.level}] ${it.message}" }
    }
    val exporter = rememberLauncherForActivityResult(ActivityResultContracts.CreateDocument("text/plain")) { uri ->
        val snapshot = exportSnapshot
        exportSnapshot = ""
        if (uri != null) scope.launch {
            val result = withContext(Dispatchers.IO) {
                runCatching {
                    requireNotNull(context.contentResolver.openOutputStream(uri)).bufferedWriter().use { it.write(snapshot) }
                }
            }
            Toast.makeText(context, if (result.isSuccess) "Sanitized logs exported" else "Log export failed", Toast.LENGTH_SHORT).show()
        }
    }
    LaunchedEffect(entries.lastOrNull(), entries.size, autoScroll) {
        if (autoScroll && entries.isNotEmpty()) listState.scrollToItem(entries.lastIndex)
    }
    Column(Modifier.fillMaxSize().background(Color(0xFF100C1C)).padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Mining Terminal / Live Logs", style = MaterialTheme.typography.titleLarge)
        Text("Real engine events · latest 500 entries · sanitized before display and export", style = MaterialTheme.typography.bodySmall)
        SettingSwitchRow("Auto-scroll", autoScroll, { autoScroll = it }, supportingText = "Turn off to inspect earlier events while mining continues.")
        OutlinedTextField(query, { query = it.take(100) }, modifier = Modifier.fillMaxWidth(), label = { Text("Filter events") }, singleLine = true)
        FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            TextButton(onClick = clear, enabled = logs.isNotEmpty()) { Text("Clear display") }
            TextButton(onClick = { clipboard.setText(AnnotatedString(text)) }, enabled = entries.isNotEmpty()) { Text("Copy logs") }
            TextButton(onClick = {
                exportSnapshot = text
                exporter.launch("NFP-XTM-Miner-diagnostics.txt")
            }, enabled = entries.isNotEmpty() && exportSnapshot.isEmpty()) { Text("Export sanitized logs") }
        }
        if (entries.isEmpty()) Text("No matching events. Start mining to see live engine output.", style = MaterialTheme.typography.bodySmall)
        LazyColumn(state = listState, modifier = Modifier.weight(1f).fillMaxWidth().background(Color(0xFF09070F)), contentPadding = PaddingValues(8.dp)) {
            itemsIndexed(entries) { _, entry ->
                val color = when (entry.level.uppercase(Locale.ROOT)) {
                    "ERROR", "CRITICAL" -> Color(0xFFFF718D)
                    "WARN", "WARNING" -> Color(0xFFFFC46B)
                    "SUCCESS" -> Color(0xFF78DFB0)
                    else -> Color(0xFFD2BFFF)
                }
                SelectionContainer {
                    Text("${dateFormat.format(Date(entry.timeMillis))} [${entry.level}] ${entry.message}", color = color, fontFamily = FontFamily.Monospace, style = MaterialTheme.typography.bodySmall, modifier = Modifier.padding(vertical = 3.dp))
                }
            }
        }
    }
}

package com.thecryptosteer.xmrminer.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

// Voluntary support only. These addresses are never passed to the mining configuration.
private const val TARI_SUPPORT_ADDRESS = "128bJEqBH2yCPKdbbkQxXFjCwGjEhg7pGKYqeGR548mxdPrgaSfHoJ6Gw12nMskpRjKR6uneeJBED8gSVxSJ3Pxfw3L"
private const val MONERO_SUPPORT_ADDRESS = "46QiekGmrwa3kEpDDXAVZkFNKzNKxyopSCLD8mgJLYeQ5mQJ3FJJ6dPT1B53h5rHMxTWN15Jf5aojXfGt36cpSgf3CjRKSz"

@Composable
fun AboutDonationCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                "Donate to helps us Update",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
            )
            Text(
                "Optional donations help support app updates and maintenance.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            DonationAddress("Tari (XTM)", "XTM", TARI_SUPPORT_ADDRESS)
            DonationAddress("Monero (XMR)", "XMR", MONERO_SUPPORT_ADDRESS)
        }
    }
}

@Composable
private fun DonationAddress(currency: String, ticker: String, address: String) {
    val clipboard = LocalClipboardManager.current
    var copied by remember(address) { mutableStateOf(false) }
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(currency, style = MaterialTheme.typography.labelLarge)
        SelectionContainer {
            Text(
                address,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.bodySmall.copy(fontFamily = FontFamily.Monospace),
            )
        }
        TextButton(onClick = {
            clipboard.setText(AnnotatedString(address))
            copied = true
        }) {
            Text(if (copied) "$ticker address copied" else "Copy $ticker address")
        }
    }
}

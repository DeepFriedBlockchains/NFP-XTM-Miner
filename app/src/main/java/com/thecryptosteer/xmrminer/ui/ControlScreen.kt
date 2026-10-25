package com.thecryptosteer.xmrminer.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.thecryptosteer.xmrminer.cloud.ControlUiState

@Composable
fun ControlScreen(
    state: ControlUiState,
    onRegister: (String, String) -> Unit,
    onLogin: (String, String) -> Unit,
    onRecover: (String, String, String) -> Unit,
    onLogout: () -> Unit,
    onSelectRig: (String?) -> Unit,
    onApproveDevice: (String, Boolean) -> Unit,
    onRefresh: () -> Unit,
    onEnableBiometric: () -> Unit,
    onSkipBiometric: () -> Unit,
    onUnlockBiometric: () -> Unit,
) {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Cloud control unavailable", style = MaterialTheme.typography.headlineSmall)
        Text("XTM-MINER uses local mining controls. Upstream accounts and remote control are disabled in this fork.")
    }
}

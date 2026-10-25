package com.thecryptosteer.xmrminer.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Memory
import androidx.compose.material.icons.outlined.Thermostat
import androidx.compose.material.icons.outlined.WarningAmber
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.thecryptosteer.xmrminer.BuildConfig

@Composable
fun DisclosureScreen(onAccept: () -> Unit) {
    var confirmed by remember { mutableStateOf(false) }
    MonitoringBackdrop {
        Column(
            Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(horizontal = 14.dp, vertical = 18.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Card(
                modifier = Modifier.fillMaxWidth().monitoringPanel(MaterialTheme.colorScheme.primary),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.32f)),
            ) {
                Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(9.dp)) {
                    MonitoringPanelHeader("Before you mine", subtitle = "Required operating disclosure", accent = MaterialTheme.colorScheme.primary)
                    Surface(color = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f), shape = MaterialTheme.shapes.extraSmall) {
                        Box(Modifier.size(42.dp), contentAlignment = Alignment.Center) { Icon(Icons.Outlined.Memory, null, tint = MaterialTheme.colorScheme.primary) }
                    }
                    Text(
                        "RandomX is sustained high-load computation. Review these operating disclosures before enabling the miner.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
            DisclosureRow(Icons.Outlined.Thermostat, "Heat and battery", "Mining produces heat, consumes substantial power, can trigger Android thermal throttling, and may accelerate battery wear during prolonged maximum-load operation.", StatusTone.WARNING)
            DisclosureRow(Icons.Outlined.WarningAmber, "User control", "Use XTM-MINER only on hardware you own or are authorized to operate. You can pause or stop mining from the app and foreground notification.", StatusTone.ACTION)
            DisclosureRow(Icons.Outlined.CheckCircle, "Your mining rewards", "This fork performs no developer mining. All submitted shares use the Tari wallet you configure. Pool fees and payout rules are set by your chosen pool.", StatusTone.HEALTHY)
            if (BuildConfig.INSTALLATION_TELEMETRY_ENABLED) {
                DisclosureRow(Icons.Outlined.CheckCircle, "Operational telemetry", "After acceptance, the direct release sends a random installation identifier, app/device version, online state, mining state, and hashrate to THE CRYPTO STEER LLC control service. It does not send an advertising ID, contacts, or precise location, and account settings or logs are not sent unless you sign in.", StatusTone.ACTION)
            } else {
                DisclosureRow(Icons.Outlined.CheckCircle, "Local privacy", "Upstream cloud accounts, remote control, installation telemetry and the private APK updater are disabled in this fork.", StatusTone.HEALTHY)
            }
            Surface(
                modifier = Modifier.fillMaxWidth().monitoringPanel(if (confirmed) CryptoSteerThemeTokens.colors.healthy else MaterialTheme.colorScheme.primary),
                color = if (confirmed) CryptoSteerThemeTokens.colors.healthy.copy(alpha = 0.10f) else MaterialTheme.colorScheme.surface,
                shape = MaterialTheme.shapes.medium,
                border = BorderStroke(1.dp, if (confirmed) CryptoSteerThemeTokens.colors.healthy.copy(alpha = 0.42f) else MaterialTheme.colorScheme.outlineVariant),
            ) {
                Row(Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = confirmed, onCheckedChange = { confirmed = it })
                    Text("I understand and accept these operating and privacy disclosures.", Modifier.weight(1f), style = MaterialTheme.typography.bodyMedium)
                }
            }
            PrimaryActionButton(
                label = "Accept and continue",
                icon = Icons.Outlined.CheckCircle,
                modifier = Modifier.fillMaxWidth(),
                enabled = confirmed,
                onClick = onAccept,
            )
        }
    }
}

@Composable
private fun DisclosureRow(icon: ImageVector, title: String, body: String, tone: StatusTone) {
    Surface(
        modifier = Modifier.fillMaxWidth().monitoringPanel(MaterialTheme.colorScheme.primary),
        color = MaterialTheme.colorScheme.surface,
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
    ) {
        Row(Modifier.padding(13.dp), horizontalArrangement = Arrangement.spacedBy(11.dp), verticalAlignment = Alignment.Top) {
            Icon(icon, null, tint = when (tone) {
                StatusTone.WARNING -> CryptoSteerThemeTokens.colors.warning
                StatusTone.HEALTHY -> CryptoSteerThemeTokens.colors.healthy
                else -> MaterialTheme.colorScheme.primary
            })
            Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(title, style = MaterialTheme.typography.titleMedium)
                Text(body, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}

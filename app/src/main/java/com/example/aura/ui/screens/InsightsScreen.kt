package com.example.aura.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aura.ui.components.AuraCard
import com.example.aura.ui.components.AuraIconTile
import com.example.aura.ui.components.AuraPillSelector
import com.example.aura.ui.components.AuraTag
import com.example.aura.ui.theme.AuraPalette

@Composable
fun InsightsScreen() {
    var phase by remember { mutableStateOf("Luteal") }
    var appLock by remember { mutableStateOf(true) }
    Column(Modifier.padding(horizontal = 16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.weight(1f)) {
                Text("HEALTH TELEMETRY", color = AuraPalette.muted, fontSize = 10.sp, letterSpacing = .6.sp)
                Text("Cycle Insights", color = AuraPalette.ink, fontSize = 26.sp, fontWeight = FontWeight.Medium)
            }
            AuraTag("⬡  Offline Encrypted", AuraPalette.sage, AuraPalette.deepSage)
        }
        AuraCard(color = AuraPalette.card, radius = 24.dp, padding = 18.dp) {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text("Historical Variance (6 Months)", color = AuraPalette.muted, fontSize = 11.sp)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("28.2 Days", color = AuraPalette.ink, fontSize = 20.sp, fontWeight = FontWeight.Medium)
                    Text("  avg duration", color = AuraPalette.deepSage, fontSize = 11.sp)
                    Spacer(Modifier.weight(1f))
                    AuraTag("⟳ 96% Regular", AuraPalette.sage, AuraPalette.deepSage)
                }
                CycleVarianceChart()
                AuraCard(color = AuraPalette.white, radius = 14.dp, padding = 12.dp) {
                    Text("●  March: 29 days total · Standard variation (±1 day)", color = AuraPalette.ink, fontSize = 11.sp)
                }
            }
        }
        AuraCard(color = AuraPalette.softCard, radius = 24.dp, padding = 18.dp) {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(Modifier.weight(1f)) {
                        Text("Phase Correlations", color = AuraPalette.muted, fontSize = 11.sp)
                        Text("Recurrent\nSymptoms", color = AuraPalette.ink, fontSize = 19.sp, lineHeight = 25.sp, fontWeight = FontWeight.Medium)
                    }
                    AuraPillSelector(listOf("Luteal", "Menstrual"), phase) { phase = it }
                }
                CorrelationRow("♧", "Mild Pelvic Tension", 75, AuraPalette.primary)
                CorrelationRow("☾", "Sleep Disturbance & Fatigue", if (phase == "Luteal") 42 else 61, AuraPalette.deepLavender)
                CorrelationRow("☼", "Introspective Focus", if (phase == "Luteal") 30 else 48, AuraPalette.deepSage)
            }
        }
        AuraCard(color = Color(0xFFE1F2EA), radius = 24.dp, padding = 18.dp) {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(Modifier.size(48.dp).clip(RoundedCornerShape(16.dp)).background(AuraPalette.deepSage), contentAlignment = Alignment.Center) {
                        Text("▣", color = AuraPalette.white, fontSize = 22.sp)
                    }
                    Column(Modifier.padding(start = 12.dp)) {
                        Text("100% Offline Vault", color = AuraPalette.ink, fontSize = 16.sp, fontWeight = FontWeight.Medium)
                        Text("Auditable GPL-3.0 Architecture", color = AuraPalette.deepSage, fontSize = 10.sp)
                    }
                }
                Text(
                    "Your reproductive biometric parameters remain strictly isolated inside on-device SQLite storage with SQLCipher AES-256 encryption. Zero remote telemetry, zero third-party SDKs, and zero cloud uploads.",
                    color = AuraPalette.deepSage, fontSize = 13.sp, lineHeight = 20.sp
                )
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    AuraTag("⌁  Zero Trackers", AuraPalette.white, AuraPalette.deepSage)
                    AuraTag("◉  Device Root Key", AuraPalette.white, AuraPalette.deepSage)
                }
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Data Sovereignty", color = AuraPalette.ink, fontSize = 19.sp, fontWeight = FontWeight.Medium, modifier = Modifier.weight(1f))
            Text("User Controlled", color = AuraPalette.muted, fontSize = 10.sp)
        }
        AuraCard(color = AuraPalette.white, radius = 22.dp, padding = 14.dp) {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                SovereigntyRow("↓", "Backup & Export Records", "Open Standard formats (.json, .csv)", AuraPalette.paleRose, AuraPalette.primary)
                SovereigntyRow("↑", "Import History Archive", "Seamless transfer from Clue, Flo, or raw CSV", AuraPalette.sage, AuraPalette.deepSage)
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 5.dp)) {
                    AuraIconTile("◎", AuraPalette.lavender, AuraPalette.deepLavender)
                    Column(Modifier.weight(1f).padding(start = 12.dp)) {
                        Text("App Lock & Biometrics", color = AuraPalette.ink, fontSize = 14.sp)
                        Text("Require fingerprint / PIN on launch", color = AuraPalette.muted, fontSize = 10.sp)
                    }
                    Switch(checked = appLock, onCheckedChange = { appLock = it })
                }
                SovereigntyRow("‹›", "Source Code & Audit", "Release v1.2.0-FOSS • Verify SHA-256", AuraPalette.card, AuraPalette.ink)
            }
        }
        AuraCard(color = Color(0xFFE9E7F0), radius = 22.dp, padding = 16.dp) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AuraIconTile("♧", AuraPalette.paleRose, AuraPalette.primary)
                Text(
                    "Notice symptom pattern changes? Your records reflect a calm, consistent 28–29 day rhythm across seasons.",
                    color = AuraPalette.muted, fontSize = 12.sp, lineHeight = 18.sp, modifier = Modifier.padding(start = 12.dp)
                )
            }
        }
    }
}

@Composable
private fun CycleVarianceChart() {
    val values = listOf(28, 29, 27, 28, 28, 29)
    val months = listOf("Oct", "Nov", "Dec", "Jan", "Feb", "Mar")
    Row(
        Modifier.fillMaxWidth().height(170.dp).padding(top = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        values.forEachIndexed { index, value ->
            Column(
                Modifier.weight(1f).fillMaxSize().clickable { },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(value.toString(), color = if (index == values.lastIndex) AuraPalette.primary else AuraPalette.muted, fontSize = 10.sp)
                Spacer(Modifier.height(4.dp))
                Box(
                    Modifier.fillMaxWidth().height((72 + (value - 27) * 8).dp)
                        .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
                        .background(if (index == values.lastIndex) AuraPalette.primary else AuraPalette.paleRose)
                )
                Text(months[index], color = if (index == values.lastIndex) AuraPalette.primary else AuraPalette.muted, fontSize = 10.sp, modifier = Modifier.padding(top = 5.dp))
            }
        }
    }
}

@Composable
private fun CorrelationRow(icon: String, label: String, percentage: Int, color: Color) {
    Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(icon, color = color, fontSize = 16.sp)
            Text(label, color = AuraPalette.ink, fontSize = 12.sp, modifier = Modifier.weight(1f).padding(start = 8.dp))
            Text("$percentage%", color = color, fontSize = 14.sp, fontWeight = FontWeight.Medium)
        }
        Box(Modifier.fillMaxWidth().height(9.dp).clip(CircleShape).background(Color(0xFFE4E1EA))) {
            Box(Modifier.fillMaxWidth(percentage / 100f).height(9.dp).clip(CircleShape).background(color))
        }
    }
}

@Composable
private fun SovereigntyRow(symbol: String, title: String, subtitle: String, color: Color, foreground: Color) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 5.dp)) {
        AuraIconTile(symbol, color, foreground)
        Column(Modifier.weight(1f).padding(start = 12.dp, end = 6.dp)) {
            Text(title, color = AuraPalette.ink, fontSize = 14.sp)
            Text(subtitle, color = AuraPalette.muted, fontSize = 10.sp, lineHeight = 13.sp, maxLines = 2, overflow = TextOverflow.Ellipsis)
        }
        Text("›", color = AuraPalette.muted, fontSize = 24.sp)
    }
}

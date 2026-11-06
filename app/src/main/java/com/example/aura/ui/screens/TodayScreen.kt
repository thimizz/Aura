package com.example.aura.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aura.ui.components.AuraCard
import com.example.aura.ui.components.AuraFlowTags
import com.example.aura.ui.components.AuraTag
import com.example.aura.ui.theme.AuraPalette

@Composable
fun TodayScreen(onLog: () -> Unit) {
    Column(
        Modifier.padding(horizontal = 18.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AuraCard(color = AuraPalette.card, radius = 28.dp, padding = 18.dp) {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("●  ACTIVE WINDOW", color = AuraPalette.deepLavender, fontSize = 12.sp, letterSpacing = 1.sp)
                    Spacer(Modifier.weight(1f))
                    Text("Cycle Length: 28 Days", color = AuraPalette.muted, fontSize = 12.sp)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Day 14", color = AuraPalette.ink, fontSize = 34.sp, fontWeight = FontWeight.Medium)
                    Text(" of 28", color = AuraPalette.muted, fontSize = 14.sp)
                    Spacer(Modifier.weight(1f))
                    AuraTag("◉  Ovulation Peak", AuraPalette.lavender, AuraPalette.ink)
                }
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    AuraTag("✓  Low chance of cramps", AuraPalette.softCard, AuraPalette.deepSage)
                    AuraTag("♡  Peak Estrogen", AuraPalette.softCard, AuraPalette.muted)
                }
            }
        }

        AuraCard(color = AuraPalette.white, radius = 28.dp, padding = 16.dp) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(Modifier.fillMaxWidth().height(335.dp), contentAlignment = Alignment.Center) {
                    CycleRing(Modifier.size(242.dp))
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("● FERTILE WINDOW", color = AuraPalette.deepLavender, fontSize = 12.sp, letterSpacing = .6.sp)
                        Text("14", color = AuraPalette.ink, fontSize = 68.sp, lineHeight = 76.sp, fontWeight = FontWeight.Medium)
                        Text("Period in 14 days", color = AuraPalette.muted, fontSize = 14.sp)
                        Button(
                            onClick = onLog,
                            colors = ButtonDefaults.buttonColors(containerColor = AuraPalette.lavender, contentColor = AuraPalette.ink)
                        ) { Text("⊕  Log for Today", fontSize = 13.sp) }
                    }
                }
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    Legend("Period", AuraPalette.rose)
                    Legend("Follicular", Color(0xFFB7D0C0))
                    Legend("Ovulation", AuraPalette.deepLavender)
                    Legend("Luteal", Color(0xFFE4E1EA))
                }
            }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Metric("♟", "Basal Temp", "36.6°C", "+0.2° surge", AuraPalette.paleRose, AuraPalette.primary, Modifier.weight(1f))
            Metric("◌", "Cervical Fluid", "Egg White", "High Fertile", AuraPalette.lavender, AuraPalette.deepLavender, Modifier.weight(1f))
            Metric("▣", "Next Period", "Nov 24", "In 14 days", AuraPalette.sage, AuraPalette.deepSage, Modifier.weight(1f))
        }

        AuraCard(color = Color(0xFFFCEDEF), radius = 28.dp, padding = 18.dp) {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text("❋  HORMONAL BLUEPRINT", color = AuraPalette.primary, fontSize = 12.sp, letterSpacing = .6.sp)
                Text("Ovulation to Luteal Shift", color = AuraPalette.ink, fontSize = 18.sp, fontWeight = FontWeight.Medium)
                Text(
                    "Estrogen levels reach their peak today before progesterone begins to ascend. You may experience higher sensory acuity, sustained social energy, and elevated basal temperature. Keep cellular hydration high as natural metabolic rate starts to accelerate.",
                    color = AuraPalette.muted, fontSize = 14.sp, lineHeight = 23.sp
                )
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    AuraTag("ϟ  Peak Energy", AuraPalette.white, AuraPalette.muted)
                    AuraTag("▣  +500ml Electrolytes", AuraPalette.white, AuraPalette.muted)
                }
                AuraTag("♧  Sharp Focus", AuraPalette.white, AuraPalette.muted)
            }
        }

        AuraCard(color = AuraPalette.white, radius = 28.dp, padding = 18.dp) {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(Modifier.weight(1f)) {
                        Text("Today's Check-in", color = AuraPalette.ink, fontSize = 18.sp, fontWeight = FontWeight.Medium)
                        Text("Tap to quickly register physical signs", color = AuraPalette.muted, fontSize = 12.sp)
                    }
                    Text("3 Logged", color = AuraPalette.primary, fontSize = 12.sp)
                }
                AuraFlowTags(
                    listOf("✓  High Energy", "✓  Clear Skin", "✓  Mild Ovulation Pain", "+  Cervical Position", "+  Libido Level", "+  Headache"),
                    AuraPalette.paleRose
                )
            }
        }
        Button(
            onClick = onLog,
            modifier = Modifier.align(Alignment.End).padding(bottom = 2.dp),
            shape = RoundedCornerShape(28.dp),
            colors = ButtonDefaults.buttonColors(containerColor = AuraPalette.primary)
        ) {
            Text("＋  Quick Log", color = AuraPalette.white, fontSize = 15.sp, modifier = Modifier.padding(horizontal = 12.dp, vertical = 3.dp))
        }
    }
}

@Composable
private fun CycleRing(modifier: Modifier = Modifier) {
    Canvas(modifier) {
        val stroke = 12.dp.toPx()
        val arcSize = Size(size.width - stroke, size.height - stroke)
        val topLeft = Offset(stroke / 2, stroke / 2)
        drawArc(Color(0xFFE9E7F0), 0f, 360f, false, topLeft, arcSize, style = Stroke(stroke, cap = StrokeCap.Round))
        drawArc(AuraPalette.primary, 270f, 68f, false, topLeft, arcSize, style = Stroke(stroke, cap = StrokeCap.Round))
        drawArc(AuraPalette.sage, 340f, 74f, false, topLeft, arcSize, style = Stroke(stroke, cap = StrokeCap.Round))
        drawArc(Color(0xFF9A8ABF), 62f, 62f, false, topLeft, arcSize, style = Stroke(stroke, cap = StrokeCap.Round))
        val center = Offset(size.width / 2, size.height / 2)
        val marker = Offset(center.x + size.width / 2, center.y)
        drawCircle(AuraPalette.white, radius = 10.dp.toPx(), center = marker)
        drawCircle(AuraPalette.deepLavender, radius = 7.dp.toPx(), center = marker, style = Stroke(2.dp.toPx()))
    }
}

@Composable
private fun Legend(label: String, color: Color) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(5.dp)) {
        Box(Modifier.size(10.dp).clip(CircleShape).background(color))
        Text(label, color = AuraPalette.muted, fontSize = 11.sp)
    }
}

@Composable
private fun Metric(
    icon: String,
    title: String,
    value: String,
    note: String,
    iconColor: Color,
    valueColor: Color,
    modifier: Modifier
) {
    AuraCard(modifier = modifier, color = AuraPalette.white, radius = 20.dp, padding = 12.dp) {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Box(Modifier.size(32.dp).clip(CircleShape).background(iconColor), contentAlignment = Alignment.Center) {
                Text(icon, color = valueColor, fontSize = 16.sp)
            }
            Text(title, color = AuraPalette.muted, fontSize = 11.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(value, color = AuraPalette.ink, fontSize = 16.sp, fontWeight = FontWeight.Medium, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(note, color = valueColor, fontSize = 11.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
        }
    }
}

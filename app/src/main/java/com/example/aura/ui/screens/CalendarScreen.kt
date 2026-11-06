package com.example.aura.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aura.ui.components.AuraCard
import com.example.aura.ui.components.AuraFlowTags
import com.example.aura.ui.components.AuraTag
import com.example.aura.ui.theme.AuraPalette

@Composable
fun CalendarScreen(onLog: () -> Unit) {
    var monthIndex by remember { mutableIntStateOf(0) }
    var mode by remember { mutableStateOf("Month") }
    var selectedDay by remember { mutableIntStateOf(12) }
    val months = listOf("November 2024", "December 2024", "January 2025", "October 2024")
    val shownMonth = months[Math.floorMod(monthIndex, months.size)]

    Column(Modifier.padding(horizontal = 16.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(shownMonth, color = AuraPalette.ink, fontSize = 24.sp, fontWeight = FontWeight.Medium, modifier = Modifier.weight(1f))
            AuraTag("Current", AuraPalette.sage, AuraPalette.deepSage)
            Spacer(Modifier.size(18.dp))
            Text("‹", color = AuraPalette.muted, fontSize = 28.sp, modifier = Modifier.clickable { monthIndex-- }.padding(horizontal = 6.dp))
            Text("›", color = AuraPalette.muted, fontSize = 28.sp, modifier = Modifier.clickable { monthIndex++ }.padding(horizontal = 6.dp))
        }
        Row(
            Modifier.fillMaxWidth().clip(CircleShape).background(AuraPalette.card).padding(4.dp),
            horizontalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            listOf("Month", "List", "Year").forEach { option ->
                Box(
                    Modifier.weight(1f).clip(CircleShape)
                        .background(if (mode == option) AuraPalette.white else Color.Transparent)
                        .clickable { mode = option }.padding(vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(option, color = AuraPalette.ink, fontSize = 12.sp)
                }
            }
        }
        Row(Modifier.horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            AuraTag("●  Period", AuraPalette.paleRose, AuraPalette.primary)
            AuraTag("●  Predicted Flow", AuraPalette.white, AuraPalette.muted)
            AuraTag("●  Fertile Window", AuraPalette.lavender, AuraPalette.deepLavender)
            AuraTag("●  Ovulation", AuraPalette.sage, AuraPalette.deepSage)
        }
        when (mode) {
            "Month" -> MonthGrid(selectedDay) { selectedDay = it }
            "List" -> AuraCard(color = AuraPalette.white, radius = 24.dp, padding = 20.dp) {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    listOf("Nov 1–5 · Period", "Nov 12 · Ovulation Day", "Nov 15–19 · Luteal Phase", "Nov 29 · Predicted Period").forEach {
                        Text(it, color = AuraPalette.ink, fontSize = 15.sp, modifier = Modifier.fillMaxWidth().clickable { selectedDay = 12 }.padding(8.dp))
                    }
                }
            }
            else -> YearOverview()
        }
        AuraCard(color = AuraPalette.card, radius = 26.dp, padding = 18.dp) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(Modifier.size(50.dp).clip(RoundedCornerShape(16.dp)).background(AuraPalette.deepSage), contentAlignment = Alignment.Center) {
                        Text("◉", color = AuraPalette.white, fontSize = 24.sp)
                    }
                    Column(Modifier.padding(start = 12.dp).weight(1f)) {
                        Text("Tue, Nov $selectedDay", color = AuraPalette.ink, fontSize = 18.sp, fontWeight = FontWeight.Medium)
                        AuraTag("Cycle Day 14", AuraPalette.sage, AuraPalette.deepSage)
                        Text("♧  Ovulation Day • Peak Fertility", color = AuraPalette.deepSage, fontSize = 12.sp)
                    }
                    Text("✎", color = AuraPalette.muted, fontSize = 20.sp)
                }
                AuraCard(color = AuraPalette.background, radius = 18.dp, padding = 14.dp) {
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text("⌁  Chance of Conception: High", color = AuraPalette.ink, fontSize = 13.sp)
                        Text("LH surge detected 24h prior. Cervical mucus egg-white consistency.", color = AuraPalette.muted, fontSize = 12.sp, lineHeight = 18.sp)
                    }
                }
                Text("Logged Biomarkers & Sensations", color = AuraPalette.muted, fontSize = 12.sp)
                AuraFlowTags(listOf("●  Mild Cramping (Mittelschmerz)", "●  High Energy", "●  BBT: 36.4°C"), AuraPalette.white)
                Button(
                    onClick = onLog,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(28.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = AuraPalette.primary)
                ) {
                    Text("＋  Log Today's Symptoms", color = AuraPalette.white, fontSize = 13.sp)
                }
            }
        }
        CycleHealthCard()
        AuraCard(color = Color(0xFFE1F2EA), radius = 18.dp, padding = 14.dp) {
            Column {
                Text("⬡  Zero-Cloud Offline Encryption", color = AuraPalette.deepSage, fontSize = 13.sp)
                Text("Cycle forecasts computed locally without trackers.", color = AuraPalette.deepSage.copy(alpha = .8f), fontSize = 11.sp)
            }
        }
    }
}

@Composable
private fun MonthGrid(selectedDay: Int, onSelect: (Int) -> Unit) {
    val days = listOf(
        listOf(27, 28, 29, 30, 31, 1, 2),
        listOf(3, 4, 5, 6, 7, 8, 9),
        listOf(10, 11, 12, 13, 14, 15, 16),
        listOf(17, 18, 19, 20, 21, 22, 23),
        listOf(24, 25, 26, 27, 28, 29, 30),
        listOf(1, 2, 3, 4, 5, 6, 7)
    )
    AuraCard(color = AuraPalette.white, radius = 24.dp, padding = 14.dp) {
        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Row(Modifier.fillMaxWidth()) {
                listOf("S", "M", "T", "W", "T", "F", "S").forEach {
                    Text(it, Modifier.weight(1f), color = AuraPalette.muted, fontSize = 12.sp, textAlign = TextAlign.Center)
                }
            }
            days.forEachIndexed { rowIndex, week ->
                Row(Modifier.fillMaxWidth()) {
                    week.forEach { day ->
                        val outsideMonth = (rowIndex == 0 && day > 20) || (rowIndex == 5 && day < 8)
                        val period = (rowIndex == 0 && day in 1..2) || (rowIndex == 1 && day in 3..5)
                        val fertile = rowIndex == 2 && day in 8..13
                        val selected = day == selectedDay && !outsideMonth
                        val predicted = rowIndex == 4 && day in 29..30
                        val background = when {
                            selected -> AuraPalette.deepSage
                            period -> if (day <= 2) AuraPalette.primary else AuraPalette.rose
                            fertile -> AuraPalette.lavender
                            predicted -> AuraPalette.paleRose
                            else -> Color.Transparent
                        }
                        Box(
                            Modifier.weight(1f).height(44.dp).padding(horizontal = 1.dp)
                                .clip(if (fertile) RoundedCornerShape(0.dp) else CircleShape)
                                .background(background).clickable { onSelect(day) },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                day.toString(),
                                color = when {
                                    selected || period -> AuraPalette.white
                                    outsideMonth -> Color(0xFFB8B5BF)
                                    else -> AuraPalette.ink
                                },
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun YearOverview() {
    AuraCard(color = AuraPalette.white, radius = 24.dp, padding = 20.dp) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("Cycle overview · 2024", color = AuraPalette.ink, fontSize = 18.sp, fontWeight = FontWeight.Medium)
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                listOf("Jun", "Jul", "Aug", "Sep", "Oct", "Nov").forEachIndexed { index, label ->
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(
                            Modifier.width(30.dp).height((54 + (index % 3) * 10).dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(if (index == 5) AuraPalette.deepSage else AuraPalette.rose)
                        )
                        Text(label, color = AuraPalette.muted, fontSize = 10.sp)
                    }
                }
            }
        }
    }
}

@Composable
private fun CycleHealthCard() {
    AuraCard(color = AuraPalette.white, radius = 24.dp, padding = 18.dp) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("▥", color = AuraPalette.primary, fontSize = 20.sp)
                Text("Cycle Health & Consistency", color = AuraPalette.ink, fontSize = 16.sp, modifier = Modifier.weight(1f).padding(start = 10.dp))
                AuraTag("Last 6 Cycles", AuraPalette.sage, AuraPalette.deepSage)
            }
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf("28" to "Cycle Length", "5" to "Period Duration", "±1.2" to "Variation").forEach { (value, title) ->
                    AuraCard(Modifier.weight(1f), AuraPalette.softCard, 16.dp, 12.dp) {
                        Column {
                            Text(title, color = AuraPalette.muted, fontSize = 10.sp)
                            Text(value, color = AuraPalette.ink, fontSize = 19.sp, fontWeight = FontWeight.Medium)
                            Text("Regular", color = AuraPalette.deepSage, fontSize = 10.sp)
                        }
                    }
                }
            }
            Text("Recent Cycle History", color = AuraPalette.muted, fontSize = 11.sp)
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Bottom) {
                listOf("Jun", "Jul", "Aug", "Sep", "Oct", "Nov").forEachIndexed { index, label ->
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(Modifier.width(18.dp).height((32 + (index % 3) * 7).dp).clip(RoundedCornerShape(5.dp)).background(if (index == 5) AuraPalette.deepSage else AuraPalette.rose.copy(alpha = .75f)))
                        Text(label, color = AuraPalette.muted, fontSize = 10.sp)
                    }
                }
            }
        }
    }
}

package com.example.aura.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aura.ui.components.AuraCard
import com.example.aura.ui.components.AuraFlowTags
import com.example.aura.ui.components.AuraPillSelector
import com.example.aura.ui.components.AuraTag
import com.example.aura.ui.theme.AuraPalette

@Composable
fun DailyLogScreen(saved: Boolean, onSave: () -> Unit) {
    var flow by remember { mutableStateOf("Light") }
    var symptoms by remember { mutableStateOf(setOf("Headache", "Bloating")) }
    var mood by remember { mutableStateOf("Calm") }
    var mucus by remember { mutableStateOf("Egg White (Peak)") }
    var vigor by remember { mutableStateOf("Moderate") }
    var sleep by remember { mutableStateOf(7.5f) }
    var reflection by remember { mutableStateOf("") }

    Column(Modifier.padding(horizontal = 16.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Row(Modifier.fillMaxWidth().padding(vertical = 2.dp), verticalAlignment = Alignment.CenterVertically) {
            Text("‹", color = AuraPalette.muted, fontSize = 26.sp)
            Column(Modifier.weight(1f).padding(start = 16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Tuesday, Nov 12", color = AuraPalette.ink, fontSize = 19.sp, fontWeight = FontWeight.Medium)
                    AuraTag("Today", AuraPalette.paleRose, AuraPalette.primary, Modifier.padding(start = 6.dp))
                }
                Text("Day 14 • Ovulatory Phase", color = AuraPalette.muted, fontSize = 12.sp)
            }
            Text("›", color = AuraPalette.muted, fontSize = 26.sp)
        }

        AuraCard(color = AuraPalette.softCard, radius = 18.dp) {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                SectionTitle("♧", "Period & Bleeding", flow, AuraPalette.primary)
                Text("Track menstrual fluid or breakthrough spotting.", color = AuraPalette.muted, fontSize = 13.sp)
                AuraPillSelector(listOf("None", "Spot", "Light", "Med", "Heavy"), flow) { flow = it }
            }
        }
        AuraCard(color = AuraPalette.card, radius = 18.dp) {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                SectionTitle("♡", "Physical Sensations", "Select all that apply", AuraPalette.deepLavender)
                AuraFlowTags(
                    listOf("✣  Cramps (Mild)", "⊙  Headache", "⊙  Bloating", "⁙  Tender Breasts", "♧  Lower Back Pain", "♧  Acne Breakouts", "☻  Nausea", "☾  Heavy Fatigue"),
                    AuraPalette.white,
                    selected = symptoms,
                    onToggle = { item ->
                        val label = item.substringAfter("  ")
                        symptoms = if (label in symptoms) symptoms - label else symptoms + label
                    }
                )
            }
        }
        AuraCard(color = AuraPalette.softCard, radius = 18.dp) {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                SectionTitle("☼", "Emotional State", "Reflective check-in", AuraPalette.deepSage)
                listOf("Calm", "Uplifted", "Energetic", "Anxious", "Restless", "Low Energy").chunked(3).forEach { rowItems ->
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        rowItems.forEach { label ->
                            MoodTile(label, mood == label, Modifier.weight(1f)) { mood = label }
                        }
                    }
                }
            }
        }
        AuraCard(color = AuraPalette.card, radius = 18.dp) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                SectionTitle("⚗", "Cervical Mucus", "Symptothermal marker", AuraPalette.deepLavender)
                Text(
                    "Key biological metric for fertility awareness & cycle phase estimation.",
                    color = AuraPalette.muted, fontSize = 13.sp, lineHeight = 19.sp
                )
                AuraFlowTags(
                    listOf("Dry / None", "Sticky", "Creamy", "‹  Egg White (Peak)", "Watery"),
                    AuraPalette.white,
                    selected = setOf(mucus),
                    onToggle = { mucus = it.substringAfter("  ") }
                )
            }
        }
        AuraCard(color = AuraPalette.softCard, radius = 18.dp) {
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                SectionTitle("☾", "Sleep Duration", String.format(java.util.Locale.ROOT, "%.1f hrs", sleep), AuraPalette.deepSage)
                Slider(value = sleep, onValueChange = { sleep = it }, valueRange = 4f..12f)
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("4 hrs", color = AuraPalette.muted, fontSize = 10.sp)
                    Text("8 hrs (Optimal)", color = AuraPalette.muted, fontSize = 10.sp)
                    Text("12+ hrs", color = AuraPalette.muted, fontSize = 10.sp)
                }
                Spacer(Modifier.height(2.dp))
                SectionTitle("ϟ", "Vigor & Vitality", vigor, AuraPalette.primary)
                AuraPillSelector(listOf("Low", "Moderate", "High"), vigor) { vigor = it }
            }
        }
        AuraCard(color = AuraPalette.softCard, radius = 18.dp) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                SectionTitle("☷", "Personal Reflection", "⬡ Local Vault", AuraPalette.deepSage)
                TextField(
                    value = reflection,
                    onValueChange = { reflection = it },
                    modifier = Modifier.fillMaxWidth().heightIn(min = 90.dp),
                    placeholder = {
                        Text(
                            "Log medications, personal feelings, dietary notes or basal body temp...",
                            color = Color(0xFF9A8887),
                            fontSize = 13.sp
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = AuraPalette.white,
                        unfocusedContainerColor = AuraPalette.white,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(14.dp)
                )
            }
        }
        Button(
            onClick = onSave,
            modifier = Modifier.fillMaxWidth().height(52.dp),
            shape = RoundedCornerShape(28.dp),
            colors = ButtonDefaults.buttonColors(containerColor = AuraPalette.primary)
        ) {
            Text(if (saved) "✓  Daily Entry Saved" else "▣  Save Daily Entry", color = AuraPalette.white, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
        }
        Text(
            if (saved) "Saved locally on your device." else "Zero tracking, zero cloud leaks. Safe in local storage.",
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
            color = AuraPalette.muted,
            fontSize = 11.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun SectionTitle(icon: String, title: String, trailing: String, color: Color) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(icon, color = color, fontSize = 20.sp)
        Text(title, color = AuraPalette.ink, fontSize = 16.sp, modifier = Modifier.weight(1f).padding(start = 6.dp))
        Text(trailing, color = color, fontSize = 11.sp)
    }
}

@Composable
private fun MoodTile(label: String, selected: Boolean, modifier: Modifier, onClick: () -> Unit) {
    Column(
        modifier.height(66.dp).clip(RoundedCornerShape(14.dp))
            .background(if (selected) AuraPalette.sage else AuraPalette.white)
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            when (label) {
                "Calm" -> "☼"
                "Uplifted" -> "☻"
                "Energetic" -> "ϟ"
                "Anxious" -> "≈"
                "Restless" -> "◎"
                else -> "▱"
            },
            color = if (selected) AuraPalette.deepSage else AuraPalette.ink,
            fontSize = 19.sp
        )
        Text(label, color = AuraPalette.ink, fontSize = 11.sp)
    }
}

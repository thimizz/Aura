package com.example.aura.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aura.ui.screens.CalendarScreen
import com.example.aura.ui.screens.DailyLogScreen
import com.example.aura.ui.screens.InsightsScreen
import com.example.aura.ui.screens.TodayScreen
import com.example.aura.ui.theme.AuraPalette

private enum class AppTab(val title: String, val icon: String) {
    Today("Today", "◯"),
    Log("Log", "⊕"),
    Calendar("Calendar", "▦"),
    Insights("Insights", "⬡")
}

@Composable
fun AuraApp() {
    var selectedTab by remember { mutableStateOf(AppTab.Today) }
    var saved by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = AuraPalette.background,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        topBar = { AppHeader(selectedTab) },
        bottomBar = { BottomNavigation(selectedTab) { selectedTab = it } }
    ) { insets ->
        Column(
            modifier = Modifier.fillMaxSize().padding(insets)
                .verticalScroll(rememberScrollState()).padding(bottom = 18.dp)
        ) {
            when (selectedTab) {
                AppTab.Today -> TodayScreen(onLog = { selectedTab = AppTab.Log })
                AppTab.Log -> DailyLogScreen(saved = saved, onSave = { saved = true })
                AppTab.Calendar -> CalendarScreen(onLog = { selectedTab = AppTab.Log })
                AppTab.Insights -> InsightsScreen()
            }
        }
    }
}

@Composable
private fun AppHeader(tab: AppTab) {
    Row(
        modifier = Modifier.fillMaxWidth().background(AuraPalette.background)
            .windowInsetsPadding(WindowInsets.statusBars).height(58.dp)
            .padding(horizontal = 18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            Modifier.size(32.dp).clip(CircleShape).background(AuraPalette.paleRose),
            contentAlignment = Alignment.Center
        ) {
            Text("◔", color = AuraPalette.primary, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }
        Column(Modifier.padding(start = 10.dp).weight(1f)) {
            Text("Aura", color = AuraPalette.ink, fontSize = 17.sp, fontWeight = FontWeight.Medium)
            Text(tab.title, color = AuraPalette.muted, fontSize = 11.sp, lineHeight = 14.sp)
        }
        HeaderAction("▣", AuraPalette.sage.copy(alpha = .55f), AuraPalette.deepSage)
        Spacer(Modifier.size(14.dp))
        HeaderAction("▢", Color.Transparent, AuraPalette.muted)
        Spacer(Modifier.size(12.dp))
        HeaderAction("♙", AuraPalette.primary, AuraPalette.white)
    }
}

@Composable
private fun HeaderAction(symbol: String, background: Color, foreground: Color) {
    Box(
        Modifier.size(36.dp).clip(CircleShape).background(background).clickable { },
        contentAlignment = Alignment.Center
    ) {
        Text(symbol, color = foreground, fontSize = 20.sp)
    }
}

@Composable
private fun BottomNavigation(selected: AppTab, onSelect: (AppTab) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().background(AuraPalette.background.copy(alpha = .98f))
            .navigationBarsPadding().height(64.dp).padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppTab.entries.forEach { tab ->
            val active = tab == selected
            Column(
                modifier = Modifier.weight(1f).height(60.dp)
                    .clip(RoundedCornerShape(18.dp)).clickable { onSelect(tab) }.padding(top = 5.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    Modifier.clip(CircleShape)
                        .background(if (active) AuraPalette.sage else Color.Transparent)
                        .padding(horizontal = 16.dp, vertical = 2.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(tab.icon, color = if (active) AuraPalette.deepSage else AuraPalette.muted, fontSize = 20.sp)
                }
                Text(tab.title, color = AuraPalette.ink, fontSize = 11.sp, lineHeight = 16.sp)
            }
        }
    }
}

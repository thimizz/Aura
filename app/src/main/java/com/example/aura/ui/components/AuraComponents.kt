package com.example.aura.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aura.ui.theme.AuraPalette

@Composable
fun AuraCard(
    modifier: Modifier = Modifier,
    color: Color = AuraPalette.white,
    radius: Dp = 20.dp,
    padding: Dp = 16.dp,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth().clip(RoundedCornerShape(radius))
            .background(color).padding(padding)
    ) {
        content()
    }
}

@Composable
fun AuraTag(
    text: String,
    background: Color,
    foreground: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.clip(CircleShape).background(background)
            .padding(horizontal = 10.dp, vertical = 6.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text,
            color = foreground,
            fontSize = 11.sp,
            lineHeight = 14.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun AuraFlowTags(
    items: List<String>,
    background: Color,
    selected: Set<String> = emptySet(),
    onToggle: ((String) -> Unit)? = null
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items.chunked(2).forEach { rowItems ->
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                rowItems.forEach { item ->
                    val label = item.substringAfter("  ")
                    val isSelected = label in selected
                    AuraTag(
                        text = item,
                        background = if (isSelected) AuraPalette.paleRose else background,
                        foreground = if (isSelected) AuraPalette.primary else AuraPalette.ink,
                        modifier = Modifier.clickable(enabled = onToggle != null) {
                            onToggle?.invoke(item)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun AuraPillSelector(
    items: List<String>,
    selected: String,
    onSelect: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().clip(CircleShape)
            .background(Color(0xFFE9E7F0)).padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items.forEach { item ->
            Box(
                Modifier.weight(1f).clip(CircleShape)
                    .background(if (selected == item) AuraPalette.primary else Color.Transparent)
                    .clickable { onSelect(item) }.padding(vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    item,
                    color = if (selected == item) AuraPalette.white else AuraPalette.muted,
                    fontSize = 11.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun AuraIconTile(symbol: String, background: Color, foreground: Color) {
    Box(
        Modifier.height(42.dp).clip(RoundedCornerShape(13.dp)).background(background)
            .padding(horizontal = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(symbol, color = foreground, fontSize = 18.sp)
    }
}

package com.qudus.tudee.designSystem.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.qudus.tudee.R
import com.qudus.tudee.designSystem.theme.TudeeTheme

enum class PriorityLevel(val displayName: String, val iconResId: Int) {
    LOW("Low", R.drawable.icon_priority_low),
    MEDIUM("Medium", R.drawable.icon_priority_medium),
    HIGH("High", R.drawable.icon_priority_high);
}
@Composable
fun PriorityLevel.getColor(): Color {
    return when (this) {
        PriorityLevel.LOW -> TudeeTheme.color.greenAccent
        PriorityLevel.MEDIUM -> TudeeTheme.color.yellowAccent
        PriorityLevel.HIGH -> TudeeTheme.color.pinkAccent
    }
}
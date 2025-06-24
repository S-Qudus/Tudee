package com.qudus.tudee.designSystem.component

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.qudus.tudee.R
import com.qudus.tudee.designSystem.theme.TudeeTheme

enum class PriorityLevel {
    Low,
    Medium,
    High
}

@DrawableRes
fun getIconForPriority(priority: PriorityLevel): Int = when (priority) {
    PriorityLevel.Low -> R.drawable.icon_priority_low
    PriorityLevel.Medium -> R.drawable.icon_priority_medium
    PriorityLevel.High -> R.drawable.icon_priority_high
}

@Composable
fun getColorForPriority(priority: PriorityLevel): Color = when (priority) {
    PriorityLevel.Low -> TudeeTheme.color.greenAccent
    PriorityLevel.Medium -> TudeeTheme.color.yellowAccent
    PriorityLevel.High -> TudeeTheme.color.pinkAccent
}

@Composable
fun getLabelForPriority(priority: PriorityLevel): String = when (priority) {
    PriorityLevel.Low -> stringResource(R.string.low)
    PriorityLevel.Medium -> stringResource(R.string.medium)
    PriorityLevel.High -> stringResource(R.string.high)
}


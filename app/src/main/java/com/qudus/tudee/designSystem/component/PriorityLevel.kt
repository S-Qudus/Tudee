package com.qudus.tudee.designSystem.component

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.qudus.tudee.R
import com.qudus.tudee.designSystem.theme.TudeeTheme

enum class PriorityLevel(@StringRes val displayNameResId: Int, val iconResId: Int) {
    LOW(R.string.low, R.drawable.icon_priority_low),
    MEDIUM(R.string.medium, R.drawable.icon_priority_medium),
    HIGH(R.string.high, R.drawable.icon_priority_high);
}
@Composable
fun PriorityLevel.getColor(): Color {
    return when (this) {
        PriorityLevel.LOW -> TudeeTheme.color.greenAccent
        PriorityLevel.MEDIUM -> TudeeTheme.color.yellowAccent
        PriorityLevel.HIGH -> TudeeTheme.color.pinkAccent
    }
}
@Composable
fun PriorityLevel.getDisplayName(): String {
    return stringResource(id = this.displayNameResId)
}
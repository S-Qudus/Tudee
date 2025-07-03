package com.qudus.tudee.ui.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.qudus.tudee.R
import com.qudus.tudee.domain.entity.Priority
import com.qudus.tudee.ui.designSystem.theme.Theme

@Composable
fun getLabelForPriority(priority: Priority): String {
    return when (priority) {
        Priority.HIGH -> stringResource(R.string.high)
        Priority.MEDIUM -> stringResource(R.string.medium)
        Priority.LOW -> stringResource(R.string.low)
    }
}

@Composable
fun getIconForPriority(priority: Priority): Painter {
    return when (priority) {
        Priority.HIGH -> painterResource(id = R.drawable.icon_flag)
        Priority.MEDIUM -> painterResource(id = R.drawable.icon_alert)
        Priority.LOW -> painterResource(id = R.drawable.icon_trade_down)
    }
}

@Composable
fun getColorForPriority(priority: Priority): Color {
    return when (priority) {
        Priority.HIGH -> Theme.color.pinkAccent
        Priority.MEDIUM -> Theme.color.yellowAccent
        Priority.LOW -> Theme.color.greenAccent
    }
}
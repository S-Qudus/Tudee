package com.qudus.tudee.ui.screen.addTask.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.qudus.tudee.R
import com.qudus.tudee.ui.designSystem.component.TudeeChip
import com.qudus.tudee.domain.entity.Priority
import com.qudus.tudee.ui.designSystem.theme.Theme

@Composable
fun PriorityChip(
    isActive: Boolean,
    priorityType: Priority,
    onChipClick: (priority: Priority) -> Unit,
    modifier: Modifier = Modifier
) {
    TudeeChip(
        label = getStringByPriorityType(priorityType),
        icon = getPainterByPriorityType(priorityType),
        modifier = modifier,
        isActive = isActive,
        isClickEnabled = true,
        onChipClick = { onChipClick(priorityType) },
        activeBackgroundColor = getColorByPriorityType(priorityType),
        backgroundColor = Theme.color.surface,
    )
}

@Composable
private fun getStringByPriorityType(type: Priority): String {
    return when (type) {
        Priority.HIGH -> stringResource(R.string.high)
        Priority.MEDIUM -> stringResource(R.string.medium)
        Priority.LOW -> stringResource(R.string.low)
    }
}

@Composable
private fun getPainterByPriorityType(type: Priority): Painter {
    return when (type) {
        Priority.HIGH -> painterResource(R.drawable.icon_flag)
        Priority.MEDIUM -> painterResource(R.drawable.icon_alert)
        Priority.LOW -> painterResource(R.drawable.icon_trade_down)
    }
}

@Composable
private fun getColorByPriorityType(type: Priority): Color {
    return when (type) {
        Priority.HIGH -> Theme.color.pinkAccent
        Priority.MEDIUM -> Theme.color.yellowAccent
        Priority.LOW -> Theme.color.greenAccent
    }
}
package com.qudus.tudee.ui.screen.task_details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.qudus.tudee.ui.designSystem.component.priority.PriorityBadge

@Composable
fun TaskStatusAndPrioritySection(
    priorityBackgroundColor: Color,
    priorityIcon: Painter,
    priorityTitle: String,
    statusBackgroundColor: Color,
    statusTextColor: Color,
    statusTitle: String
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TaskStatusBadge(
            modifier = Modifier.height(28.dp),
            backgroundColor = statusBackgroundColor,
            textColor = statusTextColor,
            title = statusTitle
        )
        PriorityBadge(
            modifier = Modifier.height(28.dp),
            backgroundColor = priorityBackgroundColor,
            icon = priorityIcon,
            title = priorityTitle
        )
    }
}
package com.qudus.tudee.ui.screen.task_details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.qudus.tudee.ui.designSystem.component.priority.PriorityBadge
import com.qudus.tudee.ui.state.PriorityUiState
import com.qudus.tudee.ui.state.TaskStatusUiState
import com.qudus.tudee.ui.state.getBackgroundColor
import com.qudus.tudee.ui.state.getTextColor
import com.qudus.tudee.ui.util.extension.toStringResource

@Composable
fun TaskStatusAndPrioritySection(
    taskStatusUiState: TaskStatusUiState, priorityUiState: PriorityUiState
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TaskStatusBadge(
            modifier = Modifier.height(28.dp),
            backgroundColor = taskStatusUiState.getBackgroundColor(),
            textColor = taskStatusUiState.getTextColor(),
            title = taskStatusUiState.status.toStringResource()
        )
        PriorityBadge(
            priority = priorityUiState,
            modifier = Modifier.height(28.dp)
        )
    }
}
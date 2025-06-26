package com.qudus.tudee.ui.screen.task_details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.qudus.tudee.ui.designSystem.component.priority.PriorityBadge
import com.qudus.tudee.ui.screen.task_details.TaskDetailsUiState

@Composable
fun TaskStatusAndPrioritySection(taskDetailsUiState: TaskDetailsUiState) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TaskStatusBadge(
            taskStatusUiState = taskDetailsUiState.taskUiState.taskStatusUiState,
            modifier = Modifier.height(28.dp)
        )
        PriorityBadge(
            priority = taskDetailsUiState.taskUiState.taskPriority,
            modifier = Modifier.height(28.dp)
        )
    }
}
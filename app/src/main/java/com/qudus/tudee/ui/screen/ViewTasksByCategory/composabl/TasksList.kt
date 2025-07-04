package com.qudus.tudee.ui.screen.ViewTasksByCategory.composabl

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.ui.screen.ViewTasksByCategory.ViewTaskScreenState

@Composable
fun TasksList(
    tasks: List<ViewTaskScreenState.TaskUiState>,
    onTaskClick: (Long) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(
            horizontal = Theme.dimension.spacing16,
            vertical = Theme.dimension.spacing8
        )
    ) {
        items(tasks, key = { it.id }) { task ->
            TaskItem(
                task = task,
                onTaskClick = { onTaskClick(task.id) },
                modifier = Modifier.padding(vertical = Theme.dimension.spacing8)
            )
        }
    }
}
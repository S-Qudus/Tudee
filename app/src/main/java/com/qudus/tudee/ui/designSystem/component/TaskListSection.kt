package com.qudus.tudee.ui.designSystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.qudus.tudee.R
//import com.qudus.tudee.designSystem.component.CategoryTask
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.ui.screen.tasksScreen.state.TaskUiState

@Composable
fun TaskListSection(modifier: Modifier = Modifier, tasks: List<TaskUiState>) {

    if (tasks.isEmpty()) {
        NoTasks()
    } else {
        LazyColumn(
            modifier = modifier
                .padding(start = Theme.dimension.spacing16, end = Theme.dimension.spacing16)
                .background(Theme.color.surface),
            verticalArrangement = Arrangement.spacedBy(Theme.dimension.spacing8),
            contentPadding = PaddingValues(Theme.dimension.spacing12, Theme.dimension.spacing12),
        ) {
            items(tasks) { task ->
                CategoryTask(
                    title = task.title,
                    description = task.description ?: "",
                  //  priorityLevel = task.priority,
                    onClick = {},
                    dateText = task.createdAt,
                    taskRes = { modifier ->
                        Icon(
                            painter = painterResource(id = R.drawable.icon_category_book_open),
                            contentDescription = "Task Icon",
                            modifier = modifier,
                            tint = Theme.color.purpleAccent
                        )
                    }
                )
            }
        }
    }
}
package com.qudus.tudee.ui.screen.ViewTasksByCategory.composabl

import com.qudus.tudee.R
import com.qudus.tudee.ui.state.getCategoryIcon


import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.qudus.tudee.designSystem.component.CategoryTask
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.ui.screen.ViewTasksByCategory.ViewTaskScreenState


@Composable
fun TaskItem(
    task: ViewTaskScreenState.TaskUiState,
    onTaskClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val iconRes = task.defaultCategoryType?.let { getCategoryIcon(it) }
        ?: R.drawable.icon_bug

    CategoryTask(
        modifier = modifier,
        title = task.title,
        description = task.description,
        priorityLevel = task.priority,
        onClick = onTaskClick,
        dateText = task.createdAt.toString(),
        taskRes = { modifier ->
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = "Task Icon",
                modifier = modifier,
                tint = Theme.color.purpleAccent
            )
        }
    )
}
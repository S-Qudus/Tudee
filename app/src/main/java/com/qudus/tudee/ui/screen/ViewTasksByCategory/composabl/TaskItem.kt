package com.qudus.tudee.ui.screen.ViewTasksByCategory.composabl

import com.qudus.tudee.R
import com.qudus.tudee.ui.state.getCategoryIcon


import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.qudus.tudee.ui.designSystem.component.CategoryTask
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.ui.mapper.toPriorityUiState
import com.qudus.tudee.ui.screen.ViewTasksByCategory.ViewTaskScreenState
import com.qudus.tudee.ui.state.getColor
import com.qudus.tudee.ui.state.getIcon
import com.qudus.tudee.ui.state.getLabel


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
        priorityIcon =  task.priority.toPriorityUiState().getIcon(),
        priorityLabel =  task.priority.toPriorityUiState().getLabel(),
        priorityBackground =  task.priority.toPriorityUiState().getColor(),
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
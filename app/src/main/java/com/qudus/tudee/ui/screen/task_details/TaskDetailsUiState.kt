package com.qudus.tudee.ui.screen.task_details

import com.qudus.tudee.ui.state.TaskUiState

data class TaskDetailsUiState(
    val taskUiState: TaskUiState = TaskUiState(),
    var isVisible: Boolean = true,
    var isTaskCompleted: Boolean = false,
    var error: Exception? = null,
)
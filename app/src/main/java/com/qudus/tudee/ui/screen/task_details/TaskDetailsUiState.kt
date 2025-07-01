package com.qudus.tudee.ui.screen.task_details

import com.qudus.tudee.domain.exception.TudeeExecption
import com.qudus.tudee.ui.state.CategoryUiState
import com.qudus.tudee.ui.state.TaskUiState

data class TaskDetailsUiState(
    val taskUiState: TaskUiState = TaskUiState(),
    val categoryUiState: CategoryUiState = CategoryUiState(),
    val isVisible: Boolean = true,
    val isTaskCompleted: Boolean = false,
    val isMoveOperationLoading: Boolean = false,
    val exception: TudeeExecption? = null,
)
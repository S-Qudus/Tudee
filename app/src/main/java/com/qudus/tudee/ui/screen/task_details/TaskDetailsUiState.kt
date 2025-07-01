package com.qudus.tudee.ui.screen.task_details

import com.qudus.tudee.domain.exception.TudeeExecption
import com.qudus.tudee.ui.state.CategoryUiState
import com.qudus.tudee.ui.state.TaskUiState

data class TaskDetailsUiState(
    val taskUiState: TaskUiState = TaskUiState(),
    var categoryUiState: CategoryUiState = CategoryUiState(),
    var isVisible: Boolean = true,
    var isTaskCompleted: Boolean = false,
    var isMoveOperationLoading: Boolean = false,
    var exception: TudeeExecption? = null,
)
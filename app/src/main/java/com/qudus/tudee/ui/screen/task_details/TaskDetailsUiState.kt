package com.qudus.tudee.ui.screen.task_details

import com.qudus.tudee.R
import com.qudus.tudee.ui.state.TaskUiState

data class TaskDetailsUiState(
    val taskUiState: TaskUiState = TaskUiState(),
    val editIcon: Int = R.drawable.icon_pencil_edit,
    val editIconDescription: String = "Pencil Edit",
    var moveButtonTitle: Int = R.string.move_to,
    var isVisible: Boolean = true,
    var isTaskCompleted: Boolean = false
)
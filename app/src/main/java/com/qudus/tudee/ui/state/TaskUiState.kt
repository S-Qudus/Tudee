package com.qudus.tudee.ui.state

import androidx.annotation.StringRes
import com.qudus.tudee.R
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

data class TaskUiState(
    val taskId: String = "",
    val taskTitle: String = "",
    val taskDescription: String = "",
    val taskPriority: PriorityUiState = PriorityUiState.MEDIUM,
    val taskCategory: CategoryUiState = CategoryUiState(),
    val taskStatusUiState: TaskStatusUiState = TaskStatusUiState.TODO,
    val taskAssignedDate: LocalDate = Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault()).date,
)
enum class TaskStatusUiState(@StringRes val status:Int){
    TODO(R.string.todo),
    IN_PROGRESS(R.string.in_progress),
    DONE(R.string.done)
}
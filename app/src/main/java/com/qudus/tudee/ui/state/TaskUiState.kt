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
    val taskCategoryId: Long = 0L,
    val taskCategoryTitle: String = "",
    val taskStatusUiState: TaskStatusUiState = TaskStatusUiState.TODO,
    val taskAssignedDate: LocalDate = Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault()).date,
)

data class CategoryUiState(
    val id: Long = 0L,
    val title: String = "",
    val imagePath: String = "",
    val defaultCategoryType: String? = null
)

enum class TaskStatusUiState(@StringRes val status: Int) {
    TODO(R.string.to_do),
    IN_PROGRESS(R.string.in_progress),
    DONE(R.string.done)
}
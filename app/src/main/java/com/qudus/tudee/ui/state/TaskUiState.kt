package com.qudus.tudee.ui.state

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

data class TaskUiState(
    val taskId: Long = 0L,
    val taskTitle: String = "Organize Study Desk",
    val taskDescription: String = "Solve all exercises from page 45 to 50 in the textbook, Solve all exercises from page 45 to 50 in the textbook.",
    val taskPriority: PriorityUiState = PriorityUiState.MEDIUM,
    val taskCategoryId: Long = 0L,
    val taskCategoryTitle: String = "",
    val taskStatusUiState: TaskStatusUiState = TaskStatusUiState.TODO,
    val taskAssignedDate: LocalDate = Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault()).date,
)
package com.qudus.tudee.ui.state

data class TaskUiState(
    val id: Long = 0L,
    val title: String = "",
    val description: String = "",
    val priority: PriorityUiState = PriorityUiState.MEDIUM,
    val categoryId: Long = 0L,
    val taskState: TaskStateUiState = TaskStateUiState.TODO,
    val createdAt: String = "",
)
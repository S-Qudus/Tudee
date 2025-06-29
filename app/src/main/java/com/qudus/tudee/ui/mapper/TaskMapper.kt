package com.qudus.tudee.ui.mapper

import com.qudus.tudee.domain.entity.Task
import com.qudus.tudee.domain.entity.Priority
import com.qudus.tudee.domain.entity.State
import com.qudus.tudee.ui.screen.addTask.AddTaskUiState
import kotlinx.datetime.toLocalDate

fun AddTaskUiState.toTask(taskId: Long = 0): Task {
    val selectedPriority = priorityUiStates.find { it.isSelected }?.type ?: Priority.LOW
    val selectedCategoryId = categoryUiStates.find { it.isSelected }?.id ?: 0L
    val createdAt = date.toLocalDate()

    return Task(
        id = taskId,
        title = title,
        description = description,
        createdAt = createdAt,
        priority = selectedPriority,
        state = State.TODO,
        categoryId = selectedCategoryId
    )
}
package com.qudus.tudee.ui.mapper

import com.qudus.tudee.domain.entity.Priority
import com.qudus.tudee.domain.entity.State
import com.qudus.tudee.domain.entity.Task
import com.qudus.tudee.ui.screen.addTask.AddTaskUiState
import com.qudus.tudee.ui.state.CategoryUiState
import com.qudus.tudee.ui.state.TaskUiState
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

fun Task.toTaskUiState(category: CategoryUiState): TaskUiState {
    return TaskUiState(
        taskId = id,
        taskTitle = title,
        taskDescription = description ?: "",
        taskAssignedDate = createdAt,
        taskPriority = priority.toPriorityUiState(),
        taskStatusUiState = state.toTaskStatusUiState(),
        taskCategory = category
    )
}

package com.qudus.tudee.ui.mapper

import com.qudus.tudee.domain.entity.Task
import com.qudus.tudee.domain.entity.Priority
import com.qudus.tudee.domain.entity.State
import com.qudus.tudee.domain.entity.DefaultCategoryType
import com.qudus.tudee.ui.state.CategoryUiState
import com.qudus.tudee.ui.state.TaskUiState
import com.qudus.tudee.ui.screen.taskEditor.TaskEditorUiState
import com.qudus.tudee.ui.screen.taskEditor.TaskEditorUiState.PriorityItemUiState
import com.qudus.tudee.ui.screen.taskEditor.TitleErrorType
import kotlinx.datetime.toLocalDate

fun TaskEditorUiState.toTask(taskId: Long = 0): Task {
    val selectedPriority = priorityUiStates.find { it.isSelected }?.type ?: Priority.LOW
    val selectedCategory = categoryUiStates.find { it.isSelected }
    val selectedCategoryId = selectedCategory?.id ?: 0L
    val defaultCategoryType = selectedCategory?.defaultCategoryType
    val createdAt = date.toLocalDate()

    return Task(
        id = taskId,
        title = title,
        description = description,
        createdAt = createdAt,
        priority = selectedPriority,
        state = State.TODO,
        categoryId = selectedCategoryId,
        defaultCategoryType = defaultCategoryType
    )
}

fun Task.toTaskUiState(): TaskEditorUiState {

    return TaskEditorUiState(
        title = title,
        description = description ?: "",
        date = createdAt.toString(),
        priorityUiStates = getPriorityUiStatesByPriorityType(priority),
        titleErrorMessageType = null
    )
}

private fun getPriorityUiStatesByPriorityType(priority: Priority): List<PriorityItemUiState> {
    return listOf(
        PriorityItemUiState(type = Priority.HIGH, isSelected = priority == Priority.HIGH),
        PriorityItemUiState(type = Priority.MEDIUM, isSelected = priority == Priority.MEDIUM),
        PriorityItemUiState(type = Priority.LOW, isSelected = priority == Priority.LOW)
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
    )
}

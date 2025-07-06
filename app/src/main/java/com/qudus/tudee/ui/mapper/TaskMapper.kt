package com.qudus.tudee.ui.mapper

import com.qudus.tudee.domain.entity.Priority
import com.qudus.tudee.domain.entity.State
import com.qudus.tudee.domain.entity.Task
import com.qudus.tudee.ui.screen.taskEditor.TaskEditorUiState
import com.qudus.tudee.ui.screen.taskEditor.TaskEditorUiState.PriorityItemUiState
import com.qudus.tudee.ui.state.TaskUiState
import kotlinx.datetime.toLocalDate

fun TaskEditorUiState.toTask(taskId: Long = 0): Task {
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

fun Task.toTaskEditorUiState(): TaskEditorUiState {

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

fun Task.toTaskUiState(): TaskUiState {
    return TaskUiState(
        id = id,
        title = title,
        description = description ?: "",
        priority = priority.toPriorityUiState(),
        categoryId = this.categoryId,
        taskState = state.toTaskStatusUiState(),
        createdAt = createdAt.toString(),// Warning: I think we need to make format date
    )
}

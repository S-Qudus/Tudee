package com.qudus.tudee.data.mapper

import com.qudus.tudee.data.dto.TaskDto
import com.qudus.tudee.domain.entity.Task
import com.qudus.tudee.domain.entity.Priority
import com.qudus.tudee.domain.entity.State

fun Task.toDto(): TaskDto {
    return TaskDto(
        id = id,
        title = title,
        description = description,
        date = createdAt,
        priority = priority.name,
        state = state.name,
        categoryId = categoryId
    )
}

fun TaskDto.toEntity(): Task {
    return Task(
        id = id,
        title = title,
        description = description,
        createdAt = date,
        priority = Priority.valueOf(priority),
        state = State.valueOf(state),
        categoryId = categoryId
    )
}
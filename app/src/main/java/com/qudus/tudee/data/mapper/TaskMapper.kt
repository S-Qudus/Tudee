package com.qudus.tudee.data.mapper

import com.qudus.tudee.data.dto.TaskDto
import com.qudus.tudee.domain.entity.Task

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
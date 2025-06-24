package com.qudus.tudee.domain.entity

import com.qudus.tudee.domain.type.Priority
import com.qudus.tudee.domain.type.State
import kotlinx.datetime.LocalDate

data class Task(
    val id: Long,
    val title: String,
    val description: String?,
    val date: LocalDate,
    val priority: Priority,
    val state: State,
    val categoryId: Long
)
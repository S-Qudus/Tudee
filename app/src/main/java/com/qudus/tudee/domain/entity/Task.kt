package com.qudus.tudee.domain.entity

import kotlinx.datetime.LocalDate

data class Task(
    val id: Long,
    val title: String,
    val description: String?,
    val createdAt: LocalDate,
    val priority: Priority,
    val state: State,
    val categoryId: Long
)





package com.qudus.tudee.data.database.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.LocalDate

@Entity(tableName = "Task")
data class TaskDto(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String,
    val description: String?,
    val date: LocalDate,
    val priority: String,
    val state: String,
    val categoryId: Long
)
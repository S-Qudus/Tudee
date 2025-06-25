package com.qudus.tudee.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Category")
data class CategoryDto(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String,
    val imagePath: String,
    val isDefaultCategory: Boolean = false
)
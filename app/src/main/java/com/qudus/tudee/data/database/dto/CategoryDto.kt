package com.qudus.tudee.data.database.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.qudus.tudee.domain.entity.DefaultCategoryType

@Entity(tableName = "Category")
data class CategoryDto(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String,
    val imagePath: String? ,
    val defaultCategoryType: DefaultCategoryType? = null
)
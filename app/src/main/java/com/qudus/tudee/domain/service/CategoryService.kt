package com.qudus.tudee.domain.service

import com.qudus.tudee.domain.entity.Category
import kotlinx.coroutines.flow.Flow

interface CategoryService {
    suspend fun createCategory(category : Category)
    suspend fun updateCategory(category : Category)
    suspend fun deleteCategory(id: Long)
    fun getCategories(): Flow<List<Category>>
    suspend fun getCategoryById(id: Long): Category
}
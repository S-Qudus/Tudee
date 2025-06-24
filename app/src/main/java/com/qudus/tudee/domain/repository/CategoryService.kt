package com.qudus.tudee.domain.repository

import com.qudus.tudee.domain.entity.Category

interface CategoryService {
    suspend fun createCategory(category : Category)
    suspend fun updateCategory(category : Category)
    suspend fun deleteCategory(id: String)
    suspend fun getCategories(): List<Category>
    suspend fun getCategoryById(id: String): Category
}
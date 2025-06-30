package com.qudus.tudee.data.service

import android.util.Log
import com.qudus.tudee.data.database.dao.CategoryDao
import com.qudus.tudee.data.mapper.toCategory
import com.qudus.tudee.domain.entity.Category
import com.qudus.tudee.domain.service.CategoryService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CategoryServiceImpl(
    private val categoryDao: CategoryDao
): CategoryService {
    override suspend fun createCategory(category: Category) {
        TODO("Not yet implemented")
    }

    override suspend fun updateCategory(category: Category) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCategory(id: Long) {
        TODO("Not yet implemented")
    }

    override fun getCategories(): Flow<List<Category>> {
        return  categoryDao.getCategories().map { it.map { it.toCategory() } }
    }

    override suspend fun getCategoryById(id: Long): Category {
        TODO("Not yet implemented")
    }

}
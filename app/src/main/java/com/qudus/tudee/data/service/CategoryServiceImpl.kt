package com.qudus.tudee.data.service

import com.qudus.tudee.data.database.dao.CategoryDao
import com.qudus.tudee.data.mapper.toCategory
import com.qudus.tudee.data.mapper.toDto
import com.qudus.tudee.data.util.wrapServiceCall
import com.qudus.tudee.data.util.wrapServiceSuspendCall
import com.qudus.tudee.domain.entity.Category
import com.qudus.tudee.domain.service.CategoryService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CategoryServiceImpl(
    private val categoryDao: CategoryDao
) : CategoryService {
    override suspend fun createCategory(category: Category) {
        categoryDao.upsertCategory(category.toDto())
    }

    override suspend fun updateCategory(category: Category) {
        categoryDao.upsertCategory(category.toDto())
    }

    override suspend fun deleteCategory(id: Long) {
        categoryDao.deleteCategoryById(id)
    }

    override fun getCategories(): Flow<List<Category>> {
        return categoryDao.getCategories().map { categories ->
            categories.map { it.toCategory() }
        }
    }

    override suspend fun getCategoryById(id: Long): Category {
        return wrapServiceSuspendCall { categoryDao.getCategoryById(id).toCategory() }
    }

}
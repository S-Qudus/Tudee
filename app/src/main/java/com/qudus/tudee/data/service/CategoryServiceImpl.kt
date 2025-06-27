package com.qudus.tudee.data.service

import com.qudus.tudee.data.database.dao.CategoryDao
import com.qudus.tudee.data.mapper.toDomain
import com.qudus.tudee.data.mapper.toEntity
import com.qudus.tudee.domain.entity.Category
import com.qudus.tudee.domain.service.CategoryService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CategoryServiceImpl(
    private val categoryDao: CategoryDao
) : CategoryService {

    override suspend fun createCategory(category: Category) {
        categoryDao.upsertCategory(category.toEntity())
    }

    override suspend fun updateCategory(category: Category) {
        categoryDao.upsertCategory(category.toEntity())
    }

    override suspend fun deleteCategory(id: Long) {
        categoryDao.deleteCategoryById(id)
    }

    override fun getCategories(): Flow<List<Category>> {
        return categoryDao.getCategories().map { list ->
            list.map { it.toDomain() }
        }
    }

    override suspend fun getCategoryById(id: Long): Category {
        return categoryDao.getCategoryById(id).toDomain()
    }
}
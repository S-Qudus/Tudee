package com.qudus.tudee.data.service

import com.qudus.tudee.data.database.dao.CategoryDao
import com.qudus.tudee.data.mapper.toCategory
import com.qudus.tudee.data.mapper.toDto
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
        return  wrapServiceCall(isCategory = true) {  categoryDao.getCategories().map { it.map { it.toCategory() } }}
    }

    override suspend fun getCategoryById(id: Long): Category {
        return categoryDao.getCategoryById(id).toCategory()
    }

}
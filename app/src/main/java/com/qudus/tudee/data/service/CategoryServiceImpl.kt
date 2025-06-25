package com.qudus.tudee.data.service

import com.qudus.tudee.domain.entity.Category
import com.qudus.tudee.domain.service.CategoryService
import kotlinx.coroutines.flow.Flow

class CategoryServiceImpl: CategoryService {
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
        TODO("Not yet implemented")
    }

    override suspend fun getCategoryById(id: Long): Category {
        TODO("Not yet implemented")
    }

}
package com.qudus.tudee.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.qudus.tudee.data.dto.CategoryDto
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Upsert
    suspend fun upsertCategory(category: CategoryDto)

    @Query("SELECT * FROM Category")
    fun getAllCategories(): Flow<List<CategoryDto>>

    @Query("Select * From Category WHERE id = :id")
    suspend fun getCategoryById(id: Long): CategoryDto

    @Query("DELETE FROM Category WHERE id = :id")
    suspend fun deleteCategoryById(id: Long)
}
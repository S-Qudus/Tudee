package com.qudus.tudee.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.qudus.tudee.data.dto.TaskDto
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Upsert
    suspend fun upsertTask(task: TaskDto)

    @Query("SELECT * FROM Task")
    fun getAllTasks(): Flow<List<TaskDto>>

    @Query("SELECT * FROM Task Where id = :id")
    suspend fun getTasksById(id: Long): TaskDto

    @Query("DELETE FROM Task WHERE id = :id")
    suspend fun deleteTaskById(id: Long)
}
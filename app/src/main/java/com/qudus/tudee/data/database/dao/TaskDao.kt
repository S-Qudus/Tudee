package com.qudus.tudee.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.qudus.tudee.data.database.dto.TaskDto
import kotlinx.coroutines.flow.Flow
import com.qudus.tudee.domain.entity.State

@Dao
interface TaskDao {
    @Upsert
    suspend fun upsertTask(task: TaskDto)

    @Query("SELECT * FROM Task")
    fun getTasks(): Flow<List<TaskDto>>

    @Query("SELECT * FROM Task Where id = :id")
    suspend fun getTaskById(id: Long): TaskDto

    @Query("DELETE FROM Task WHERE id = :id")
    suspend fun deleteTaskById(id: Long)

    @Query("SELECT * FROM Task WHERE date = :date")
    fun getTasksByDate(date: String): Flow<List<TaskDto>>

    @Query("SELECT * FROM Task WHERE state = :state")
    fun getTasksByState(state: State): Flow<List<TaskDto>>
}
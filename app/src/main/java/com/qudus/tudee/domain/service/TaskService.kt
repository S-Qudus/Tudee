package com.qudus.tudee.domain.service

import com.qudus.tudee.domain.entity.Task
import kotlinx.coroutines.flow.Flow
import com.qudus.tudee.domain.entity.State

interface TaskService {
    suspend fun createTask(task: Task)
    suspend fun updateTask(task: Task)
    suspend fun deleteTask(id: Long)
    suspend fun getTasksByCategoryId(id: Long): Flow<List<Task>>
    suspend fun getTaskById(id: Long): Task
    fun getTasksCountByState(state: State): Flow<Int>

}
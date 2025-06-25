package com.qudus.tudee.domain.service

import com.qudus.tudee.domain.entity.Task
import kotlinx.coroutines.flow.Flow
import com.qudus.tudee.domain.entity.State

interface TaskService {
    suspend fun createTake(task: Task)
    suspend fun updateTask(task: Task)
    suspend fun deleteTask(id: Long)
    suspend fun getTasks(id: Long): Flow<List<Task>>
    suspend fun getTasksCountByState(state: State): Flow<Int>
    suspend fun getTaskByID(id: Long): Task
    suspend fun moveToState(taskId: String, newState: State)
}
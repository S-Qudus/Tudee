package com.qudus.tudee.domain.repository

import com.qudus.tudee.domain.entity.Task
import com.qudus.tudee.domain.type.State

interface TaskService {
    suspend fun createTake(task: Task)
    suspend fun updateTask(task: Task)
    suspend fun deleteTask(id: String)
    suspend fun getTasks(id: String): List<Task>
    suspend fun getTasksByState(state: State): List<Task>
    suspend fun getTaskByID(id: String): Task
    suspend fun moveToState(taskId: String, newState: State)
}
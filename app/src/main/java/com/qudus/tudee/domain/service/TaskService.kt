package com.qudus.tudee.domain.service

import com.qudus.tudee.domain.entity.Task
import kotlinx.coroutines.flow.Flow
import com.qudus.tudee.domain.entity.State
import kotlinx.datetime.LocalDate

interface TaskService {
    suspend fun createTake(task: Task)
    suspend fun updateTask(task: Task)
    suspend fun deleteTask(id: Long)
    suspend fun getTasksByCategoryId(id: Long): List<Task>
    suspend fun getTaskById(id: Long): Task
    fun getTasksCountByState(state: State): Flow<Int>

    fun getTasksByDate(date: LocalDate): Flow<List<Task>>

    fun getTasksByState(state: State): Flow<List<Task>>
}
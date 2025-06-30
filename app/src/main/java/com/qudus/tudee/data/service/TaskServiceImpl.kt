package com.qudus.tudee.data.service

import com.qudus.tudee.data.database.dao.TaskDao
import com.qudus.tudee.data.mapper.toDto
import com.qudus.tudee.data.mapper.toEntity
import com.qudus.tudee.domain.entity.State
import com.qudus.tudee.domain.entity.Task
import com.qudus.tudee.domain.exception.*
import com.qudus.tudee.domain.service.TaskService
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class TaskServiceImpl(
    private val taskDao: TaskDao,
    private val validator: InputValidator
): TaskService {
    override suspend fun createTake(task: Task) {
        try {
            validator.validateTitle(task.title)
            taskDao.upsertTask(task.toDto())
        } catch (e: Exception) {
            throw TaskCreationFailedException()
        }
    }

    override suspend fun updateTask(task: Task) {
        try {
            validator.validateTitle(task.title)
            taskDao.upsertTask(task.toDto())
        } catch (e: Exception) {
            throw TaskUpdateFailedException()
        }
    }

    override suspend fun deleteTask(id: Long) {
        try {
            taskDao.deleteTaskById(id)
        } catch (e: Exception) {
            throw TaskDeletionFailedException()
        }
    }

    override suspend fun getTasksByCategoryId(id: Long): List<Task> {
        return try {
            // TODO: Add specific query to TaskDao
            taskDao.getTasks().map { tasks ->
                tasks.filter { it.categoryId == id }.map { it.toEntity() }
            }.first()
        } catch (e: Exception) {
            throw TaskFetchAllFailedException()
        }
    }

    override fun getTasksCountByState(state: State): Flow<Int> {
        return try {
            taskDao.getTasks().map { tasks ->
                tasks.count { it.state == state.name }
            }
        } catch (e: Exception) {
            throw TaskFetchAllFailedException()
        }
    }

    override suspend fun getTaskById(id: Long): Task {
        return try {
            taskDao.getTaskById(id).toEntity()
        } catch (e: Exception) {
            throw TaskNotFoundException()
        }
    }

    // Helper method to get all tasks
    suspend fun getAllTasks(): List<Task> {
        return try {
            taskDao.getTasks().map { tasks ->
                tasks.map { it.toEntity() }
            }.first()
        } catch (e: Exception) {
            throw TaskFetchAllFailedException()
        }
    }
}
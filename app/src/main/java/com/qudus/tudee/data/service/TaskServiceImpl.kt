package com.qudus.tudee.data.service

import com.qudus.tudee.data.database.dao.TaskDao
import com.qudus.tudee.data.mapper.toDto
import com.qudus.tudee.data.mapper.toEntity
import com.qudus.tudee.data.util.wrapServiceSuspendCall
import com.qudus.tudee.domain.entity.State
import com.qudus.tudee.domain.entity.Task
import com.qudus.tudee.domain.service.TaskService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class TaskServiceImpl(
    private val taskDao: TaskDao,
    private val validator: InputValidator
) : TaskService {

    override suspend fun createTask(task: Task) {
        wrapServiceSuspendCall {
            validator.validateTitle(task.title)
            taskDao.upsertTask(task.toDto())
        }
    }

    override suspend fun updateTask(task: Task) {
        wrapServiceSuspendCall {
            validator.validateTitle(task.title)
            taskDao.upsertTask(task.toDto())
        }
    }

    override suspend fun deleteTask(id: Long) {
        taskDao.deleteTaskById(id)
    }

    override suspend fun getAllTasks(): List<Task> {
        return wrapServiceSuspendCall {
            taskDao.getTasks().map { tasks ->
                tasks.map { it.toEntity() }
            }.first()
        }
    }

    override suspend fun getTasksByCategoryId(id: Long): List<Task> {
        return wrapServiceSuspendCall {
            taskDao.getTasks().map { tasks ->
                tasks.filter { it.categoryId == id }.map { it.toEntity() }
            }.first()
        }
    }

    override fun getTasksCountByState(state: State): Flow<Int> {
        return taskDao.getTasks().map { tasks ->
            tasks.count { it.state == state.name }
        }
    }

    override suspend fun getTaskById(id: Long): Task {
        return wrapServiceSuspendCall {
            taskDao.getTaskById(id).toEntity()
        }
    }

    override suspend fun moveToState(taskId: Long, newState: State) {
        val task = taskDao.getTaskById(taskId)
        val newTask = task.copy(state = newState.name)
        taskDao.upsertTask(newTask)
    }

}
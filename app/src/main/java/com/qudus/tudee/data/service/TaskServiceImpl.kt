package com.qudus.tudee.data.service

import com.qudus.tudee.data.database.dao.TaskDao
import com.qudus.tudee.data.mapper.toDto
import com.qudus.tudee.data.util.wrapServiceSuspendCall
import com.qudus.tudee.data.mapper.toTask
import com.qudus.tudee.domain.entity.State
import com.qudus.tudee.domain.entity.Task
import com.qudus.tudee.domain.service.TaskService
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow

class TaskServiceImpl(
    private val taskDao: TaskDao,
    private val validator: InputValidator
): TaskService {
    override suspend fun createTake(task: Task) {
        wrapServiceSuspendCall {
            validator.validateTitle(task.title)
            taskDao.upsertTask(task.toDto())
        }
    }

    override suspend fun updateTask(task: Task) {
        validator.validateTitle(task.title)
        taskDao.upsertTask(task.toDto())
    }

    override suspend fun deleteTask(id: Long) {
        TODO("Not yet implemented")
    }

    override suspend fun getTasksByCategoryId(id: Long): List<Task> {
        TODO("Not yet implemented")
    }

    override fun getTasksCountByState(state: State): Flow<Int> {
        TODO("Not yet implemented")
    }

    override suspend fun getTaskById(id: Long): Task {
        return taskDao.getTaskById(id).toTask()
    }
}
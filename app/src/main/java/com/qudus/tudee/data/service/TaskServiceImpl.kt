package com.qudus.tudee.data.service

import com.qudus.tudee.data.database.dao.TaskDao
import com.qudus.tudee.data.mapper.toDto
import com.qudus.tudee.data.mapper.toTask
import com.qudus.tudee.data.util.wrapServiceCall
import com.qudus.tudee.data.util.wrapServiceSuspendCall
import com.qudus.tudee.domain.entity.State
import com.qudus.tudee.domain.entity.Task
import com.qudus.tudee.domain.service.TaskService
import kotlinx.coroutines.flow.Flow
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
        validator.validateTitle(task.title)
        taskDao.upsertTask(task.toDto())
    }

    override suspend fun deleteTask(id: Long) {
        TODO("Not yet implemented")
    }

    override suspend fun getTasksByCategoryId(id: Long): Flow<List<Task>> {
        return wrapServiceCall {
            taskDao.getTasks()
                .map {
                    it.map { it.toTask() }
                        .filter {
                            it.categoryId == id
                        }
                }
        }
    }

    override fun getTasksCountByState(state: State): Flow<Int> {
        TODO("Not yet implemented")
    }

    override suspend fun getTaskById(id: Long): Task {
        return wrapServiceSuspendCall { taskDao.getTaskById(id).toTask() }
    }
}
package com.qudus.tudee.data.service

import com.qudus.tudee.data.database.dao.TaskDao
import com.qudus.tudee.data.mapper.toTask
import com.qudus.tudee.data.mapper.toDto
import com.qudus.tudee.domain.entity.State
import com.qudus.tudee.domain.entity.Task
import com.qudus.tudee.domain.service.TaskService
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

class TaskServiceImpl(
  private val taskDao: TaskDao,
  private val validator: InputValidator
): TaskService {

    override suspend fun createTake(task: Task) {
        validator.validateTitle(task.title)
        taskDao.upsertTask(task.toDto())
    }

    override suspend fun updateTask(task: Task) {
        TODO("Not yet implemented")
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
        TODO("Not yet implemented")
    }

    override fun getTasksByDate(date: LocalDate): Flow<List<Task>> {
        return taskDao.getTasksByDate(date.toString()).map { dtoList ->
            dtoList.map { it.toTask() }
        }
    }

    override fun getTasksByState(state: State): Flow<List<Task>> {
        return taskDao.getTasksByState(state).map { dtoList ->
            dtoList.map { it.toTask() }
        }
    }
}
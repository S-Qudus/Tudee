package com.qudus.tudee.data.service

import com.qudus.tudee.data.dto.TaskDto
import com.qudus.tudee.domain.entity.State
import com.qudus.tudee.domain.entity.Task
import com.qudus.tudee.domain.service.TaskService
import kotlinx.coroutines.flow.Flow

class TaskServiceImpl(
    private val taskDto: TaskDto
): TaskService {
    override suspend fun createTake(task: Task) {
        TODO("Not yet implemented")
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

    override suspend fun getTaskByID(id: Long): Task {
        TODO("Not yet implemented")
    }
}
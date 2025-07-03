package com.qudus.tudee.ui.state

import com.qudus.tudee.domain.entity.Task
import com.qudus.tudee.domain.entity.State

data class TaskState(
    val activeTasks: List<Task> = emptyList(),
    val upcomingTasks: List<Task> = emptyList(),
    val selectedTask: Task? = null,
    val taskToEdit: Task? = null
) {
    val hasActiveTasks: Boolean = activeTasks.isNotEmpty()
    val hasUpcomingTasks: Boolean = upcomingTasks.isNotEmpty()

    val completedTasksCount: Int = activeTasks.count { it.state == State.DONE}
    val inProgressTasksCount: Int = activeTasks.count { it.state == State.IN_PROGRESS }
    val todoTasksCount: Int = upcomingTasks.size

    val taskCounts: List<Pair<Int, String>> = listOf(
        completedTasksCount to "COMPLETED",
        inProgressTasksCount to "IN_PROGRESS",
        todoTasksCount to "TODO"
    )
} 
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

    val taskCounts: List<Pair<Int, String>> = listOf(
        activeTasks.count { it.state == State.COMPLETED } to "COMPLETED",
        activeTasks.count { it.state == State.IN_PROGRESS } to "IN_PROGRESS",
        upcomingTasks.size to "TODO"
    )
} 
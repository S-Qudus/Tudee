package com.qudus.tudee.ui.state

import com.qudus.tudee.domain.entity.Task
import com.qudus.tudee.domain.entity.State

enum class TudeeUiStatus {
    GOOD,
    OKAY,
    POOR,
    BAD
}


fun getTudeeStatus(tasks: List<Task>): TudeeUiStatus {
    val allTasksDone = tasks.all { it.state == State.DONE }
    val allTasksNotFinished = !tasks.any { it.state == State.DONE }
    val hasSomeProgress = tasks.any { it.state == State.DONE || it.state == State.IN_PROGRESS }
    
    return when {
        tasks.isEmpty() -> TudeeUiStatus.POOR
        allTasksNotFinished -> TudeeUiStatus.BAD
        allTasksDone -> TudeeUiStatus.GOOD
        hasSomeProgress -> TudeeUiStatus.OKAY
        else -> TudeeUiStatus.POOR
    }
}


fun getTudeeStatus(completedTasks: Int, totalTasks: Int): TudeeUiStatus {
    return when {
        totalTasks == 0 -> TudeeUiStatus.POOR
        completedTasks == totalTasks -> TudeeUiStatus.GOOD
        completedTasks >= (totalTasks * 0.7) -> TudeeUiStatus.GOOD
        completedTasks >= (totalTasks * 0.4) -> TudeeUiStatus.OKAY
        completedTasks > 0 -> TudeeUiStatus.POOR
        else -> TudeeUiStatus.BAD
    }
} 
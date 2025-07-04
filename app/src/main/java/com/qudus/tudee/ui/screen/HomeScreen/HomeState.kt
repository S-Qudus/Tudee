package com.qudus.tudee.ui.screen.HomeScreen

import com.qudus.tudee.domain.entity.Task
import com.qudus.tudee.domain.entity.State
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

data class ThemeState(
    val isDarkTheme: Boolean = false
)

data class OverviewState(
    val todayDate: LocalDateTime = Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault()),
    val finishedTaskCount: Int = 0,
    val allTaskCount: Int = 0
) {
    val progressPercentage: Float
        get() = if (allTaskCount > 0) finishedTaskCount.toFloat() / allTaskCount else 0f
    
    val hasTasks: Boolean = allTaskCount > 0
}

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

data class UiVisibilityState(
    val isLoading: Boolean = false,
    val showAddTaskSheet: Boolean = false,
    val showEditTaskSheet: Boolean = false,
    val showTaskDetailsBottomSheet: Boolean = false,
    val errorMessage: String? = null
)

data class HomeUiState(
    val theme: ThemeState = ThemeState(),
    val overview: OverviewState = OverviewState(),
    val tasks: TaskState = TaskState(),
    val ui: UiVisibilityState = UiVisibilityState(errorMessage = null)
) {
    val isDarkTheme: Boolean get() = theme.isDarkTheme
    val isLoading: Boolean get() = ui.isLoading
    val finishedTaskCount: Int get() = overview.finishedTaskCount
    val allTaskCount: Int get() = overview.allTaskCount
    val activeTasks: List<Task> get() = tasks.activeTasks
    val upcomingTasks: List<Task> get() = tasks.upcomingTasks
    val selectedTask: Task? get() = tasks.selectedTask
    val taskToEdit: Task? get() = tasks.taskToEdit
    val showAddTaskSheet: Boolean get() = ui.showAddTaskSheet
    val showEditTaskSheet: Boolean get() = ui.showEditTaskSheet
    val showTaskDetailsBottomSheet: Boolean get() = ui.showTaskDetailsBottomSheet
    
    val hasTasks: Boolean get() = overview.hasTasks
    val hasActiveTasks: Boolean get() = tasks.hasActiveTasks
    val hasUpcomingTasks: Boolean get() = tasks.hasUpcomingTasks
    val progressPercentage: Float get() = overview.progressPercentage
    val taskCounts: List<Pair<Int, String>> get() = tasks.taskCounts
    
    val completedTasksCount: Int get() = tasks.completedTasksCount
    val inProgressTasksCount: Int get() = tasks.inProgressTasksCount
    val todoTasksCount: Int get() = tasks.todoTasksCount
    val todayDate: LocalDateTime get() = overview.todayDate
} 
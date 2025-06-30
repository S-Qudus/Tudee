package com.qudus.tudee.ui.state

import com.qudus.tudee.domain.entity.Task
import kotlinx.datetime.LocalDateTime

data class HomeUiState(
    val theme: ThemeState = ThemeState(),
    val overview: OverviewState = OverviewState(),
    val tasks: TaskState = TaskState(),
    val ui: UiVisibilityState = UiVisibilityState(errorMessage = null)
) {
    // Convenience properties for backward compatibility
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
    
    // Computed properties
    val hasTasks: Boolean get() = overview.hasTasks
    val hasActiveTasks: Boolean get() = tasks.hasActiveTasks
    val hasUpcomingTasks: Boolean get() = tasks.hasUpcomingTasks
    val progressPercentage: Float get() = overview.progressPercentage
    val taskCounts: List<Pair<Int, String>> get() = tasks.taskCounts
    
    // Overview Card specific properties
    val completedTasksCount: Int get() = tasks.completedTasksCount
    val inProgressTasksCount: Int get() = tasks.inProgressTasksCount
    val todoTasksCount: Int get() = tasks.todoTasksCount
    val todayDate: LocalDateTime get() = overview.todayDate
}

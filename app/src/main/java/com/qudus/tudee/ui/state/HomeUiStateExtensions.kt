package com.qudus.tudee.ui.state

import com.qudus.tudee.domain.entity.Task

fun HomeUiState.updateTheme(update: (ThemeState) -> ThemeState): HomeUiState {
    return copy(theme = update(theme))
}

fun HomeUiState.updateOverview(update: (OverviewState) -> OverviewState): HomeUiState {
    return copy(overview = update(overview))
}

fun HomeUiState.updateTasks(update: (TaskState) -> TaskState): HomeUiState {
    return copy(tasks = update(tasks))
}

fun HomeUiState.updateUi(update: (UiVisibilityState) -> UiVisibilityState): HomeUiState {
    return copy(ui = update(ui))
}

// Convenience functions for common operations
fun HomeUiState.setLoading(loading: Boolean): HomeUiState {
    return updateUi { it.copy(isLoading = loading) }
}

fun HomeUiState.setDarkTheme(isDark: Boolean): HomeUiState {
    return updateTheme { it.copy(isDarkTheme = isDark) }
}

fun HomeUiState.setSelectedTask(task: Task?): HomeUiState {
    return updateTasks { it.copy(selectedTask = task) }
}

fun HomeUiState.setTaskToEdit(task: Task?): HomeUiState {
    return updateTasks { it.copy(taskToEdit = task) }
}

fun HomeUiState.showAddTaskSheet(): HomeUiState {
    return updateUi { it.copy(showAddTaskSheet = true) }
}

fun HomeUiState.hideAddTaskSheet(): HomeUiState {
    return updateUi { it.copy(showAddTaskSheet = false) }
}

fun HomeUiState.showEditTaskSheet(): HomeUiState {
    return updateUi { it.copy(showEditTaskSheet = true) }
}

fun HomeUiState.hideEditTaskSheet(): HomeUiState {
    return updateUi { it.copy(showEditTaskSheet = false) }
}

fun HomeUiState.showTaskDetails(): HomeUiState {
    return updateUi { it.copy(showTaskDetailsBottomSheet = true) }
}

fun HomeUiState.hideTaskDetails(): HomeUiState {
    return updateUi { it.copy(showTaskDetailsBottomSheet = false) }
} 
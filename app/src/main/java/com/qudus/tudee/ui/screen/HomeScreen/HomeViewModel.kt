package com.qudus.tudee.ui.screen.HomeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qudus.tudee.domain.entity.Task
import com.qudus.tudee.domain.entity.Priority
import com.qudus.tudee.domain.entity.State
import com.qudus.tudee.domain.service.PreferencesManager
import com.qudus.tudee.ui.state.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate

class HomeViewModel(
    private val preferencesManager: PreferencesManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        loadInitialTasks()
        loadThemePreference()
    }

    private fun loadThemePreference() {
        viewModelScope.launch {
            preferencesManager.isDarkTheme.collect { isDarkTheme ->
                _uiState.update { it.setDarkTheme(isDarkTheme) }
            }
        }
    }

    private fun loadInitialTasks() {
        _uiState.update { it.setLoading(true) }
        
        try {
            val tasks = getSampleTasks()
            val done = tasks.count { it.state == State.COMPLETED }
            val inProgress = tasks.filter { it.state == State.IN_PROGRESS }
            val upcoming = tasks.filter { it.state == State.TODO }

            _uiState.update {
                it.updateOverview { overview ->
                    overview.copy(
                        finishedTaskCount = done,
                        allTaskCount = tasks.size
                    )
                }.updateTasks { taskState ->
                    taskState.copy(
                        activeTasks = inProgress,
                        upcomingTasks = upcoming
                    )
                }.setLoading(false)
            }
        } catch (e: Exception) {
            _uiState.update { it.setLoading(false) }
        }
    }

    fun onAddButtonClicked() {
        _uiState.update { it.showAddTaskSheet() }
    }

    fun onDismissBottomSheet() {
        _uiState.update { it.hideAddTaskSheet() }
    }

    // Theme Management
    fun onThemeToggle() {
        viewModelScope.launch {
            val newTheme = !_uiState.value.isDarkTheme
            preferencesManager.setDarkTheme(newTheme)
        }
    }

    private fun getSampleTasks(): List<Task> {
        return listOf(
            Task(
                id = 1,
                title = "Complete UI Design",
                description = "Work on the UI components",
                createdAt = LocalDate(2024, 6, 1),
                priority = Priority.HIGH,
                state = State.IN_PROGRESS,
                categoryId = 1
            ),
            Task(
                id = 2,
                title = "Code Review",
                description = "Improve code quality",
                createdAt = LocalDate(2024, 6, 2),
                priority = Priority.MEDIUM,
                state = State.TODO,
                categoryId = 1
            ),
            Task(
                id = 3,
                title = "Deploy Application",
                description = "Publish the app to store",
                createdAt = LocalDate(2024, 6, 3),
                priority = Priority.HIGH,
                state = State.COMPLETED,
                categoryId = 1
            )
        )
    }

    fun onTaskClicked(taskId: Long) {
        println("Task clicked with ID: $taskId")
    }
}


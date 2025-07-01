package com.qudus.tudee.ui.screen.HomeScreen

import androidx.lifecycle.viewModelScope
import com.qudus.tudee.domain.entity.Task
import com.qudus.tudee.domain.entity.State
import com.qudus.tudee.domain.service.PreferenceService
import com.qudus.tudee.domain.service.TaskService
import com.qudus.tudee.domain.exception.TudeeExecption
import com.qudus.tudee.ui.base.BaseViewModel
import com.qudus.tudee.ui.state.HomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.toLocalDateTime

class HomeViewModel(
    private val preferenceService: PreferenceService,
    private val taskService: TaskService
) : BaseViewModel<HomeUiState>(HomeUiState()) {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        loadTasks()
        loadThemePreference()
        updateTodayDate()
    }

    private fun loadThemePreference() {
        viewModelScope.launch {
            preferenceService.getDarkTheme().collect { isDarkTheme ->
                _uiState.update { it.copy(theme = it.theme.copy(isDarkTheme = isDarkTheme)) }
            }
        }
    }

    private fun loadTasks() {
        _uiState.update { it.copy(ui = it.ui.copy(isLoading = true)) }
        
        viewModelScope.launch {
            try {
                val allTasks = taskService.getAllTasks()
                updateTaskState(allTasks)
            } catch (e: TudeeExecption) {
                _uiState.update { 
                    it.copy(
                        ui = it.ui.copy(
                            isLoading = false,
                            errorMessage = e.message ?: "Failed to load tasks"
                        )
                    )
                }
            } catch (e: Exception) {
                _uiState.update { 
                    it.copy(
                        ui = it.ui.copy(
                            isLoading = false,
                            errorMessage = "Unexpected error: ${e.message}"
                        )
                    )
                }
            }
        }
    }

    private fun updateTaskState(tasks: List<Task>) {
        val completedCount = tasks.count { it.state == State.COMPLETED }
        val inProgressTasks = tasks.filter { it.state == State.IN_PROGRESS }
        val todoTasks = tasks.filter { it.state == State.TODO }

        _uiState.update {
            it.copy(
                overview = it.overview.copy(
                    finishedTaskCount = completedCount,
                    allTaskCount = tasks.size
                ),
                tasks = it.tasks.copy(
                    activeTasks = inProgressTasks,
                    upcomingTasks = todoTasks
                ),
                ui = it.ui.copy(isLoading = false)
            )
        }
    }

    private fun updateTodayDate() {
        _uiState.update {
            it.copy(
                overview = it.overview.copy(
                    todayDate = kotlinx.datetime.Clock.System.now()
                        .toLocalDateTime(kotlinx.datetime.TimeZone.currentSystemDefault())
                )
            )
        }
    }

    // UI Event Handlers
    fun onAddButtonClicked() {
        _uiState.update { it.copy(ui = it.ui.copy(showAddTaskSheet = true)) }
    }

    fun onDismissBottomSheet() {
        _uiState.update { it.copy(ui = it.ui.copy(showAddTaskSheet = false)) }
    }

    fun onThemeToggle() {
        viewModelScope.launch {
            val newTheme = !_uiState.value.isDarkTheme
            preferenceService.setDarkTheme(newTheme)
            _uiState.update { it.copy(theme = it.theme.copy(isDarkTheme = newTheme)) }
        }
    }

    fun onTaskClicked(taskId: Long) {
        println("Task clicked with ID: $taskId")
        // TODO: Navigate to task details or show task options
    }

    // Navigation to task screens
    fun onNavigateToDoneTasks() {
        // TODO: Navigate to done tasks screen
        println("Navigate to done tasks screen")
    }

    fun onNavigateToInProgressTasks() {
        // TODO: Navigate to in progress tasks screen
        println("Navigate to in progress tasks screen")
    }

    fun onNavigateToTodoTasks() {
        // TODO: Navigate to todo tasks screen
        println("Navigate to todo tasks screen")
    }

    fun getCurrentDate() = _uiState.value.todayDate.date

    fun refreshTasks() {
        loadTasks()
    }
}


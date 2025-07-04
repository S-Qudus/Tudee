package com.qudus.tudee.ui.screen.HomeScreen

import androidx.lifecycle.viewModelScope
import com.qudus.tudee.domain.entity.Task
import com.qudus.tudee.domain.entity.State
import com.qudus.tudee.domain.service.TaskService
import com.qudus.tudee.domain.exception.TudeeExecption
import com.qudus.tudee.ui.base.BaseViewModel
import com.qudus.tudee.ui.navigation.NavViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.toLocalDateTime

class HomeViewModel(
    private val taskService: TaskService,
    private val navViewModel: NavViewModel
) : BaseViewModel<HomeUiState>(HomeUiState()) {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        loadTasks()
        updateTodayDate()
    }

    private fun loadTasks() {
        _uiState.update { it.copy(ui = it.ui.copy(isLoading = true)) }
        
        collectFlow(
            flow = taskService.getAllTasks(),
            onEach = { allTasks ->
                updateTaskState(allTasks)
            },
            onError = { exception ->
                _uiState.update { 
                    it.copy(
                        ui = it.ui.copy(
                            isLoading = false,
                            errorMessage = exception.message ?: "Failed to load tasks"
                        )
                    )
                }
            }
        )
    }

    private fun updateTaskState(tasks: List<Task>) {
        val completedCount = tasks.count { it.state == State.DONE }
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
        navViewModel.toggleTheme()
    }

    fun onTaskClicked(taskId: Long) {
        _uiState.update {
            it.copy(
                ui = it.ui.copy(
                    showEditTaskSheet = true,
                    selectedTaskId = taskId
                )
            )
        }
    }

    fun onDismissEditTaskSheet() {
        _uiState.update {
            it.copy(
                ui = it.ui.copy(
                    showEditTaskSheet = false,
                    selectedTaskId = null
                )
            )
        }
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


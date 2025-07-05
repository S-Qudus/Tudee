package com.qudus.tudee.ui.screen.HomeScreen

import androidx.lifecycle.viewModelScope
import com.qudus.tudee.domain.entity.State
import com.qudus.tudee.domain.entity.Task
import com.qudus.tudee.domain.service.PreferenceService
import com.qudus.tudee.domain.service.TaskService
import com.qudus.tudee.ui.base.BaseViewModel
import com.qudus.tudee.ui.screen.HomeScreen.HomeUiEffect.NavigateBackFromEditTaskWithSuccessState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.toLocalDateTime

class HomeViewModel(
    private val preferenceService: PreferenceService,
    private val taskService: TaskService,
) : BaseViewModel<HomeUiState>(HomeUiState()) {

    init {
        loadInitialData()

        viewModelScope.launch {
            UiEventBus.effect.collectLatest { effect ->
                when (effect) {
                    is HomeUiEffect.NavigateBackWithCancelation -> hideSheets()
                    is HomeUiEffect.NavigateBackFromAddTaskWithSuccessState -> showSnackBar(OperationType.ADD_TASK, effect.isSuccess)
                    is NavigateBackFromEditTaskWithSuccessState -> showSnackBar(OperationType.EDIT_TASK, effect.isSuccess)
                    is HomeUiEffect.NavigateToEditTask -> showEditTaskSheet()
                    is HomeUiEffect.NavigateBakeFromTaskDetail -> hideTaskDetailsSheet()
                    is HomeUiEffect.NavigateToTaskDetails -> {}
                }
            }
        }
    }

    private fun loadInitialData() {
        loadTasks()
        loadThemePreference()
        updateTodayDate()
    }

    private fun loadThemePreference() {
        viewModelScope.launch {
            preferenceService.getDarkTheme().collect { isDarkTheme ->
                _state.update { it.copy(theme = it.theme.copy(isDarkTheme = isDarkTheme)) }
            }
        }
    }

    private fun loadTasks() {
        _state.update { it.copy(ui = it.ui.copy(isLoading = true)) }

        collectFlow(
            flow = taskService.getAllTasks(),
            onEach = { allTasks ->
                updateTaskState(allTasks)
            },
            onError = { exception ->
                _state.update {
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

    private fun hideSheets() {
        _state.update {
            it.copy(ui = it.ui.copy(showAddTaskSheet = false, showEditTaskSheet = false))
        }
    }

    private fun showSnackBar(type: OperationType, isSuccess: Boolean) {
        _state.update {
            it.copy(
                ui = it.ui.copy(
                    showEditTaskSheet = false,
                    showAddTaskSheet = false,
                    snackBarItemUiState = it.ui.snackBarItemUiState.copy(
                        isVisible = true,
                        operationType = type,
                        operationDone = isSuccess
                    )
                )
            )
        }
    }

    private fun showEditTaskSheet() {
        _state.update {
            it.copy(
                ui = it.ui.copy(
                    showEditTaskSheet = true,
                    showTaskDetailsBottomSheet = false
                )
            )
        }
    }

    private fun hideTaskDetailsSheet() {
        _state.update { it.copy(ui = it.ui.copy(showTaskDetailsBottomSheet = false)) }
    }


    private fun updateTaskState(tasks: List<Task>) {
        val completedCount = tasks.count { it.state == State.DONE }
        val inProgressTasks = tasks.filter { it.state == State.IN_PROGRESS }
        val todoTasks = tasks.filter { it.state == State.TODO }

        _state.update {
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
        _state.update {
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
        _state.update { it.copy(ui = it.ui.copy(showAddTaskSheet = true)) }
    }

    fun onThemeToggle() {
        viewModelScope.launch {
            val newTheme = !_state.value.isDarkTheme
            preferenceService.setDarkTheme(newTheme)
            _state.update { it.copy(theme = it.theme.copy(isDarkTheme = newTheme)) }
        }
    }

    fun onTaskClicked(taskId: Long) {
        _state.update { it.copy(ui = it.ui.copy(showTaskDetailsBottomSheet = true)) }

        viewModelScope.launch {
            UiEventBus.emitEffect(HomeUiEffect.NavigateToTaskDetails(taskId))
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

    fun getCurrentDate() = _state.value.todayDate.date

    fun refreshTasks() {
        loadTasks()
    }

    fun onSnackBarDismissed() {
        _state.update {
            it.copy(
                ui = it.ui.copy(
                    snackBarItemUiState = it.ui.snackBarItemUiState.copy(
                        isVisible = false,
                        operationType = null
                    )
                )
            )
        }
    }
}


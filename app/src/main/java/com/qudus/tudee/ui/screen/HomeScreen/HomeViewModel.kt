package com.qudus.tudee.ui.screen.HomeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qudus.tudee.domain.entity.Task
import com.qudus.tudee.domain.entity.Priority
import com.qudus.tudee.domain.entity.State
import com.qudus.tudee.domain.service.PreferencesManager
import com.qudus.tudee.domain.service.TaskService
import com.qudus.tudee.domain.exception.TudeeExecption
import com.qudus.tudee.ui.state.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import kotlinx.datetime.toLocalDateTime

class HomeViewModel(
    private val preferencesManager: PreferencesManager,
    private val taskService: TaskService
) : ViewModel() {

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
            preferencesManager.isDarkTheme.collect { isDarkTheme ->
                _uiState.update { it.setDarkTheme(isDarkTheme) }
            }
        }
    }

    private fun loadTasks() {
        _uiState.update { it.setLoading(true) }
        
        viewModelScope.launch {
            try {
                // Get all tasks from the service
                val allTasks = (taskService as com.qudus.tudee.data.service.TaskServiceImpl).getAllTasks()
                updateTaskState(allTasks)
            } catch (e: TudeeExecption) {
                _uiState.update { 
                    it.setLoading(false).updateUi { ui ->
                        ui.copy(errorMessage = e.message ?: "Failed to load tasks")
                    }
                }
            } catch (e: Exception) {
                _uiState.update { 
                    it.setLoading(false).updateUi { ui ->
                        ui.copy(errorMessage = "Unexpected error: ${e.message}")
                    }
                }
            }
        }
    }

    private fun updateTaskState(tasks: List<Task>) {
        val completedCount = tasks.count { it.state == State.COMPLETED }
        val inProgressTasks = tasks.filter { it.state == State.IN_PROGRESS }
        val todoTasks = tasks.filter { it.state == State.TODO }

        _uiState.update {
            it.updateOverview { overview ->
                overview.copy(
                    finishedTaskCount = completedCount,
                    allTaskCount = tasks.size
                )
            }.updateTasks { taskState ->
                taskState.copy(
                    activeTasks = inProgressTasks,
                    upcomingTasks = todoTasks
                )
            }.setLoading(false)
        }
    }

    private fun updateTodayDate() {
        _uiState.update {
            it.updateOverview { overview ->
                overview.copy(
                    todayDate = kotlinx.datetime.Clock.System.now()
                        .toLocalDateTime(kotlinx.datetime.TimeZone.currentSystemDefault())
                )
            }
        }
    }

    // UI Event Handlers
    fun onAddButtonClicked() {
        _uiState.update { it.showAddTaskSheet() }
    }

    fun onDismissBottomSheet() {
        _uiState.update { it.hideAddTaskSheet() }
    }

    fun onThemeToggle() {
        viewModelScope.launch {
            val newTheme = !_uiState.value.isDarkTheme
            preferencesManager.setDarkTheme(newTheme)
            _uiState.update { it.setDarkTheme(newTheme) }
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

    // Function to format date as string for composables
    fun getFormattedDate(): String {
        val date = _uiState.value.todayDate.date
        val dayOfWeek = when (date.dayOfWeek.name) {
            "MONDAY" -> "الاثنين"
            "TUESDAY" -> "الثلاثاء"
            "WEDNESDAY" -> "الأربعاء"
            "THURSDAY" -> "الخميس"
            "FRIDAY" -> "الجمعة"
            "SATURDAY" -> "السبت"
            "SUNDAY" -> "الأحد"
            else -> date.dayOfWeek.name.take(3)
        }
        
        val month = when (date.month.name) {
            "JANUARY" -> "يناير"
            "FEBRUARY" -> "فبراير"
            "MARCH" -> "مارس"
            "APRIL" -> "أبريل"
            "MAY" -> "مايو"
            "JUNE" -> "يونيو"
            "JULY" -> "يوليو"
            "AUGUST" -> "أغسطس"
            "SEPTEMBER" -> "سبتمبر"
            "OCTOBER" -> "أكتوبر"
            "NOVEMBER" -> "نوفمبر"
            "DECEMBER" -> "ديسمبر"
            else -> date.month.name.take(3)
        }
        
        return "$dayOfWeek، ${date.dayOfMonth} $month ${date.year}"
    }

    // Refresh tasks after adding a new one
    fun refreshTasks() {
        loadTasks()
    }
}


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
import kotlinx.datetime.toLocalDateTime

class HomeViewModel(
    private val preferencesManager: PreferencesManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        loadInitialTasks()
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

    private fun loadInitialTasks() {
        _uiState.update { it.setLoading(true) }
        
        try {
            val tasks = getSampleTasks()
            updateTaskState(tasks)
        } catch (e: Exception) {
            _uiState.update { it.setLoading(false) }
        }
    }

    private fun updateTaskState(tasks: List<Task>) {
        val taskStats = calculateTaskStats(tasks)
        val categorizedTasks = categorizeTasks(tasks)

        _uiState.update {
            it.updateOverview { overview ->
                overview.copy(
                    finishedTaskCount = taskStats.completedCount,
                    allTaskCount = tasks.size
                )
            }.updateTasks { taskState ->
                taskState.copy(
                    activeTasks = categorizedTasks.activeTasks,
                    upcomingTasks = categorizedTasks.upcomingTasks
                )
            }.setLoading(false)
        }
    }

    private fun calculateTaskStats(tasks: List<Task>): TaskStats {
        return TaskStats(
            completedCount = tasks.count { it.state == State.COMPLETED },
            inProgressCount = tasks.count { it.state == State.IN_PROGRESS },
            todoCount = tasks.count { it.state == State.TODO }
        )
    }

    private fun categorizeTasks(tasks: List<Task>): CategorizedTasks {
        return CategorizedTasks(
            activeTasks = tasks.filter { it.state == State.IN_PROGRESS },
            upcomingTasks = tasks.filter { it.state == State.TODO }
        )
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
        return "${date.dayOfWeek.name.take(3)}, ${date.dayOfMonth} ${date.month.name.take(3)} ${date.year}"
    }

    // Sample Data
    private fun getSampleTasks(): List<Task> = listOf(
        Task(
            id = 1,
            title = "Complete UI Design",
            description = "Work on the UI components and improve user experience",
            createdAt = LocalDate(2024, 6, 1),
            priority = Priority.HIGH,
            state = State.IN_PROGRESS,
            categoryId = 1L // Book/Study
        ),
        Task(
            id = 2,
            title = "Code Review",
            description = "Review and improve code quality across the project",
            createdAt = LocalDate(2024, 6, 2),
            priority = Priority.MEDIUM,
            state = State.TODO,
            categoryId = 6L // Development
        ),
        Task(
            id = 3,
            title = "Gym Workout",
            description = "Complete daily workout routine",
            createdAt = LocalDate(2024, 6, 3),
            priority = Priority.HIGH,
            state = State.IN_PROGRESS,
            categoryId = 5L // Health/Fitness
        ),
        Task(
            id = 4,
            title = "Shopping List",
            description = "Buy groceries and household items",
            createdAt = LocalDate(2024, 6, 4),
            priority = Priority.LOW,
            state = State.TODO,
            categoryId = 4L // Shopping
        ),
        Task(
            id = 5,
            title = "Personal Project",
            description = "Work on personal side project",
            createdAt = LocalDate(2024, 6, 5),
            priority = Priority.MEDIUM,
            state = State.TODO,
            categoryId = 3L // Personal
        ),
        Task(
            id = 6,
            title = "Team Meeting",
            description = "Weekly team sync meeting",
            createdAt = LocalDate(2024, 6, 6),
            priority = Priority.HIGH,
            state = State.IN_PROGRESS,
            categoryId = 2L // Work
        ),
        Task(
            id = 7,
            title = "Cook Dinner",
            description = "Prepare healthy dinner for family",
            createdAt = LocalDate(2024, 6, 7),
            priority = Priority.MEDIUM,
            state = State.TODO,
            categoryId = 7L // Food/Cooking
        ),
        Task(
            id = 8,
            title = "Budget Planning",
            description = "Plan monthly budget and expenses",
            createdAt = LocalDate(2024, 6, 8),
            priority = Priority.HIGH,
            state = State.TODO,
            categoryId = 9L // Finance
        ),
        Task(
            id = 9,
            title = "Morning Exercise",
            description = "Complete morning workout routine",
            createdAt = LocalDate(2024, 6, 9),
            priority = Priority.MEDIUM,
            state = State.COMPLETED,
            categoryId = 5L // Health/Fitness
        ),
        Task(
            id = 10,
            title = "Read Documentation",
            description = "Read project documentation",
            createdAt = LocalDate(2024, 6, 10),
            priority = Priority.LOW,
            state = State.COMPLETED,
            categoryId = 1L // Book/Study
        )
    )
}

// Data classes for better organization
private data class TaskStats(
    val completedCount: Int,
    val inProgressCount: Int,
    val todoCount: Int
)

private data class CategorizedTasks(
    val activeTasks: List<Task>,
    val upcomingTasks: List<Task>
)


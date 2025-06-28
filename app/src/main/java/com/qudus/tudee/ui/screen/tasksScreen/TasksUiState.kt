package com.qudus.tudee.ui.screen.tasksScreen

import android.os.Build
import androidx.annotation.RequiresApi
import com.qudus.tudee.domain.entity.Priority
import com.qudus.tudee.domain.entity.Task
import com.qudus.tudee.domain.entity.State
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)

data class TasksUiState(
    val currentMonth: LocalDate = LocalDate.now().withDayOfMonth(1),
    val selectedDate: LocalDate = LocalDate.now(),
    val selectedState: State = State.TODO,
    val tasks: List<TaskUiState> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

data class TaskUiState(
    val id: Long,
    val title: String,
    val description: String?,
    val createdAt: String,
    val priority: PriorityUiState,
    val state: StateUiState,
    val categoryId: Long
)

enum class PriorityUiState(val title: String) {
    HIGH("High"),
    MEDIUM("Medium"),
    LOW("Low")
}

enum class StateUiState(val title: String) {
    TODO("To Do"),
    IN_PROGRESS("In Progress"),
    DONE("Done");
}
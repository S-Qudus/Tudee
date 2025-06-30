package com.qudus.tudee.ui.screen.tasksScreen.state

import android.os.Build
import androidx.annotation.RequiresApi
import com.qudus.tudee.ui.state.PriorityUiState
import com.qudus.tudee.ui.state.StateUiState
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)

data class TasksUiState(
    val currentMonth: LocalDate = LocalDate.now().withDayOfMonth(1),
    val selectedDate: LocalDate = LocalDate.now(),
    val selectedState: StateUiState = StateUiState.TODO,
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
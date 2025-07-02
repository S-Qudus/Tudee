package com.qudus.tudee.ui.screen.tasksScreen.state

import android.os.Build
import androidx.annotation.RequiresApi
import com.qudus.tudee.ui.state.PriorityUiState
import com.qudus.tudee.ui.state.StateUiState
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime


@RequiresApi(Build.VERSION_CODES.O)

data class TasksUiState(
    val currentMonth: LocalDate = firstOfMonth(Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date),
    val selectedDate: LocalDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
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

private fun firstOfMonth(date: LocalDate): LocalDate = LocalDate(date.year, date.month, 1)
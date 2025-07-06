package com.qudus.tudee.ui.screen.tasksScreen.state

import android.os.Build
import androidx.annotation.RequiresApi
import com.qudus.tudee.ui.state.TaskStateUiState
import com.qudus.tudee.ui.state.TaskUiState
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime


@RequiresApi(Build.VERSION_CODES.O)

data class TasksUiState(
    val currentMonth: LocalDate = firstOfMonth(Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date),
    val selectedDate: LocalDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
    val selectedState: TaskStateUiState = TaskStateUiState.TODO,
    val tasks: List<TaskUiState> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

private fun firstOfMonth(date: LocalDate): LocalDate = LocalDate(date.year, date.month, 1)
package com.qudus.tudee.ui.screen.tasksScreen.viewModel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.qudus.tudee.domain.service.TaskService
import com.qudus.tudee.ui.base.BaseViewModel
import com.qudus.tudee.ui.mapper.TasksScreenMapper
import com.qudus.tudee.ui.screen.tasksScreen.state.TasksUiState
import com.qudus.tudee.ui.state.StateUiState
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.datetime.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
class TaskViewModel(private val taskService: TaskService) :
    BaseViewModel<TasksUiState>(TasksUiState()) {

    init {
        collectTasks()
    }

    private fun collectTasks() {
        _state.update { it.copy(isLoading = true, error = null) }

        collectFlow(
            flow = combine(
                state.map { it.selectedDate },
                state.map { it.selectedState }
            ) { date, selectedState -> date to selectedState }
                .flatMapLatest { (date, selectedState) ->
                    taskService.getTasksByDate(date).map { tasks ->
                        tasks
                            .filter { it.state == TasksScreenMapper.mapToDomain(selectedState) }
                            .map { TasksScreenMapper.map(it) }
                    }
                },
            onEach = { taskItems ->
                _state.update { it.copy(isLoading = false, tasks = taskItems, error = null) }

                val current = state.value
                Log.d(
                    "DEBUG",
                    "tasks for ${current.selectedDate} (${current.selectedState}) = ${taskItems.size}"
                )
                Log.d("TaskViewModel", "tasks size = ${taskItems.size} | $taskItems")
            },
            onError = { exception ->
                _state.update { it.copy(isLoading = false, error = exception.message) }
            }
        )
    }

    fun selectDate(date: LocalDate) = _state.update { it.copy(selectedDate = date) }

    fun selectState(state: StateUiState) = _state.update { it.copy(selectedState = state) }

    fun selectMonth(month: LocalDate) =
        _state.update { it.copy(currentMonth = LocalDate(month.year, month.month, 1)) }
}
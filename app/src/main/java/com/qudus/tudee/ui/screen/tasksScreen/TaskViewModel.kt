package com.qudus.tudee.ui.screen.tasksScreen

import androidx.lifecycle.ViewModel
import com.qudus.tudee.domain.entity.Task
import com.qudus.tudee.domain.service.TaskService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.datetime.todayIn
import androidx.lifecycle.viewModelScope
import com.qudus.tudee.domain.entity.State
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import java.time.LocalDate
import java.time.ZoneId

class TaskViewModel(private val taskService: TaskService) : ViewModel() {

    private val _selectedDate =
        MutableStateFlow(LocalDate.now(ZoneId.systemDefault()))
    val selectedDate: StateFlow<LocalDate> = _selectedDate

    private val _selectedState = MutableStateFlow(State.TODO)
    val selectedState: StateFlow<State> = _selectedState

    val tasks: StateFlow<List<Task>> =
        combine(_selectedDate, _selectedState) { d, s -> d to s }
            .flatMapLatest { (date, state) ->
                taskService.getTasksByDate(date)
                    .map { it.filter { t -> t.state == state } }
            }
            .let {
                MutableStateFlow<List<Task>>(emptyList()).also { stateFlow ->
                    viewModelScope.launch {
                        it.collect { list ->
                            stateFlow.value = list
                        }
                    }
                }
            }

    fun selectDate(date: LocalDate) {
        _selectedDate.value = date
    }

    fun selectState(state: State) {
        _selectedState.value = state
    }
}
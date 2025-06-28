package com.qudus.tudee.ui.screen.tasksScreen

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qudus.tudee.domain.entity.State
import com.qudus.tudee.domain.entity.Task
import com.qudus.tudee.domain.service.TaskService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate


@RequiresApi(Build.VERSION_CODES.O)
class TaskViewModel(private val taskService: TaskService) : ViewModel() {

    private val _currentMonth = MutableStateFlow(LocalDate.now().withDayOfMonth(1))
    val selectedMonth: StateFlow<LocalDate> = _currentMonth.asStateFlow()

    private val _selectedDate = MutableStateFlow(LocalDate.now())
    val selectedDate: StateFlow<LocalDate> = _selectedDate.asStateFlow()

    private val _selectedState = MutableStateFlow(State.TODO)
    val selectedState: StateFlow<State> = _selectedState.asStateFlow()

    val tasks: StateFlow<List<Task>> = combine(
        _selectedDate,
        _selectedState
    ) { date, state ->
        Pair(date, state)
    }.flatMapLatest { (date, state) ->
        taskService.getTasksByDate(date)
            .map { list -> list.filter { it.state == state } }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    init {
        viewModelScope.launch {
            tasks.collect { list ->
                Log.d(
                    "DEBUGNedoo", "tasks for ${selectedDate.value} " +
                            "(${selectedState.value}) = ${list.size}"
                )
                Log.d("TaskViewModelNedoo", "tasks size = ${list.size} | $list")
            }
        }
    }

    fun selectDate(date: LocalDate) {
        _selectedDate.value = date
    }

    fun selectState(state: State) {
        _selectedState.value = state
    }

    fun selectMonth(month: LocalDate) {
        _currentMonth.value = month.withDayOfMonth(1)
    }

}
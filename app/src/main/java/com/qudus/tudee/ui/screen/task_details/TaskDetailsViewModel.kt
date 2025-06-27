package com.qudus.tudee.ui.screen.task_details

import android.util.Log
import com.qudus.tudee.ui.base.BaseViewModel
import com.qudus.tudee.ui.state.TaskStatusUiState
import kotlinx.coroutines.flow.update

class TaskDetailsViewModel() : BaseViewModel<TaskDetailsUiState>(TaskDetailsUiState()) {

    init {
        updateTaskCompletionStatus()
    }

    fun onDismiss() {
        _state.update { it.copy(isVisible = false) }
    }

    fun onEditTaskClick() {

    }

    fun onMoveTaskStatusClick() {
        Log.d("test before", _state.value.taskUiState.taskStatusUiState.toString())
        val nextStatus = state.value.taskUiState.taskStatusUiState.getNextState()
        _state.update {
            it.copy(
                taskUiState = it.taskUiState.copy(
                    taskStatusUiState = nextStatus
                )
            )
        }
        updateTaskCompletionStatus()
        Log.d("test after", _state.value.taskUiState.taskStatusUiState.toString())
    }

    private fun updateTaskCompletionStatus() {
        _state.update {
            it.copy(
                isTaskCompleted = it.taskUiState.taskStatusUiState == TaskStatusUiState.DONE
            )
        }
    }
}

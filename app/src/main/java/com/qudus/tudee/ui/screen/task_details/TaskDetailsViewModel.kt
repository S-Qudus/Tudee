package com.qudus.tudee.ui.screen.task_details

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
        val nextStatus = state.value.taskUiState.taskStatusUiState.getNextState()
        _state.update {
            it.copy(
                taskUiState = it.taskUiState.copy(
                    taskStatusUiState = nextStatus
                )
            )
        }
        updateTaskCompletionStatus()
    }

    private fun updateTaskCompletionStatus() {
        _state.update {
            it.copy(
                isTaskCompleted = it.taskUiState.taskStatusUiState == TaskStatusUiState.DONE
            )
        }
    }
}

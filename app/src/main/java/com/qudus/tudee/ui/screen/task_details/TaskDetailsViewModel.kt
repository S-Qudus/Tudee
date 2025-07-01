package com.qudus.tudee.ui.screen.task_details

import com.qudus.tudee.domain.exception.TudeeExecption
import com.qudus.tudee.domain.service.TaskService
import com.qudus.tudee.ui.base.BaseViewModel
import com.qudus.tudee.ui.mapper.toState
import com.qudus.tudee.ui.state.TaskStatusUiState
import kotlinx.coroutines.flow.update

class TaskDetailsViewModel(
    private val taskService: TaskService,
) : BaseViewModel<TaskDetailsUiState>(TaskDetailsUiState()) {

    init {
        updateTaskCompletionStatus()
    }

    fun onDismiss() {
        _state.update { it.copy(isVisible = false) }
    }

    fun onEditTaskClick() {
        // TODO: navigate to edit task
    }

    fun onMoveTaskStatusClick() {
        tryToExecute(
            action = {
                val nextState = state.value.taskUiState.taskStatusUiState.getNextState()
                taskService.moveToState(
                    taskId = _state.value.taskUiState.taskId,
                    newState = nextState.toState()
                )
                nextState
            },
            onSuccess = ::updateTaskStatusUiState,
            onError = ::onMoveStateError
        )

    }

    private fun updateTaskStatusUiState(nextState: TaskStatusUiState) {
        _state.update {
            it.copy(
                taskUiState = it.taskUiState.copy(
                    taskStatusUiState = nextState
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

    private fun onMoveStateError(exception: TudeeExecption) {
        _state.update { it.copy(error = exception) }
        // TODO: dismiss and send exception
        onDismiss()
    }
}

package com.qudus.tudee.ui.screen.task_details

import com.qudus.tudee.domain.entity.Category
import com.qudus.tudee.domain.entity.Task
import com.qudus.tudee.domain.exception.TudeeExecption
import com.qudus.tudee.domain.service.CategoryService
import com.qudus.tudee.domain.service.TaskService
import com.qudus.tudee.ui.base.BaseViewModel
import com.qudus.tudee.ui.mapper.toCategoryUiState
import com.qudus.tudee.ui.mapper.toState
import com.qudus.tudee.ui.mapper.toTaskUiState
import com.qudus.tudee.ui.state.TaskStatusUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.update
import org.koin.core.component.KoinComponent

class TaskDetailsViewModel(
    private val taskService: TaskService,
    private val categoryService: CategoryService,
) : BaseViewModel<TaskDetailsUiState>(TaskDetailsUiState()), KoinComponent {

    //todo: receive task id that i need to get Task
    val taskId: Long = 3

    init {
        fetchTaskDetails()
    }

    private fun fetchTaskDetails() {
        tryToExecute(
            action = { taskService.getTaskById(taskId) },
            onSuccess = ::onGetTaskSuccess,
            onError = ::onGetTaskError,
        )
    }

    private fun onGetTaskSuccess(task: Task) {
        fetchCategoryById(task.categoryId)
        _state.update { it.copy(taskUiState = task.toTaskUiState(it.categoryUiState)) }
        updateTaskCompletionStatus()
    }

    private fun onGetTaskError(exception: TudeeExecption) {
        _state.update { it.copy(exception = exception) }
    }

    private fun fetchCategoryById(id: Long) {
        tryToExecute(
            action = { categoryService.getCategoryById(id) },
            onSuccess = ::onGetCategorySuccess,
            onError = ::onGetCategoryError
        )
    }

    private fun onGetCategorySuccess(category: Category) {
        _state.update { it.copy(categoryUiState = category.toCategoryUiState()) }
    }

    private fun onGetCategoryError(exception: TudeeExecption) {
        _state.update { it.copy(exception = exception) }
    }

    fun onDismiss() {
        _state.update { it.copy(isVisible = false) }
    }

    fun onEditTaskClick() {
        // TODO: navigate to edit task
    }

    fun onMoveTaskStatusClick() {
        setLoadingState(true)
        tryToExecute(
            action = {
                delay(600)
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

    private fun setLoadingState(isLoading: Boolean) {
        _state.update { it.copy(isMoveOperationLoading = isLoading) }
    }

    private fun updateTaskStatusUiState(nextState: TaskStatusUiState) {
        setLoadingState(false)
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
        setLoadingState(false)
        _state.update { it.copy(exception = exception) }
        // TODO: dismiss and send exception
        onDismiss()
    }
}

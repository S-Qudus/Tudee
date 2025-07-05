package com.qudus.tudee.ui.screen.editTask

import com.qudus.tudee.domain.exception.TaskNotFoundException
import com.qudus.tudee.domain.exception.TudeeExecption
import com.qudus.tudee.domain.service.CategoryService
import com.qudus.tudee.domain.service.TaskService
import com.qudus.tudee.ui.mapper.toCategoryItemUiState
import com.qudus.tudee.ui.mapper.toTask
import com.qudus.tudee.ui.mapper.toTaskUiState
import com.qudus.tudee.ui.screen.taskEditor.DataErrorType
import com.qudus.tudee.ui.screen.taskEditor.TaskEditorUiState
import com.qudus.tudee.ui.screen.taskEditor.TaskEditorViewModel
import com.qudus.tudee.ui.screen.taskEditor.CategoryErrorType
import kotlinx.coroutines.flow.update

class EditTaskViewModel(
    private val categoryService: CategoryService,
    private val taskService: TaskService
) : TaskEditorViewModel(), EditTaskInteraction {

    //todo: receive the real task id that i need to edit
    val id = 2L

    init {
        tryToExecute(
            action = { taskService.getTaskById(id) },
            onSuccess = { oldTask ->
                getExistingCategories(oldTask.categoryId)
                updateUiStateWithOldState(oldTask.toTaskUiState())
            },
            onError = ::onGetCurrentTaskError,
        )
    }

    private fun onGetCurrentTaskError(exception: TudeeExecption) {
        if (exception is TaskNotFoundException)
            _state.update { it.copy(dataErrorMessageType = DataErrorType.NOT_FOUND) }
        else
            _state.update { it.copy(dataErrorMessageType = DataErrorType.GENERAL) }
    }

    override fun onGetCategoriesError(exception: TudeeExecption) {
        _state.update { it.copy(categoryErrorMessageType = CategoryErrorType.FAILED_IN_FETCH) }
    }

    private fun getExistingCategories(taskCategoryId: Long) {
        collectFlow(
            flow = categoryService.getCategories(),
            onEach = { categories ->
                _state.update {
                    it.copy(
                        categoryUiStates = categories.map {
                            it.toCategoryItemUiState(categoryId = taskCategoryId)
                        }
                    )
                }
            },
            onError = ::onGetCategoriesError,
        )
    }

    private fun updateUiStateWithOldState(oldTaskUiState: TaskEditorUiState) {
        _state.update {
            it.copy(
                title = oldTaskUiState.title,
                description = oldTaskUiState.description,
                date = oldTaskUiState.date,
                priorityUiStates = oldTaskUiState.priorityUiStates,
                categoryUiStates = oldTaskUiState.categoryUiStates,
            )
        }
    }

    override fun onEditTaskClicked() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            action = { taskService.updateTask(state.value.toTask(taskId = id)) },
            onSuccess = ::onEditTaskSuccess,
            onError = ::onSubmitTaskError,
        )
    }

    private fun onEditTaskSuccess(unit: Unit) {
        _state.update { it.copy(isLoading = false) }

        //todo: move to the prev screen with success params true
    }
}
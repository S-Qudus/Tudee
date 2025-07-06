package com.qudus.tudee.ui.screen.editTask

import androidx.lifecycle.viewModelScope
import com.qudus.tudee.domain.exception.TaskNotFoundException
import com.qudus.tudee.domain.exception.TaskUpsertFailedException
import com.qudus.tudee.domain.exception.TudeeExecption
import com.qudus.tudee.domain.service.CategoryService
import com.qudus.tudee.domain.service.TaskService
import com.qudus.tudee.ui.mapper.toCategoryItemUiState
import com.qudus.tudee.ui.mapper.toTask
import com.qudus.tudee.ui.mapper.toTaskEditorUiState
import com.qudus.tudee.ui.screen.HomeScreen.HomeUiEffect
import com.qudus.tudee.ui.screen.HomeScreen.UiEventBus
import com.qudus.tudee.ui.screen.taskEditor.CategoryErrorType
import com.qudus.tudee.ui.screen.taskEditor.DataErrorType
import com.qudus.tudee.ui.screen.taskEditor.TaskEditorUiState
import com.qudus.tudee.ui.screen.taskEditor.TaskEditorViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EditTaskViewModel(
    private val categoryService: CategoryService,
    private val taskService: TaskService
) : TaskEditorViewModel(), EditTaskInteraction {

    init {
        viewModelScope.launch {
            UiEventBus.effect.collectLatest { effect ->
                if (effect is HomeUiEffect.NavigateToEditTask){
                    _state.update { it.copy(id = effect.taskId) }
                    tryToExecute(
                        action = { taskService.getTaskById(effect.taskId) },
                        onSuccess = { oldTask ->
                            getExistingCategories(oldTask.categoryId)
                            updateUiStateWithOldState(oldTask.toTaskEditorUiState())
                        },
                        onError = ::onGetCurrentTaskError,
                    )
                }
            }
        }

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
            action = { taskService.updateTask(state.value.toTask(taskId = _state.value.id)) },
            onSuccess = ::onEditTaskSuccess,
            onError = ::onEditTaskError,
        )
    }

    private fun onEditTaskSuccess(unit: Unit) {
        _state.update { it.copy(isLoading = false) }
        viewModelScope.launch {
            UiEventBus.emitEffect(HomeUiEffect.NavigateBackFromEditTaskWithSuccessState(true))
        }
    }

    private fun onEditTaskError(exception: TudeeExecption) {
        if (exception is TaskUpsertFailedException){
            viewModelScope.launch {
                UiEventBus.emitEffect(HomeUiEffect.NavigateBackFromEditTaskWithSuccessState(false))
            }
        }else{
            super.onSubmitTaskError(exception)
        }
    }
}
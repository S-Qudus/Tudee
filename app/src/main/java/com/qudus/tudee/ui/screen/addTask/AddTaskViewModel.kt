package com.qudus.tudee.ui.screen.addTask

import androidx.lifecycle.viewModelScope
import com.qudus.tudee.domain.entity.Category
import com.qudus.tudee.domain.exception.TaskUpsertFailedException
import com.qudus.tudee.domain.exception.TudeeExecption
import com.qudus.tudee.domain.service.CategoryService
import com.qudus.tudee.domain.service.TaskService
import com.qudus.tudee.ui.mapper.toCategoryItemUiState
import com.qudus.tudee.ui.mapper.toTask
import com.qudus.tudee.ui.screen.HomeScreen.HomeUiEffect
import com.qudus.tudee.ui.screen.HomeScreen.UiEventBus
import com.qudus.tudee.ui.screen.addTask.state.AddTaskInteraction
import com.qudus.tudee.ui.screen.taskEditor.CategoryErrorType
import com.qudus.tudee.ui.screen.taskEditor.TaskEditorViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddTaskViewModel(
    categoryService: CategoryService,
    private val taskService: TaskService,
) : TaskEditorViewModel(), AddTaskInteraction {

    init {
        collectFlow(
            flow = categoryService.getCategories(),
            onEach = ::updateCategoriesUiState,
            onError = ::onGetCategoriesError,
        )
    }

    private fun updateCategoriesUiState(categories: List<Category>) {
        _state.update { it.copy(categoryUiStates = categories.map { it.toCategoryItemUiState() }) }
    }

    override fun onAddTaskClicked() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            action = { taskService.createTask(state.value.toTask()) },
            onSuccess = ::onAddTaskSuccess,
            onError = ::onAddTaskError,
        )
    }

    private fun onAddTaskSuccess(unit: Unit) {
        viewModelScope.launch {
            UiEventBus.emitEffect(HomeUiEffect.NavigateBackFromAddTaskWithSuccessState(true))
            resetUiState()
        }

    }

    private fun onAddTaskError(exception: TudeeExecption) {
        if (exception is TaskUpsertFailedException){
            viewModelScope.launch {
                UiEventBus.emitEffect(HomeUiEffect.NavigateBackFromAddTaskWithSuccessState(false))
                resetUiState()
            }
        }else{
            super.onSubmitTaskError(exception)
        }
    }

    override fun onGetCategoriesError(exception: TudeeExecption) {
        _state.update { it.copy(categoryErrorMessageType = CategoryErrorType.FAILED_IN_FETCH) }
    }
}
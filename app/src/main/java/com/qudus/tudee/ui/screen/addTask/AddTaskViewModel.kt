package com.qudus.tudee.ui.screen.addTask

import com.qudus.tudee.domain.entity.Category
import com.qudus.tudee.domain.exception.TudeeExecption
import com.qudus.tudee.domain.exception.EmptyInputException
import com.qudus.tudee.domain.exception.InvalidStartCharacterException
import com.qudus.tudee.domain.exception.InputTooLongException
import com.qudus.tudee.domain.exception.InputTooShortException
import com.qudus.tudee.domain.service.CategoryService
import com.qudus.tudee.domain.service.TaskService
import com.qudus.tudee.ui.mapper.toCategoryItemUiState
import com.qudus.tudee.ui.mapper.toTask
import com.qudus.tudee.ui.screen.taskEditor.TaskEditorViewModel
import com.qudus.tudee.ui.screen.addTask.state.AddTaskInteraction
import com.qudus.tudee.ui.screen.taskEditor.TitleErrorType
import com.qudus.tudee.ui.screen.taskEditor.CategoryErrorType
import kotlinx.coroutines.flow.update

class AddTaskViewModel(
    categoryService: CategoryService,
    private val taskService: TaskService,
    private val onDismiss: () -> Unit = {},
    private val onTaskAdded: () -> Unit = {}
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
        _state.update { it.copy(isLoading = false) }
        onTaskAdded()
        onDismiss()
    }

    private fun onAddTaskError(exception: TudeeExecption) {
        val errorType = when (exception) {
            is EmptyInputException -> TitleErrorType.EMPTY
            is InvalidStartCharacterException -> TitleErrorType.INVALID_START
            is InputTooLongException -> TitleErrorType.TOO_LONG
            is InputTooShortException -> TitleErrorType.TOO_SHORT
            else -> TitleErrorType.INVALID
        }
        _state.update { it.copy(isLoading = false, titleErrorMessageType = errorType) }
    }

    override fun onGetCategoriesError(exception: TudeeExecption) {
        _state.update { it.copy(categoryErrorMessageType = CategoryErrorType.FAILED_IN_FETCH) }
    }

    override fun onCancelAddTask() {
        onDismiss()
    }
}
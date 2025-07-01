package com.qudus.tudee.ui.screen.addTask

import com.qudus.tudee.domain.entity.Category
import com.qudus.tudee.domain.service.CategoryService
import com.qudus.tudee.domain.service.TaskService
import com.qudus.tudee.ui.mapper.toCategoryItemUiState
import com.qudus.tudee.ui.mapper.toTask
import com.qudus.tudee.ui.screen.taskEditor.TaskEditorViewModel
import kotlinx.coroutines.flow.update

class AddTaskViewModel(
    categoryService: CategoryService,
    private val taskService: TaskService,
    private val onDismiss: () -> Unit = {},
    private val onTaskAdded: () -> Unit = {}
) : BaseViewModel<AddTaskUiState>(AddTaskUiState()), AddTaskInteraction {
    private val taskService: TaskService
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
            onError = ::onSubmitTaskError,
        )
    }

    private fun onAddTaskSuccess(unit: Unit) {
        _state.update { it.copy(isLoading = false, isSheetOpen = false) }
        //todo: move to the prev screen with success params true
        _state.update { it.copy(isLoading = false) }
        onTaskAdded()
        onDismiss()
    }

    private fun onAddTaskError(exception: TudeeExecption) {
        val errorType = when (exception) {
            is EmptyInputException -> AddTaskUiState.TitleErrorType.EMPTY
            is InvalidStartCharacterException -> AddTaskUiState.TitleErrorType.INVALID_START
            is InputTooLongException -> AddTaskUiState.TitleErrorType.TOO_LONG
            is InputTooShortException -> AddTaskUiState.TitleErrorType.TOO_SHORT
            else -> AddTaskUiState.TitleErrorType.INVALID
        }
        _state.update { it.copy(isLoading = false, titleErrorMessageType = errorType) }
    }

    override fun onCancelAddTask() {
        onDismiss()
    }
}
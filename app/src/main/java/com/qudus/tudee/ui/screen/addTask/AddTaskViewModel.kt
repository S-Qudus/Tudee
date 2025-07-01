package com.qudus.tudee.ui.screen.addTask

import com.qudus.tudee.domain.entity.Category
import com.qudus.tudee.domain.entity.Priority
import com.qudus.tudee.domain.exception.CategoryFetchAllFailedException
import com.qudus.tudee.domain.exception.EmptyInputException
import com.qudus.tudee.domain.exception.InputTooLongException
import com.qudus.tudee.domain.exception.InputTooShortException
import com.qudus.tudee.domain.exception.InvalidStartCharacterException
import com.qudus.tudee.domain.exception.TudeeExecption
import com.qudus.tudee.domain.service.CategoryService
import com.qudus.tudee.domain.service.TaskService
import com.qudus.tudee.ui.base.BaseViewModel
import com.qudus.tudee.ui.mapper.toCategoryItemUiState
import com.qudus.tudee.ui.mapper.toTask
import com.qudus.tudee.ui.screen.addTask.AddTaskUiState.CategoryItemUiState.Companion.isSameSelection
import com.qudus.tudee.ui.screen.addTask.AddTaskUiState.CategoryItemUiState.Companion.selectById
import com.qudus.tudee.ui.screen.addTask.AddTaskUiState.PriorityItemUiState.Companion.isSameSelection
import com.qudus.tudee.ui.screen.addTask.AddTaskUiState.PriorityItemUiState.Companion.selectByPriorityType
import kotlinx.coroutines.flow.update
import kotlinx.datetime.LocalDate

class AddTaskViewModel(
    categoryService: CategoryService,
    private val taskService: TaskService,
    private val onDismiss: () -> Unit = {},
    private val onTaskAdded: () -> Unit = {}
) : BaseViewModel<AddTaskUiState>(AddTaskUiState()), AddTaskInteraction {

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

    private fun onGetCategoriesError(exception: TudeeExecption) {
        val categoryErrorType = when (exception) {
            is CategoryFetchAllFailedException -> AddTaskUiState.CategoryErrorType.FAILED_IN_FETCH
            else -> AddTaskUiState.CategoryErrorType.NOT_FOUND
        }

        _state.update { it.copy(categoryErrorMessageType = categoryErrorType) }
    }

    override fun onCategoryTypeSelectChange(id: Long) {
        if (_state.value.categoryUiStates.isSameSelection(id)) return
        updateFormState { it.copy(categoryUiStates = it.categoryUiStates.selectById(id)) }
    }

    override fun onTitleValueChange(newTitle: String) {
        updateFormState { it.copy(title = newTitle, titleErrorMessageType = null) }
    }

    override fun onDescriptionValueChange(newDescription: String) {
        updateFormState { it.copy(description = newDescription) }
    }

    override fun onDateFieldClick() = _state.update { it.copy(isDatePickerOpen = true) }

    override fun onDateSelected(selectedDate: LocalDate) {
        _state.update { it.copy(date = selectedDate.toString(), isDatePickerOpen = false) }
    }

    override fun onDatePickCancel() = _state.update { it.copy(isDatePickerOpen = false) }

    override fun onPrioritySelectChange(newPriority: Priority) {
        if (_state.value.priorityUiStates.isSameSelection(newPriority)) return
        updateFormState {
            it.copy(priorityUiStates = it.priorityUiStates.selectByPriorityType(newPriority))
        }
    }

    private inline fun updateFormState(transform: (AddTaskUiState) -> AddTaskUiState) {
        _state.update { oldState ->
            val newState = transform(oldState)
            val isTitleValid = newState.title.isNotBlank()
            val isCategorySelected = newState.categoryUiStates.any { it.isSelected }
            val isPrioritySelected = newState.priorityUiStates.any { it.isSelected }
            val isAddButtonEnabled = isTitleValid && isCategorySelected && isPrioritySelected

            newState.copy(isAddButtonEnabled = isAddButtonEnabled)
        }
    }

    override fun onAddTaskClicked() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            action = { taskService.createTake(state.value.toTask()) },
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
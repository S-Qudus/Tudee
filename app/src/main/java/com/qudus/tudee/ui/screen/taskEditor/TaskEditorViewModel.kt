package com.qudus.tudee.ui.screen.taskEditor

import androidx.lifecycle.viewModelScope
import com.qudus.tudee.domain.entity.Priority
import com.qudus.tudee.domain.exception.CategoryFetchAllFailedException
import com.qudus.tudee.domain.exception.EmptyInputException
import com.qudus.tudee.domain.exception.InputTooLongException
import com.qudus.tudee.domain.exception.InputTooShortException
import com.qudus.tudee.domain.exception.InvalidStartCharacterException
import com.qudus.tudee.domain.exception.TudeeExecption
import com.qudus.tudee.ui.base.BaseViewModel
import com.qudus.tudee.ui.screen.HomeScreen.HomeUiEffect
import com.qudus.tudee.ui.screen.HomeScreen.UiEventBus
import com.qudus.tudee.ui.screen.taskEditor.TaskEditorUiState.CategoryItemUiState.Companion.isSameSelection
import com.qudus.tudee.ui.screen.taskEditor.TaskEditorUiState.CategoryItemUiState.Companion.selectById
import com.qudus.tudee.ui.screen.taskEditor.TaskEditorUiState.PriorityItemUiState.Companion.isSameSelection
import com.qudus.tudee.ui.screen.taskEditor.TaskEditorUiState.PriorityItemUiState.Companion.selectByPriorityType
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate

open class TaskEditorViewModel: BaseViewModel<TaskEditorUiState>(TaskEditorUiState()), TaskEditorInteraction {

    override fun onTitleValueChange(newTitle: String) {
        updateFormState { it.copy(title = newTitle, titleErrorMessageType = null ) }
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

    override fun onCategoryTypeSelectChange(id: Long) {
        if (_state.value.categoryUiStates.isSameSelection(id)) return
        updateFormState { it.copy(categoryUiStates = it.categoryUiStates.selectById(id)) }
    }

    open fun onGetCategoriesError(exception: TudeeExecption) {
        val categoryErrorType = when (exception) {
            is CategoryFetchAllFailedException -> CategoryErrorType.FAILED_IN_FETCH
            else -> CategoryErrorType.NOT_FOUND
        }

        _state.update { it.copy(categoryErrorMessageType = categoryErrorType) }
    }

    private inline fun updateFormState(transform: (TaskEditorUiState) -> TaskEditorUiState) {
        _state.update { oldState ->
            val newState = transform(oldState)
            val isTitleValid = newState.title.isNotBlank()
            val isCategorySelected = newState.categoryUiStates.any { it.isSelected }
            val isPrioritySelected = newState.priorityUiStates.any { it.isSelected }
            val isEditButtonEnabled = isTitleValid && isCategorySelected && isPrioritySelected

            newState.copy(isPrimaryButtonEnabled = isEditButtonEnabled)
        }
    }

    override fun onCancelChangeTask() {
        viewModelScope.launch {
            UiEventBus.emitEffect(HomeUiEffect.NavigateBackWithCancelation())
        }
    }

    protected fun resetUiState() {
        _state.update {
            it.copy(
                title = "",
                description = "",
                date = TaskEditorUiState.getCurrentDate(),
                priorityUiStates = TaskEditorUiState.PriorityItemUiState.defaultPriorityStates,
                isDatePickerOpen = false,
                isPrimaryButtonEnabled = false,
                isLoading = false,
                titleErrorMessageType = null,
                categoryErrorMessageType = null,
                dataErrorMessageType = null,
                categoryUiStates = it.categoryUiStates.map { c -> c.copy(isSelected = false) }
            )
        }
    }

    fun onSubmitTaskError(exception: TudeeExecption) {
        val errorType = when (exception) {
            is EmptyInputException -> TitleErrorType.EMPTY
            is InvalidStartCharacterException -> TitleErrorType.INVALID_START
            is InputTooLongException -> TitleErrorType.TOO_LONG
            is InputTooShortException -> TitleErrorType.TOO_SHORT
            else -> TitleErrorType.INVALID
        }
        _state.update { it.copy(isLoading = false, titleErrorMessageType = errorType) }
    }
}
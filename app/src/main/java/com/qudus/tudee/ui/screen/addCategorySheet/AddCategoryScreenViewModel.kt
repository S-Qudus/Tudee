package com.qudus.tudee.ui.screen.addCategorySheet
import AddCategoryUiState
import androidx.lifecycle.viewModelScope
import com.qudus.tudee.data.service.CategoryServiceImpl
import com.qudus.tudee.domain.entity.Category
import com.qudus.tudee.domain.exception.CategoryTitleMustStartWithLetterException
import com.qudus.tudee.domain.exception.CategoryTitleTooShortException
import com.qudus.tudee.domain.exception.EmptyCategoryTitleException
import com.qudus.tudee.ui.base.BaseViewModel
import com.qudus.tudee.ui.screen.addTask.AddTaskUiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddCategoryViewModel(
    private val categoryService: CategoryServiceImpl
) : BaseViewModel<AddCategoryUiState>(AddCategoryUiState()), AddCategoryInteraction {

    sealed class Event {
        object NavigateBack : Event()
        data class ShowError(val exception: Throwable) : Event()
    }

    private val _event = MutableSharedFlow<Event>()
    val event = _event.asSharedFlow()

    override fun onTitleValueChange(newTitle: String) {
        _state.update {
            it.copy(
                title = newTitle,
                titleErrorMessageType = null,
                isTitleValid = newTitle.isNotBlank()
            )
        }
    }

    override fun onImageSelected(imageUri: String) {
        _state.update {
            it.copy(
                image = imageUri,
                isImageValid = imageUri.isNotEmpty()
            )
        }
    }

    override fun onAddCategoryClicked() {
        val current = state.value
        val trimmedTitle = current.title.trim()

        if (trimmedTitle.isEmpty()) {
            _state.update { it.copy(titleErrorMessageType = AddTaskUiState.TitleErrorType.EMPTY) }
            emitEvent(Event.ShowError(EmptyCategoryTitleException()))
            return
        }

        if (!trimmedTitle[0].isLetter()) {
            _state.update { it.copy(titleErrorMessageType = AddTaskUiState.TitleErrorType.INVALID_START) }
            emitEvent(Event.ShowError(CategoryTitleMustStartWithLetterException()))
            return
        }

        if (trimmedTitle.length < 3) {
            _state.update { it.copy(titleErrorMessageType = AddTaskUiState.TitleErrorType.TOO_SHORT) }
            emitEvent(Event.ShowError(CategoryTitleTooShortException()))
            return
        }

        if (!current.isImageValid) {
            return
        }

        val category = Category(
            id = 0,
            title = trimmedTitle,
            imagePath = current.image
        )

        _state.update { it.copy(isLoading = true) }

        tryToExecute(
            action = { categoryService.createCategory(category) },
            onSuccess = {
                _state.update { it.copy(isLoading = false, isSheetOpen = false) }
                emitEvent(Event.NavigateBack)
            },
            onError = {
                _state.update { it.copy(isLoading = false, titleErrorMessageType = AddTaskUiState.TitleErrorType.INVALID) }
                emitEvent(Event.ShowError(it))
            }
        )
    }

    override fun onCancelClicked() {
        emitEvent(Event.NavigateBack)
    }

    private fun emitEvent(event: Event) {
        viewModelScope.launch {
            _event.emit(event)
        }
    }
}
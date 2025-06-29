package com.qudus.tudee.ui.screen.categorysheet
import androidx.lifecycle.viewModelScope
import com.qudus.tudee.data.service.CategoryServiceImpl
import com.qudus.tudee.domain.entity.Category
import com.qudus.tudee.domain.exception.CategoryTitleMustStartWithLetterException
import com.qudus.tudee.domain.exception.CategoryTitleTooShortException
import com.qudus.tudee.domain.exception.EmptyCategoryTitleException
import com.qudus.tudee.ui.base.BaseViewModel
import com.qudus.tudee.ui.state.CategoryUiState
import com.qudus.tudee.ui.util.UiImage
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddCategoryViewModel(
    private val categoryService: CategoryServiceImpl
) : BaseViewModel<CategoryUiState>(CategoryUiState()) {

    sealed class Event {
        object NavigateBack : Event()
        data class ShowError(val exception: Throwable) : Event()
    }

    private val _event = MutableSharedFlow<Event>()
    val event = _event.asSharedFlow()

    fun updateTitle(title: String) {
        val trimmed = title.trim()
        val isValid = trimmed.length >= 3 && trimmed[0].isLetter()

        _state.update {
            it.copy(
                title = title,
                isTitleValid = isValid
            )
        }
    }

    fun updateImage(image: UiImage) {
        _state.update { it.copy(image = image.toString()) }
    }

    fun saveCategory() {
        val current = state.value
        val trimmedTitle = current.title.trim()

        if (trimmedTitle.isEmpty()) {
            _state.update { it.copy(isTitleValid = false) }
            emitEvent(Event.ShowError(EmptyCategoryTitleException()))
            return
        }

        if (!trimmedTitle[0].isLetter()) {
            _state.update { it.copy(isTitleValid = false) }
            emitEvent(Event.ShowError(CategoryTitleMustStartWithLetterException()))
            return
        }

        if (trimmedTitle.length < 3) {
            _state.update { it.copy(isTitleValid = false) }
            emitEvent(Event.ShowError(CategoryTitleTooShortException()))
            return
        }

        val category = Category(
            id = 0,
            title = trimmedTitle,
            imagePath = current.image
        )

        tryToExecute(
            action = { categoryService.createCategory(category) },
            onSuccess = { emitEvent(Event.NavigateBack) },
            onError = { emitEvent(Event.ShowError(it)) }
        )
    }

    fun cancel() {
        emitEvent(Event.NavigateBack)
    }

    private fun emitEvent(event: Event) {
        viewModelScope.launch {
            _event.emit(event)
        }
    }
}
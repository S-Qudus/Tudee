package com.qudus.tudee.ui.screen.categorysheet

import androidx.lifecycle.viewModelScope
import com.qudus.tudee.domain.exception.CategoryException
import com.qudus.tudee.domain.exception.EmptyCategoryTitleException
import com.qudus.tudee.ui.base.BaseViewModel
import com.qudus.tudee.ui.state.CategoryUiState
import com.qudus.tudee.ui.util.UiImage
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CategoryViewModel : BaseViewModel<CategoryUiState>(CategoryUiState()) {

    fun updateTitle(title: String) {
        _state.update { it.copy(title = title) }
    }

    fun updateImage(image: UiImage) {
        _state.update { it.copy(image = image.toString()) }
    }

    fun saveCategory(
        onSuccess: (CategoryUiState) -> Unit,
        onError: (CategoryException) -> Unit
    ) {
        val current = state.value

        if (current.title.trim().isEmpty()) {
            onError(EmptyCategoryTitleException())
            return
        }

        _state.update { it.copy() }

        viewModelScope.launch {
            try {
                onSuccess(current)
            } catch (e: CategoryException) {
                onError(e)
            }
        }
    }
    // delete category (confirm)

    //cancel bottom ()

    fun setInitialState(category: CategoryUiState) {
        _state.value = category
    }
}
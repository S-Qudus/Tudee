package com.qudus.tudee.ui.screen.editCategoryScreen

import com.qudus.tudee.ui.screen.addTask.AddTaskUiState


data class EditCategoryUiState(
    val id: Long = 0,
    val title: String = "",
    val image: String = "",
    val isTitleValid: Boolean = false,
    val isImageValid: Boolean = false,
    val isSheetOpen: Boolean = true,
    val isLoading: Boolean = false,
    val titleErrorMessageType: AddTaskUiState.TitleErrorType? = null
) {
    companion object {
        fun validateTitle(title: String): Boolean {
            val trimmed = title.trim()
            return trimmed.length >= 3 && trimmed[0].isLetter()
        }
    }
}
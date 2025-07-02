package com.qudus.tudee.ui.screen.addCategoryScreen

sealed class AddCategoryEvent {
    object NavigateBack : AddCategoryEvent()
    data class ShowError(val exception: Throwable) : AddCategoryEvent()
}
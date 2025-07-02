package com.qudus.tudee.ui.screen.editCategoryScreen

sealed class Event {
    object ShowConfirmDeleteDialog : Event()
    object NavigateBack : Event()
    data class ShowError(val exception: Throwable) : Event()
}
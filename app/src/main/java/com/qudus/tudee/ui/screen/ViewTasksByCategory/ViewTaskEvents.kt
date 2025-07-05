package com.qudus.tudee.ui.screen.ViewTasksByCategory



sealed class ViewTaskEvents {
    object NavigateBack : ViewTaskEvents()
    data class NavigateToEditCategory(val categoryId: Long) : ViewTaskEvents()

}
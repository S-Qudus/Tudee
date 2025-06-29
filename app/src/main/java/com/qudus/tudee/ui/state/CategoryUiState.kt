package com.qudus.tudee.ui.state

data class CategoryUiState (
    val id: Long = 0L,
    val title: String = "",
    val image: String="",
    val isPredefined: Boolean = false,
    val isEditMode: Boolean = false,
    val isTitleValid: Boolean = true
)
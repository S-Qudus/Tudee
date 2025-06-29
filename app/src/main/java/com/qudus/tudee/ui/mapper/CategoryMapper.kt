package com.qudus.tudee.ui.mapper

import com.qudus.tudee.domain.entity.Category
import com.qudus.tudee.ui.screen.addTask.AddTaskUiState

fun Category.toCategoryItemUiState(): AddTaskUiState.CategoryItemUiState{
    return AddTaskUiState.CategoryItemUiState(
        id = this.id,
        imagePath = this.imagePath,
        title = this.title,
        defaultCategoryType = this.defaultCategoryType
    )
}


package com.qudus.tudee.ui.mapper

import com.qudus.tudee.domain.entity.Category
import com.qudus.tudee.ui.screen.taskEditor.TaskEditorUiState

fun Category.toCategoryItemUiState(categoryId: Long? = null): TaskEditorUiState.CategoryItemUiState{
    return TaskEditorUiState.CategoryItemUiState(
        id = this.id,
        imagePath = this.imagePath,
        title = this.title,
        isSelected = this.id == categoryId,
        defaultCategoryType = this.defaultCategoryType
    )
}


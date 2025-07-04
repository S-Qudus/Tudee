package com.qudus.tudee.ui.mapper

import com.qudus.tudee.domain.entity.Category
import com.qudus.tudee.ui.state.CategoryUiState
import com.qudus.tudee.ui.screen.categories.CategoryUiItem
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

fun Category.toCategoryUiState(): CategoryUiState {
    return CategoryUiState(
        id = this.id,
        title = this.title,
        defaultCategoryType = this.defaultCategoryType
    )
}

fun Category.toCategoryUiItem(categoryId: Long? = null, taskCount: Int): CategoryUiItem{
    return CategoryUiItem(
        id = this.id,
        title = this.title,
        imagePath = this.imagePath,
        defaultCategoryType = this.defaultCategoryType,
        isSelected = this.id == categoryId,
        taskCount = taskCount
    )

}


package com.qudus.tudee.ui.screen.categories

import com.qudus.tudee.domain.entity.DefaultCategoryType

data class CategoryUiItem(
    val id: Long,
    val title: String,
    val imagePath: String,
    val defaultCategoryType: DefaultCategoryType? = null,
    val isSelected: Boolean,
    val taskCount: Int = 0
)

data class CategoriesUiState(
    val categories: List<CategoryUiItem> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val createSuccessMessage: String? = null,
    val showBottomSheet: Boolean = false
)

data class CreateCategoryUiState(
    val isLoading: Boolean = false,
    val isCreated: Boolean = false,
    val errorMessage: String? = null
)
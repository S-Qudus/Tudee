package com.qudus.tudee.ui.state

import com.qudus.tudee.domain.entity.DefaultCategoryType

data class CategoryUiState(
    val id: Long = 0,
    val title: String = "",
    val image: String = "",
    val isPredefined: Boolean = false,
    val defaultCategoryType: DefaultCategoryType? = null
)
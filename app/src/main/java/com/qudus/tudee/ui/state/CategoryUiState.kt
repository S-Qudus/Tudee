package com.qudus.tudee.ui.state

import androidx.annotation.DrawableRes
import com.qudus.tudee.R
import com.qudus.tudee.domain.entity.DefaultCategoryType

data class CategoryUiState(
    val id: Long = 0,
    val title: String = "",
    @DrawableRes
    val image: Int = R.drawable.icon_book_open,
    val isPredefined: Boolean = false,
    val defaultCategoryType: DefaultCategoryType? = null
)
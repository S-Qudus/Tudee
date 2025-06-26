package com.qudus.tudee.ui.state

import androidx.annotation.DrawableRes
import com.qudus.tudee.R

data class CategoryUiState (
    val id: String="0",
    val title:String="",
     @DrawableRes
     val image: Int = R.drawable.icon_book_open,
     val isPredefined: Boolean= false
)
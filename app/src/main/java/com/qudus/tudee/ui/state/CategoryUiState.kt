package com.qudus.tudee.ui.state

import androidx.annotation.DrawableRes

data class CategoryUiState (
    val id: String="0",
    val title:String="",
     @DrawableRes
     val image: Int? = null,
     val isPredefined: Boolean= false
)
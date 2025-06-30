package com.qudus.tudee.ui.screen.onBoarding

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class OnBoardingUiState(
    val currentPage: Int = 0,
    val onBoardingItemUiState: List<OnBoardingItemUiState> = emptyList(),
    val isDarkTheme: Boolean = false,
    val isCompleted: Boolean = false
)

data class OnBoardingItemUiState(
    @StringRes val title: Int,
    @StringRes val description: Int,
    @DrawableRes val imageRes: Int
)
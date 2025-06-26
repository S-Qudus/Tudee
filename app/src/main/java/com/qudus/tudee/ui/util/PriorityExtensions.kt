package com.qudus.tudee.ui.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.qudus.tudee.ui.state.PriorityUiState

import com.qudus.tudee.ui.designSystem.theme.Theme

@Composable
fun PriorityUiState.getColor(): Color {
    return when (this) {
        PriorityUiState.HIGH -> Theme.color.pinkAccent
        PriorityUiState.MEDIUM -> Theme.color.yellowAccent
        PriorityUiState.LOW -> Theme.color.greenAccent
    }
}
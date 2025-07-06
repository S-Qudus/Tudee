package com.qudus.tudee.ui.state

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.qudus.tudee.R
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.ui.util.extension.toStringResource

enum class TaskStateUiState() {
    IN_PROGRESS,
    TODO,
    DONE;

    fun getNextState(): TaskStateUiState = when (this) {
        TODO -> IN_PROGRESS
        else -> DONE
    }
}

@Composable
fun TaskStateUiState.getTextColor(): Color = when (this) {
    TaskStateUiState.TODO -> Theme.color.yellowAccent
    TaskStateUiState.IN_PROGRESS -> Theme.color.purpleAccent
    TaskStateUiState.DONE -> Theme.color.greenAccent
}

@Composable
fun TaskStateUiState.getBackgroundColor(): Color = when (this) {
    TaskStateUiState.TODO -> Theme.color.yellowVariant
    TaskStateUiState.IN_PROGRESS -> Theme.color.purpleVariant
    TaskStateUiState.DONE -> Theme.color.greenVariant
}

@Composable
fun TaskStateUiState.getStateText(): String = when (this) {
    TaskStateUiState.TODO -> R.string.to_do.toStringResource()
    TaskStateUiState.IN_PROGRESS -> R.string.in_progress.toStringResource()
    TaskStateUiState.DONE -> R.string.done.toStringResource()
}
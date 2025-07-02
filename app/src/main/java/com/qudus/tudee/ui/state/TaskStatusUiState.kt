package com.qudus.tudee.ui.state

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.qudus.tudee.R
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.ui.util.extension.toStringResource

enum class TaskStatusUiState() {
    TODO,
    IN_PROGRESS,
    DONE;

    fun getNextState(): TaskStatusUiState = when (this) {
        TODO -> IN_PROGRESS
        else -> DONE
    }
}

@Composable
fun TaskStatusUiState.getTextColor(): Color = when (this) {
    TaskStatusUiState.TODO -> Theme.color.yellowAccent
    TaskStatusUiState.IN_PROGRESS -> Theme.color.purpleAccent
    TaskStatusUiState.DONE -> Theme.color.greenAccent
}

@Composable
fun TaskStatusUiState.getBackgroundColor(): Color = when (this) {
    TaskStatusUiState.TODO -> Theme.color.yellowVariant
    TaskStatusUiState.IN_PROGRESS -> Theme.color.purpleVariant
    TaskStatusUiState.DONE -> Theme.color.greenVariant
}

@Composable
fun TaskStatusUiState.getStatusText(): String = when (this) {
    TaskStatusUiState.TODO -> R.string.todo.toStringResource()
    TaskStatusUiState.IN_PROGRESS -> R.string.in_progress.toStringResource()
    TaskStatusUiState.DONE -> R.string.done.toStringResource()
}
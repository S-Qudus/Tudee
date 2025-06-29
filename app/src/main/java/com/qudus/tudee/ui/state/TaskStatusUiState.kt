package com.qudus.tudee.ui.state

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.qudus.tudee.R
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.ui.util.extension.toStringResource

enum class TaskStatusUiState(@StringRes val status: Int) {
    TODO(R.string.todo),
    IN_PROGRESS(R.string.in_progress),
    DONE(R.string.done);

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
fun TaskStatusUiState.getStatusText(): String {
    return this.status.toStringResource()
}
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

    @Composable
    fun getTextColor(): Color = when (this) {
        TODO -> Theme.color.yellowAccent
        IN_PROGRESS -> Theme.color.purpleAccent
        DONE -> Theme.color.greenAccent
    }

    @Composable
    fun getBackgroundColor(): Color = when (this) {
        TODO -> Theme.color.yellowVariant
        IN_PROGRESS -> Theme.color.purpleVariant
        DONE -> Theme.color.greenVariant
    }

    @Composable
    fun getStatusText(): String {
       return this.status.toStringResource()
    }

    fun getNextState(): TaskStatusUiState = when (this) {
        TODO -> IN_PROGRESS
        else -> DONE
    }

}
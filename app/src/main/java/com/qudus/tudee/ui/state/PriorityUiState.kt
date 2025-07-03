package com.qudus.tudee.ui.state

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import com.qudus.tudee.R
import com.qudus.tudee.domain.entity.Priority
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.ui.util.extension.toPainter
import com.qudus.tudee.ui.util.extension.toStringResource

enum class PriorityUiState() {
    HIGH,
    MEDIUM,
    LOW;

    fun toDomain(): Priority = when (this) {
        HIGH -> Priority.HIGH
        MEDIUM -> Priority.MEDIUM
        LOW -> Priority.LOW
    }
}

@Composable
fun PriorityUiState.getColor(): Color = when (this) {
    PriorityUiState.LOW -> Theme.color.greenAccent
    PriorityUiState.MEDIUM -> Theme.color.yellowAccent
    PriorityUiState.HIGH -> Theme.color.pinkAccent
}

@Composable
fun PriorityUiState.getIcon(): Painter = when (this) {
    PriorityUiState.LOW -> R.drawable.icon_trade_down.toPainter()
    PriorityUiState.MEDIUM -> R.drawable.icon_alert.toPainter()
    PriorityUiState.HIGH -> R.drawable.icon_flag.toPainter()
}

@Composable
fun PriorityUiState.getLabel(): String = when (this) {
    PriorityUiState.LOW -> R.string.low.toStringResource()
    PriorityUiState.MEDIUM -> R.string.medium.toStringResource()
    PriorityUiState.HIGH -> R.string.high.toStringResource()
}

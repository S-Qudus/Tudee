package com.qudus.tudee.ui.state

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import com.qudus.tudee.R
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.ui.util.extension.toPainter
import com.qudus.tudee.ui.util.extension.toStringResource

enum class PriorityUiState() {
    HIGH,
    MEDIUM,
    LOW;

    @Composable
    fun getIcon(): Painter = when (this) {
        LOW -> R.drawable.icon_trade_down.toPainter()
        MEDIUM -> R.drawable.icon_alert.toPainter()
        HIGH -> R.drawable.icon_flag.toPainter()
    }

    @Composable
    fun getColor(): Color = when (this) {
        LOW -> Theme.color.greenAccent
        MEDIUM -> Theme.color.yellowAccent
        HIGH -> Theme.color.pinkAccent
    }

    @Composable
    fun getLabel(): String = when (this) {
        LOW -> R.string.low.toStringResource()
        MEDIUM -> R.string.medium.toStringResource()
        HIGH -> R.string.high.toStringResource()
    }
}
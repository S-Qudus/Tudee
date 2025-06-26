package com.qudus.tudee.ui.designSystem.component.text_field

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

sealed class TudeeTextFieldType {
    data class Paragraph(
        val maxLength: Int = 500,
        val maxLines: Int = 10,
        val height: Dp = 168.dp
    ) : TudeeTextFieldType()

    data class WithIcon(
        val icon: Painter,
        val maxLength: Int = 100,
        val maxLines: Int = 1,
        val height: Dp = 56.dp
    ) : TudeeTextFieldType()
}
package com.qudus.tudee.presentation.designSystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.qudus.tudee.presentation.designSystem.color.LocalTudeeColors
import com.qudus.tudee.presentation.designSystem.color.TudeeColors
import com.qudus.tudee.presentation.designSystem.textStyle.LocalTudeeTextStyle
import com.qudus.tudee.presentation.designSystem.textStyle.TudeeTextStyle

object Theme {
    val color: TudeeColors
        @Composable @ReadOnlyComposable get() = LocalTudeeColors.current

    val textStyle: TudeeTextStyle
        @Composable @ReadOnlyComposable get() = LocalTudeeTextStyle.current
}
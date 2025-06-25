package com.qudus.tudee.ui.designSystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.qudus.tudee.ui.designSystem.color.LocalTudeeColors
import com.qudus.tudee.ui.designSystem.color.TudeeColors
import com.qudus.tudee.ui.designSystem.textStyle.LocalTudeeTextStyle
import com.qudus.tudee.ui.designSystem.textStyle.TudeeTextStyle

object Theme {
    val color: TudeeColors
        @Composable @ReadOnlyComposable get() = LocalTudeeColors.current

    val textStyle: TudeeTextStyle
        @Composable @ReadOnlyComposable get() = LocalTudeeTextStyle.current
}
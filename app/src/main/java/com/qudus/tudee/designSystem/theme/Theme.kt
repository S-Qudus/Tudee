package com.qudus.tudee.designSystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.qudus.tudee.designSystem.color.LocalTudeeColors
import com.qudus.tudee.designSystem.color.TudeeColors
import com.qudus.tudee.designSystem.textStyle.LocalTudeeTextStyle
import com.qudus.tudee.designSystem.textStyle.TudeeTextStyle
import androidx.compose.runtime.staticCompositionLocalOf

object Theme {
    val color: TudeeColors
        @Composable @ReadOnlyComposable get() = LocalTudeeColors.current

    val textStyle: TudeeTextStyle
        @Composable @ReadOnlyComposable get() = LocalTudeeTextStyle.current
    val dimension: Dimension
        @Composable
        @ReadOnlyComposable get() = LocalDimension.current

}

private val LocalDimension = staticCompositionLocalOf {
    Dimension
}

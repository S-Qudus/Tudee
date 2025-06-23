package com.qudus.tudee.presentation.designSystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.qudus.tudee.presentation.designSystem.color.LocalTudeeColors
import com.qudus.tudee.presentation.designSystem.color.darkThemeColor
import com.qudus.tudee.presentation.designSystem.color.lightThemeColor
import com.qudus.tudee.presentation.designSystem.textStyle.LocalTudeeTextStyle
import com.qudus.tudee.presentation.designSystem.textStyle.defaultTextStyle

@Composable
fun TudeeTheme(
    isDarkTheme: Boolean,
    content: @Composable () -> Unit
) {

    val theme = if (isDarkTheme) darkThemeColor else lightThemeColor
    CompositionLocalProvider(
        LocalTudeeColors provides theme,
        LocalTudeeTextStyle provides defaultTextStyle
    ) {
        content()
    }
}

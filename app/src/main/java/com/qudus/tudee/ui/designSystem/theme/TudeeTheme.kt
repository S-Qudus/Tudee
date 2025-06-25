package com.qudus.tudee.ui.designSystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.qudus.tudee.ui.designSystem.color.LocalTudeeColors
import com.qudus.tudee.ui.designSystem.color.darkThemeColor
import com.qudus.tudee.ui.designSystem.color.lightThemeColor
import com.qudus.tudee.ui.designSystem.textStyle.LocalTudeeTextStyle
import com.qudus.tudee.ui.designSystem.textStyle.defaultTextStyle

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

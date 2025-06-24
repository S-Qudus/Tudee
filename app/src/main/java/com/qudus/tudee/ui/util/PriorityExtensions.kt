package com.qudus.tudee.ui.util
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.qudus.tudee.data.model.Priority
import com.qudus.tudee.designSystem.color.darkThemeColor
import com.qudus.tudee.designSystem.color.lightThemeColor

@Composable
fun Priority.getColor(): Color {
    val isDark = isSystemInDarkTheme()

    return when (this) {
        Priority.HIGH -> if (isDark) darkThemeColor.pinkAccent else lightThemeColor.pinkAccent
        Priority.MEDIUM -> if (isDark) darkThemeColor.yellowAccent else lightThemeColor.yellowAccent
        Priority.LOW -> if (isDark) darkThemeColor.greenAccent else lightThemeColor.greenAccent
    }
}

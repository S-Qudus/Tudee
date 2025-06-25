package com.qudus.tudee.ui.designSystem.color

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

data class TudeeColors(
    val primary: Color,
    val secondary: Color,
    val primaryVariant: Color,
    val primaryGradient: Brush,

    val title: Color,
    val body: Color,
    val hint: Color,
    val stroke: Color,
    val surfaceLow: Color,
    val surface: Color,
    val surfaceHigh: Color,
    val onPrimary: Color,
    val onPrimaryCaption: Color,
    val onPrimaryCard: Color,
    val onPrimaryStroke: Color,
    val disable: Color,


    val pinkAccent: Color,
    val yellowAccent: Color,
    val greenAccent: Color,
    val purpleAccent: Color,
    val error: Color,
    val overlay: Color,
    val emojiTint: Color,
    val yellowVariant: Color,
    val greenVariant: Color,
    val purpleVariant: Color,
    val errorVariant: Color,
)

val LocalTudeeColors = staticCompositionLocalOf { lightThemeColor }
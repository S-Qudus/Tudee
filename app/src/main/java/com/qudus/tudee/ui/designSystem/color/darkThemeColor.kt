package com.qudus.tudee.ui.designSystem.color

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val darkThemeColor = TudeeColors(
    primary = Color(0xFF3090BF),
    secondary = Color(0xFFCC7851),
    primaryVariant = Color(0xFF05202E),
    primaryGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFF3090BF),
            Color(0xFF3A9CCD),
        )
    ),

    title = Color(0xDEFFFFFF),
    body = Color(0x99FFFFFF),
    hint = Color(0x61FFFFFF),
    stroke = Color(0x1FFFFFFF),

    surfaceLow = Color(0xFF020108),
    surface = Color(0xFF0D0C14),
    surfaceHigh = Color(0xFF0F0E19),
    onPrimary = Color(0xDEFFFFFF),
    onPrimaryCaption = Color(0xB2FFFFFF),
    onPrimaryCard = Color(0x29060414),
    onPrimaryStroke = Color(0x99242424),
    disable = Color(0xFF1D1E1F),

    pinkAccent = Color(0xFFCC5268),
    yellowAccent = Color(0xFFB28F25),
    greenAccent = Color(0xFF4D8064),
    purpleAccent = Color(0xFF6F63B2),
    error = Color(0xFFF95555),
    overlay = Color(0x5202151E),
    emojiTint = Color(0xDE1F1F1F),

    yellowVariant = Color(0xFF1F1E1C),
    greenVariant = Color(0xFF1C1F1D),
    purpleVariant = Color(0xFF1C1A33),
    errorVariant = Color(0xFF1F1111),
    TransparentBlack10 = Color(0x1A000000)

)
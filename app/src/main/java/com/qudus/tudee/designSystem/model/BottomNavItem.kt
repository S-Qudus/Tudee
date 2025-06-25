package com.qudus.tudee.designSystem.model

import androidx.compose.ui.graphics.painter.Painter

data class BottomNavItem(
    val route: String,
    val iconFill: Painter,
    val iconStroke: Painter
)
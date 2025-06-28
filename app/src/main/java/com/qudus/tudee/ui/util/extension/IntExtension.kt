package com.qudus.tudee.ui.util.extension

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

@Composable
fun Int.toPainter(): Painter {
    return painterResource(this)
}

@Composable
fun Int.toStringResource(): String {
    return stringResource(this)
}
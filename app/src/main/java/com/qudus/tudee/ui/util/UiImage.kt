package com.qudus.tudee.ui.util

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import coil.compose.rememberAsyncImagePainter

sealed class UiImage {
    data class Drawable(@DrawableRes val resId: Int) : UiImage()
    data class External(val uri: String) : UiImage()


    @Composable
    fun asPainter(): Painter {
        return when (this) {
            is Drawable -> painterResource(id = resId)
            is External -> rememberAsyncImagePainter(model = uri)
        }
    }

    fun asString(): String = when (this) {
        is Drawable -> resId.toString()
        is External -> uri

    }
}

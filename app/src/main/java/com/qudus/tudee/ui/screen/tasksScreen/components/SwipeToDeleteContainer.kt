package com.qudus.tudee.ui.screen.tasksScreen.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.qudus.tudee.R
import com.qudus.tudee.ui.designSystem.theme.Theme
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun <T> SwipeToRevealContainer(
    item: T, onDelete: (T) -> Unit, modifier: Modifier = Modifier, content: @Composable (T) -> Unit
) {
    val swipeOffset = remember { Animatable(0f) }
    var containerWidth by remember { mutableFloatStateOf(0f) }
    val coroutineScope = rememberCoroutineScope()

    val revealThreshold = containerWidth * 0.2f

    val backgroundColor by animateColorAsState(
        targetValue = if (swipeOffset.value <= -revealThreshold) {
            Theme.color.errorVariant
        } else {
            Theme.color.errorVariant
        }, animationSpec = tween(200)
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(backgroundColor, RoundedCornerShape(16.dp))
            .onSizeChanged { containerWidth = it.width.toFloat() },
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor, RoundedCornerShape(16.dp)),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    coroutineScope.launch {
                        onDelete(item)
                    }
                }, modifier = Modifier.padding(end = 16.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.icon_delete),
                    contentDescription = "delete item",
                    tint = Theme.color.error
                )
            }
        }

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .offset { IntOffset(swipeOffset.value.roundToInt(), 0) }
                .pointerInput(Unit) {
                    detectHorizontalDragGestures(onDragEnd = {
                        coroutineScope.launch {
                            if (kotlin.math.abs(swipeOffset.value) < revealThreshold) {
                                swipeOffset.animateTo(0f, animationSpec = tween(200))
                            } else {
                                swipeOffset.animateTo(
                                    -revealThreshold, animationSpec = tween(200)
                                )
                            }
                        }
                    }, onHorizontalDrag = { _, dragAmount ->
                        coroutineScope.launch {
                            if (dragAmount < 0) {
                                val newOffset = (swipeOffset.value + dragAmount).coerceIn(
                                    -revealThreshold * 1.5f, 0f
                                )
                                swipeOffset.snapTo(newOffset)
                            }
                        }
                    })
                }
                .clip(RoundedCornerShape(16.dp))) {
            content(item)
        }
    }
}

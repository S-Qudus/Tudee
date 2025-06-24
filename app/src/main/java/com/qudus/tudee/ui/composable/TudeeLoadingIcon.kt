package com.qudus.tudee.ui.composable

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.qudus.tudee.R
import com.qudus.tudee.designSystem.theme.Theme
import com.qudus.tudee.designSystem.theme.TudeeTheme

@Composable
fun TudeeLoadingIcon(
    tint: Color,
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    duration: Int = 1000
) {
    val infiniteTransition = rememberInfiniteTransition(label = "loading_rotation")

    val color = infiniteTransition.animateColor(
        initialValue = tint,
        targetValue = tint.copy(alpha = 0.4f),
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = duration,
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "rotation"
    )

    Icon(
        painter = painterResource(id = R.drawable.icon_loading),
        contentDescription = stringResource(R.string.loading),
        modifier = modifier
            .size(size),
        tint = color.value
    )
}

@Preview
@Composable
private fun TudeeLoadingIconPrev() {
    TudeeTheme(false) {
        TudeeLoadingIcon(tint = Theme.color.primary)
    }
}
package com.qudus.tudee.ui.designSystem.component.ThemeSwitchButton

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.qudus.tudee.R
import com.qudus.tudee.ui.designSystem.theme.Theme

@Composable
internal fun BoxScope.AnimatedSun(
    isDarkMode: Boolean,
    animationDuration: Int
) {
    AnimatedVisibility(
        visible = !isDarkMode,
        enter = fadeIn(tween(animationDuration, easing = EaseOut)),
        exit = slideOutHorizontally(
            targetOffsetX = { it },
            animationSpec = tween(animationDuration, easing = EaseOut)
        ) + fadeOut(tween(animationDuration, easing = EaseOut)),
        modifier = Modifier.align(Alignment.CenterStart)
    ) {
        Box(
            modifier = Modifier
                .size(28.dp)
                .background(Theme.color.yellowAccent, CircleShape)
        )
    }
}

@Composable
internal fun BoxScope.AnimatedMoon(
    isDarkMode: Boolean,
    animationDuration: Int
) {
    AnimatedVisibility(
        visible = isDarkMode,
        enter = fadeIn(tween(animationDuration, easing = EaseOut)),
        exit = slideOutHorizontally(
            targetOffsetX = { -it },
            animationSpec = tween(animationDuration, easing = EaseOut)
        ) + fadeOut(tween(animationDuration, easing = EaseOut)),
        modifier = Modifier.align(Alignment.CenterEnd)
    ) {
        Box(
            modifier = Modifier
                .size(28.dp)
                .background(Color.White, CircleShape)
        )
    }
}

@Composable
internal fun BoxScope.MoonCraterLarge(isDarkMode: Boolean) {
    AnimatedVisibility(
        visible = isDarkMode,
        enter = fadeIn(tween(800, easing = EaseOut)),
        exit = fadeOut(tween(800, easing = EaseOut))
    ) {
        Box(
            modifier = Modifier
                .size(8.dp)
                .align(Alignment.CenterEnd)
                .offset(x = (-10).dp, y = (-8).dp)
                .background(Color.White, CircleShape)
                .border(1.dp, Theme.color.primary, CircleShape)
        )
    }
}

@Composable
internal fun BoxScope.MoonCraterMedium(isDarkMode: Boolean) {
    AnimatedVisibility(
        visible = isDarkMode,
        enter = fadeIn(tween(800, easing = EaseOut)),
        exit = fadeOut(tween(800, easing = EaseOut))
    ) {
        Box(
            modifier = Modifier
                .size(14.dp)
                .align(Alignment.CenterEnd)
                .offset(x = (-20).dp, y = 0.dp)
                .background(Color.White, CircleShape)
                .border(1.dp, Theme.color.primary, CircleShape)
        )
    }
}

@Composable
internal fun BoxScope.MoonCraterSmall(isDarkMode: Boolean) {
    AnimatedVisibility(
        visible = isDarkMode,
        enter = fadeIn(tween(800, easing = EaseOut)),
        exit = fadeOut(tween(800, easing = EaseOut))
    ) {
        Box(
            modifier = Modifier
                .size(4.dp)
                .align(Alignment.CenterEnd)
                .offset(x = (-12).dp, y = 6.dp)
                .background(Color.White, CircleShape)
                .border(1.dp, Theme.color.primary, CircleShape)
        )
    }
}

@Composable
internal fun BoxScope.AnimatedStars(
    isDarkMode: Boolean,
    animationDuration: Int
) {
    AnimatedVisibility(
        visible = isDarkMode,
        enter = fadeIn(tween(animationDuration, easing = EaseOut)),
        exit = fadeOut(tween(animationDuration, easing = EaseOut)),
        modifier = Modifier.align(Alignment.CenterStart)
    ) {
        Image(
            painter = painterResource(R.drawable.icon_stars),
            contentDescription = null,
            modifier = Modifier
                .size(28.dp)
                .offset(x = 2.dp)
        )
    }
}

@Composable
internal fun BoxScope.FirstGreyCloud(isDarkMode: Boolean) {
    AnimatedCircle(
        isClicked = isDarkMode,
        modifier = Modifier.align(Alignment.TopEnd),
        size = 20.dp,
        startOffsetX = 45.dp,
        clickedOffsetX = 50.dp,
        startOffsetY = (-3).dp,
        clickedOffsetY = 50.dp,
        color = Theme.color.surfaceLow
    )
}

@Composable
internal fun BoxScope.SecondGreyCloud(isDarkMode: Boolean) {
    AnimatedCircle(
        isClicked = isDarkMode,
        modifier = Modifier.align(Alignment.BottomEnd),
        size = 16.dp,
        startOffsetX = 32.dp,
        clickedOffsetX = 50.dp,
        startOffsetY = (0).dp,
        clickedOffsetY = 50.dp,
        color = Theme.color.surfaceLow
    )
}

@Composable
internal fun BoxScope.FirstWhiteCloud(isDarkMode: Boolean) {
    AnimatedCircle(
        isClicked = isDarkMode,
        modifier = Modifier.align(Alignment.BottomEnd),
        size = 18.dp,
        startOffsetX = 1.dp,
        clickedOffsetX = 50.dp,
        startOffsetY = 8.dp,
        clickedOffsetY = 50.dp,
        color = Color.White
    )
}

@Composable
internal fun BoxScope.TransformingWhiteCloud(
    isDarkMode: Boolean,
    animationDuration: Int
) {
    AnimatedVisibility(
        visible = !isDarkMode,
        enter = slideIn(
            initialOffset = { IntOffset((-1.5 * it.width).toInt(), (it.height / 2).toInt()) },
            animationSpec = tween(animationDuration, easing = EaseOut)
        ) + scaleIn(
            initialScale = 0.5f,
            animationSpec = tween(animationDuration, easing = EaseOut)
        ) + fadeIn(tween(animationDuration, easing = EaseOut)),
        exit = slideOut(
            targetOffset = { IntOffset((-1.5 * it.width).toInt(), (it.height / 2).toInt()) },
            animationSpec = tween(animationDuration, easing = EaseOut)
        ) + scaleOut(
            targetScale = 0.5f,
            animationSpec = tween(animationDuration, easing = EaseOut)
        ) + fadeOut(tween(animationDuration, easing = EaseOut)),
        modifier = Modifier.align(Alignment.BottomEnd)
    ) {
        Box(
            modifier = Modifier
                .size(14.dp, 16.dp)
                .offset(x = (-12).dp, y = 8.dp)
                .background(Theme.color.surfaceHigh, RoundedCornerShape(100.dp))
        )
    }
}

@Composable
internal fun BoxScope.AnimatedTransformingMoonCircle(
    isDarkMode: Boolean,
    animationDuration: Int
) {
    val moonCircleSize by animateDpAsState(
        targetValue = if (isDarkMode) 10.dp else 29.dp,
        animationSpec = tween(animationDuration, easing = EaseOut)
    )
    val circleColor by animateColorAsState(
        targetValue = if (isDarkMode) Color.White else Color.White,
        animationSpec = tween(animationDuration, easing = EaseOut)
    )
    AnimatedCircle(
        isClicked = isDarkMode,
        modifier = Modifier.align(Alignment.TopEnd),
        size = moonCircleSize,
        startOffsetX = 15.dp,
        startOffsetY = (-2).dp,
        clickedOffsetX = (-14).dp,
        clickedOffsetY = 8.dp,
        color = circleColor,
        hasInnerShadow = true
    )
}

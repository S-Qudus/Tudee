package com.qudus.tudee.designSystem.component.buttons.ThemeSwitchButton

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.qudus.tudee.designSystem.theme.Theme

@Composable
fun ThemeSwitchButton(
    isDarkMode: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val animationDuration = 800
    val bgColor by animateColorAsState(
        targetValue = if (isDarkMode) Color(0xFF151535) else Theme.color.primary,
        animationSpec = tween(durationMillis = animationDuration, easing = EaseOut)
    )

    Box(
        modifier = modifier
            .size(64.dp, 36.dp)
            .clip(RoundedCornerShape(100.dp))
            .background(bgColor)
            .border(1.dp, Theme.color.stroke, RoundedCornerShape(100.dp))
            .padding(horizontal = 2.dp)
            .toggleable(
                value = isDarkMode,
                onValueChange = onCheckedChange,
                role = Role.Switch,
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            )
    ) {
        AnimatedStars(isDarkMode, animationDuration)
        AnimatedSun(isDarkMode, animationDuration)
        AnimatedMoon(isDarkMode, animationDuration)
        MoonCraterLarge(isDarkMode)
        MoonCraterMedium(isDarkMode)
        MoonCraterSmall(isDarkMode)
        FirstGreyCloud(isDarkMode)
        SecondGreyCloud(isDarkMode)
        FirstWhiteCloud(isDarkMode)
        TransformingWhiteCloud(isDarkMode, animationDuration)
        AnimatedTransformingMoonCircle(isDarkMode, animationDuration)
    }
}


@Composable
internal fun AnimatedCircle(
    isClicked: Boolean,
    modifier: Modifier,
    size: Dp,
    startOffsetX: Dp,
    clickedOffsetX: Dp,
    startOffsetY: Dp,
    clickedOffsetY: Dp,
    color: Color = Color.White,
    hasInnerShadow: Boolean = false,
    durationMillis: Int = 800
) {
    val offsetX by animateDpAsState(
        targetValue = if (isClicked) clickedOffsetX else startOffsetX,
        animationSpec = tween(durationMillis, easing = EaseOut)
    )
    val offsetY by animateDpAsState(
        targetValue = if (isClicked) clickedOffsetY else startOffsetY,
        animationSpec = tween(durationMillis, easing = EaseOut)
    )
    val innerShadowColor by animateColorAsState(
        targetValue = if (isClicked && hasInnerShadow) Color(0xFFBFD2FF) else Color.Transparent,
        animationSpec = tween(durationMillis, easing = EaseOut)
    )

    Box(
        modifier = modifier
            .size(size)
            .offset(x = offsetX, y = offsetY)
            .background(color, CircleShape)
            .then(
                if (hasInnerShadow) Modifier.border(1.dp, innerShadowColor, CircleShape)
                else Modifier
            )
    )
}

@PreviewLightDark
@Composable
private fun ThemeSwitchButtonPreview() {
    var checked by remember { mutableStateOf(false) }
    ThemeSwitchButton(
        isDarkMode = checked,
        onCheckedChange = { checked = it },
        modifier = Modifier.padding(8.dp)
    )
}
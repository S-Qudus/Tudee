package com.qudus.tudee.ui.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.qudus.tudee.R
import com.qudus.tudee.ui.designSystem.theme.Theme

@Composable
fun TudeeTextBadge(
    badgeNumber: String,
    modifier: Modifier = Modifier,
    contentColor: Color = Theme.color.hint,
    containerColor: Color = Theme.color.surfaceLow
) {
    Text(
        modifier = modifier
            .widthIn(min = 32.dp)
            .background(containerColor, shape = CircleShape)
            .padding(vertical = 2.dp, horizontal = 4.dp),
        text = badgeNumber,
        color = contentColor,
        textAlign = TextAlign.Center,
        maxLines = 1
    )
}

@Preview
@Composable
private fun TudeeTextBadgePreview() {
    TudeeTextBadge(
        badgeNumber = "16",
        contentColor = Theme.color.hint,
        containerColor = Theme.color.surfaceLow
    )
}

@Composable
fun TudeeCheckBadge(visible: Boolean, modifier: Modifier = Modifier) {
    AnimatedVisibility(
        modifier = modifier,
        visible = visible,
        enter = scaleIn(animationSpec =  spring(stiffness = Spring.StiffnessMediumLow)),
        exit = scaleOut(animationSpec =  spring(stiffness = Spring.StiffnessMediumLow))
    ) {
        Icon(
            modifier = Modifier
                .background(color = Theme.color.greenAccent, shape = CircleShape)
                .padding(4.dp),
            painter = painterResource(R.drawable.icon_tick_double),
            tint = Theme.color.onPrimary,
            contentDescription = "$visible"
        )
    }
}

@Preview
@Composable
private fun TudeeCheckedBadge() {
    TudeeCheckBadge(visible = true)
}


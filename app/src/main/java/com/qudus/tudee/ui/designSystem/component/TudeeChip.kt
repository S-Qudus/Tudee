package com.qudus.tudee.ui.designSystem.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.qudus.tudee.R
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.ui.designSystem.theme.TudeeTheme

@Composable
fun TudeeChip(
    label: String,
    icon: Painter,
    modifier: Modifier = Modifier,
    isActive: Boolean = false,
    isClickEnabled: Boolean = false,
    onChipClick: () -> Unit = {},
    activeBackgroundColor: Color = Theme.color.primary,
    labelSize: TextUnit = Theme.textStyle.label.small.fontSize,
    iconSize: Dp = 12.dp,
    backgroundColor: Color
) {
    val animatedContainerColor by animateColorAsState(
        targetValue = if (isActive) activeBackgroundColor else Theme.color.surfaceLow,
    )
    val animatedContentColor = animateColorAsState(
        targetValue = if (isActive) Theme.color.onPrimary else Theme.color.hint,
    )

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(100.dp))
            .background(animatedContainerColor)
            .clickable(
                enabled = isClickEnabled,
                onClick = { onChipClick() },
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            )
            .padding(horizontal = 8.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            painter = icon,
            contentDescription = "label",
            tint = animatedContentColor.value,
            modifier = Modifier.size(iconSize)
        )
        Text(
            text = label,
            color = animatedContentColor.value,
            style = Theme.textStyle.label.small
        )

    }
}

@Preview(showBackground = true)
@Composable
fun PriorityChipsPreview() {
    val colors = Theme.color
    val textColor = colors.onPrimary
    TudeeTheme(true) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TudeeChip(
                label = stringResource(R.string.high),
                icon = painterResource(id = R.drawable.icon_flag),
                activeBackgroundColor = colors.pinkAccent,
                backgroundColor = Theme.color.surface,
            )
            TudeeChip(
                label = stringResource(R.string.medium),
                icon = painterResource(id = R.drawable.icon_alert),
                activeBackgroundColor = colors.yellowAccent,
                backgroundColor = Theme.color.surface,
            )
            TudeeChip(
                label = stringResource(R.string.low),
                icon = painterResource(id = R.drawable.icon_trade_down),
                activeBackgroundColor = colors.greenAccent,
                backgroundColor = Theme.color.surface,
            )
        }
    }
}
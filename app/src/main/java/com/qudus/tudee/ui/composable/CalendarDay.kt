package com.qudus.tudee.ui.composable

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.ui.designSystem.theme.TudeeTheme

@Composable
fun CalendarDay(
    date: String,
    day: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
) {
    val background: Modifier =
        if (isSelected) modifier.background(brush = Theme.color.primaryGradient)
        else modifier.background(
            color = Theme.color.surface
        )
    val dayColor by animateColorAsState(
        if (isSelected) Theme.color.onPrimary else Theme.color.body
    )
    val monthColor by animateColorAsState(
        if (isSelected) Theme.color.onPrimaryCaption else Theme.color.hint
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(2.dp),
        modifier = modifier
            .width(56.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
            .then(background)
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Text(
            text = date,
            style = Theme.textStyle.title.medium,
            color = dayColor
        )
        Text(
            text = day,
            style = Theme.textStyle.body.small,
            color = monthColor

        )
    }
}

@PreviewLightDark
@Composable
fun CalendarDayLightSelectedPreview() {
    var isSelected by remember { mutableStateOf(true) }
    TudeeTheme(isDarkTheme = false) {
        CalendarDay(
            date = "15",
            day = "Mon",
            isSelected = isSelected,
            onClick = {
                isSelected = !isSelected
            }
        )
    }
}

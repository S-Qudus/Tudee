package com.qudus.tudee.designSystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.qudus.tudee.R
import com.qudus.tudee.designSystem.theme.TudeeTheme

@Composable
fun TudeeChip(
    label: String,
    modifier: Modifier = Modifier,
    labelColor: Color = TudeeTheme.color.onPrimary,
    icon: Painter,
    backgroundColor: Color = TudeeTheme.color.primary,
    labelSize: TextUnit = TudeeTheme.textStyle.label.small.fontSize,
    iconSize: Dp = 12.dp
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(100.dp))
            .background(backgroundColor)
            .padding(horizontal = 8.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
        Icon(
            painter = icon,
            contentDescription = "label",
            tint = labelColor,
            modifier = Modifier.size(iconSize)
        )
            Text(
                text = label,
                color = labelColor,
                style = TudeeTheme.textStyle.label.small.copy(fontSize = labelSize)
            )

        }
    }

@Preview(showBackground = true)
@Composable
fun PriorityChipsPreview() {
    val colors = TudeeTheme.color
    val textColor = colors.onPrimary
    TudeeTheme(true) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            TudeeChip(
                label = "High",
                icon = painterResource(id = R.drawable.icon_priority_high),
                backgroundColor = colors.pinkAccent,
                labelColor = textColor
            )
            TudeeChip(
                label = "Medium",
                icon = painterResource(id = R.drawable.icon_priority_medium),
                backgroundColor = colors.yellowAccent,
                labelColor = textColor
            )
            TudeeChip(
                label = "Low",
                icon = painterResource(id = R.drawable.icon_priority_low),
                backgroundColor = colors.greenAccent,
                labelColor = textColor
            )
        }
    }
}
package com.qudus.tudee.ui.designSystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.qudus.tudee.ui.designSystem.theme.Theme

@Composable
fun StatusCardItem(
    count: Int,
    label: String,
    color: Color,
    icon: Painter,
    modifier: Modifier = Modifier,
    backgroundColor: Color = color.copy(alpha = 0.15f),
    iconBackgroundColor: Color = Color.White.copy(alpha = 0.12f),
    iconBorderColor: Color = Color.White.copy(alpha = 0.12f),
    contentPadding: PaddingValues = PaddingValues(16.dp),
    iconSize: Dp = 24.dp,
    iconContainerSize: Dp = 40.dp,
    iconContainerPadding: Dp = 8.dp
) {
    Box(
        modifier = modifier
            .background(backgroundColor, shape = RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(20.dp))
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(x = 40.dp, y = (-41).dp)
                .alpha(0.16f)
        ) {
            Box(
                modifier = Modifier
                    .size(78.dp)
                    .border(
                        width = 1.dp,
                        color = Color.White.copy(alpha = 0.87f),
                        shape = RoundedCornerShape(200.dp)
                    )
            )
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(45.09.dp)
                    .background(
                        color = Color.White.copy(alpha = 0.87f),
                        shape = CircleShape
                    )
            )
            Box(
                modifier = Modifier
                    .padding(bottom = 3.9.dp, start = 11.7.dp)
                    .align(Alignment.BottomStart)
                    .size(7.8.dp)
                    .background(
                        color = Color.White.copy(alpha = 0.87f),
                        shape = CircleShape
                    )
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Icon Container
            Box(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = iconBorderColor,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .size(iconContainerSize)
                    .background(
                        color = iconBackgroundColor,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(iconContainerPadding),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = icon,
                    contentDescription = null,
                    modifier = Modifier.size(iconSize)
                )
            }

            // Count Text
            Text(
                text = "$count",
                style = Theme.textStyle.headline.medium,
                color = Theme.color.onPrimary,
                fontWeight = FontWeight.Bold
            )

            // Label Text
            Text(
                text = label,
                style = Theme.textStyle.label.small,
                color = Theme.color.onPrimaryCaption
            )
        }
    }
}

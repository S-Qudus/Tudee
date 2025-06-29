package com.qudus.tudee.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.qudus.tudee.R
import com.qudus.tudee.ui.designSystem.theme.Theme

@Composable
fun TaskCountByStatusCard(
    count: Int,
    label: String,
    color: Color,
    icon: Painter,
    modifier: Modifier = Modifier,
    backgroundColor: Color = color.copy(alpha = 0.15f),
    iconBackgroundColor: Color = Theme.color.surfaceHigh.copy(alpha = 0.24f),
    iconBorderColor: Color = Theme.color.stroke,
    contentPadding: PaddingValues = PaddingValues(12.dp),
    iconSize: Dp = 24.dp,
    iconContainerSize: Dp = 40.dp,
    iconContainerPadding: Dp = 8.dp
) {
    Box(
        modifier = modifier
            .background(backgroundColor, shape = RoundedCornerShape(20.dp))
    ) {
        Image(
            painter = painterResource(id = R.drawable.image_overview_card_background),
            contentDescription = null,
            modifier = Modifier
                .size(39.dp, 38.dp)
                .align(Alignment.TopEnd)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
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

            Text(
                text = "$count",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = color
            )

            Text(
                text = label,
                style = Theme.textStyle.label.small,
                color = color
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskCountByStatusCardPreview() {
    TaskCountByStatusCard(
        count = 5,
        label = stringResource(id = R.string.done),
        color = Theme.color.greenAccent,
        icon = painterResource(id = R.drawable.icon_overview_card_completed),
        modifier = Modifier.width(120.dp)
    )
} 
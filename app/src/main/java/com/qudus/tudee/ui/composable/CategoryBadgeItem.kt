package com.qudus.tudee.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.qudus.tudee.R
import com.qudus.tudee.ui.designSystem.theme.Theme

@Composable
fun CategoryBadgeItem(
    title: String,
    iconPainter: Painter,
    modifier: Modifier = Modifier,
    isClickable: Boolean = false,
    onItemClick: () -> Unit = {},
    badge: @Composable BoxScope.() -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box {
            CategoryIcon(
                modifier = Modifier.size(78.dp),
                iconPainter = iconPainter,
                title = title,
                isClickable = isClickable,
                onItemClick = onItemClick,
            )
            badge()
        }

        Text(
            text = title,
            style = Theme.textStyle.label.small,
            color = Theme.color.body
        )
    }
}

@Composable
fun CategoryIcon(
    modifier: Modifier = Modifier,
    iconPainter: Painter,
    title: String,
    isClickable: Boolean,
    onItemClick: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(color = Theme.color.surfaceHigh, shape = CircleShape)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(color = Theme.color.primaryVariant),
                enabled = isClickable,
                onClick = onItemClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = iconPainter,
            contentDescription = title,
            contentScale = ContentScale.Crop
        )
    }
}

@Preview(showSystemUi = false, showBackground = true)
@Composable
private fun CategoryTextBadgeItemPreview() {
    CategoryBadgeItem(
        title = "Education",
        iconPainter = painterResource(R.drawable.icon_book_open),
    ) {
        TudeeTextBadge(
            modifier = Modifier.align(Alignment.TopEnd),
            badgeNumber = "16",
            contentColor = Theme.color.hint,
            containerColor = Theme.color.surfaceLow
        )
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
private fun CategoryCheckBadgeItemPreview() {
    CategoryBadgeItem(
        title = "Education",
        iconPainter = painterResource(R.drawable.icon_book_open),
        isClickable = true,
        onItemClick = {}
    ) {
        TudeeCheckBadge(modifier = Modifier.align(Alignment.TopEnd), visible = true)
    }
}
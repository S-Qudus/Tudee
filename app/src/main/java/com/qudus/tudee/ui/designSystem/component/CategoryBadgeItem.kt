package com.qudus.tudee.ui.designSystem.component

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
    id: Long,
    title: String,
    modifier: Modifier = Modifier,
    isClickable: Boolean = false,
    onItemClick: (id: Long) -> Unit = {},
    imagePainter: Painter,
    badge: @Composable BoxScope.() -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Theme.dimension.spacing8)
    ) {
        Box {
            CategoryIcon(
                isClickable = isClickable,
                onItemClick = onItemClick,
                id = id,
                imagePainter = imagePainter,
                title = title
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
    title: String,
    imagePainter: Painter,
    modifier: Modifier = Modifier,
    id: Long = 0,
    isClickable: Boolean = false,
    onItemClick: (Long) -> Unit = {},
) {
    Box(
        modifier = modifier
            .size(78.dp)
            .clip(CircleShape)
            .background(color = Theme.color.surfaceHigh, shape = CircleShape)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(color = Theme.color.primaryVariant),
                enabled = isClickable,
                onClick = { onItemClick(id) }
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = imagePainter,
            contentDescription = title,
            contentScale = ContentScale.Crop
        )
    }
}

@Preview(showSystemUi = false, showBackground = true)
@Composable
private fun CategoryTextBadgeItemPreview() {
    CategoryBadgeItem(
        id = 0,
        title = "Education",
        imagePainter = painterResource(R.drawable.icon_book_open)
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
        id = 0,
        title = "Education",
        isClickable = true,
        imagePainter = painterResource(R.drawable.icon_book_open),
        onItemClick = {}
    ) {
        TudeeCheckBadge(modifier = Modifier.align(Alignment.TopEnd), visible = true)
    }
}
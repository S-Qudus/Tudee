package com.qudus.tudee.ui.composable.confirm

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.qudus.tudee.R
import com.qudus.tudee.ui.composable.TudeeTextButton
import com.qudus.tudee.ui.designSystem.component.TudeeBottomSheet
import com.qudus.tudee.ui.designSystem.theme.Theme


@Composable
fun ConfirmDelete(
    title: String,
    description: String,
    image: Painter,
    onCancel: () -> Unit,
    onConfirmDelete: () -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = Theme.dimension.spacing24, topEnd = Theme.dimension.spacing24))
            .background(Theme.color.surface),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            style = Theme.textStyle.title.large,
            color = Theme.color.title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Theme.dimension.spacing16)
        )
        Text(
            text = description,
            style = Theme.textStyle.body.large,
            color = Theme.color.body,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Theme.dimension.spacing16, vertical = Theme.dimension.spacing12)
        )
        Image(
            painter = image,
            contentDescription = "Image",
            modifier = Modifier
                .width(107.dp)
                .height(100.dp)
        )
        Spacer(modifier = Modifier.height(Theme.dimension.spacing24))


        val dropTopShadow = Modifier.drawBehind {
            val shadowColor = Color.Black.copy(alpha = 0.03f)
            val shadowHeight = 20.dp.toPx()
            val gradient = Brush.verticalGradient(
                colors = listOf(
                    Color.Transparent,
                    shadowColor
                ),
                startY = -shadowHeight,
                endY = 0f
            )

            drawRect(
                brush = gradient,
                topLeft = Offset(0f, -shadowHeight),
                size = Size(size.width, shadowHeight)
            )
        }
        Column(
            modifier = Modifier
                .then(dropTopShadow)
                .padding(top = Theme.dimension.spacing12, bottom = Theme.dimension.spacing12)

        ) {
            TudeeTextButton(
                modifier = Modifier
                    .height(Theme.dimension.spacing56)
                    .fillMaxWidth()
                    .padding(horizontal = Theme.dimension.spacing16)
                    .clip(CircleShape)
                    .background(Theme.color.errorVariant),
                onClickTextButton = onConfirmDelete,
                isEnabled = true,
                isLoading = isLoading,
                isNegativeButton = true,

                ) {
                Text(
                    text = stringResource(R.string.delete),
                    style = Theme.textStyle.label.large,
                    color = Theme.color.error,
                )
            }
            Spacer(modifier = Modifier.height(Theme.dimension.spacing12))
            TudeeTextButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Theme.dimension.spacing56)
                    .padding(horizontal = Theme.dimension.spacing16)
                    .clip(CircleShape)
                    .background(Theme.color.onPrimary)
                    .border(
                        1.dp,
                        shape = CircleShape,
                        color = Theme.color.stroke,
                    ),
                onClickTextButton = onCancel,
                isEnabled = true,
                isLoading = false,
                isNegativeButton = false,
            ) {
                Text(
                    text = stringResource(R.string.cancel),
                    style = Theme.textStyle.label.large,
                    color = Theme.color.primary,
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ConfirmDeletePreview() {
    TudeeBottomSheet(isSheetOpen = true, onDismissRequest = {}) {
        ConfirmDelete(
            title = stringResource(R.string.are_you_sure),
            description = stringResource(R.string.this_action_cannot_be_undone),
            image = painterResource(id = R.mipmap.image_confirm_delete),
            onCancel = {},
            onConfirmDelete = {}
        )

    }

}
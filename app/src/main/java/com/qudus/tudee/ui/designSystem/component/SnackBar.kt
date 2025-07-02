package com.qudus.tudee.ui.designSystem.component

import android.graphics.BlurMaskFilter
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.qudus.tudee.R
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.ui.designSystem.theme.TudeeTheme


@Composable
fun SnackBar(
    state: SnackBarState,
    message: String,
    iconColor: Color,
    background: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .dropShadow(blur = 16.dp, offsetY = 4.dp, color = Color(0x1F000000))
            .background(Theme.color.surfaceHigh, shape = RoundedCornerShape(16.dp))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(background, shape = RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = when (state) {
                    SnackBarState.ERROR -> painterResource(R.drawable.icon_information_diamond)
                    SnackBarState.SUCCESS -> painterResource(id = R.drawable.icon_checkmark_badge)
                },
                contentDescription = "snack bar",
                alignment = Alignment.Center,
                colorFilter = ColorFilter.tint(iconColor)
            )
        }
        Text(
            message,
            style = Theme.textStyle.body.medium,
            color = Theme.color.body,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(start = 12.dp),
        )
    }
}

enum class SnackBarState {
    ERROR,
    SUCCESS
}


@Preview
@Composable
private fun SnackBarComponentPreview() {
    TudeeTheme(isDarkTheme = false) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .background(Color.White)
        ) {
            SnackBar(
                state = SnackBarState.ERROR,
                message = "Some error happened.",
                iconColor = Theme.color.error,
                background = Theme.color.errorVariant
            )
            Spacer(modifier = Modifier.height(20.dp))
            SnackBar(
                state = SnackBarState.SUCCESS,
                message = "Successfully.",
                iconColor = Theme.color.greenAccent,
                background = Theme.color.greenVariant,
            )
        }
    }
}

// This is a comment for people who make a review of the code
// I know that we shouldn't put this extension here; I will move it later, don't worry.
@Stable
fun Modifier.dropShadow(
    color: Color = Color.Black.copy(0.1f),
    blur: Dp = 4.dp,
    offsetY: Dp = 4.dp,
    offsetX: Dp = 0.dp,
    spread: Dp = 0.dp,
    modifier: Modifier = Modifier
) = this.then(
    modifier.drawBehind {
        this.drawIntoCanvas {
            val paint = Paint()
            val frameworkPaint = paint.asFrameworkPaint()
            val spreadPixel = spread.toPx()
            val leftPixel = (0f - spreadPixel) + offsetX.toPx()
            val topPixel = (0f - spreadPixel) + offsetY.toPx()
            val rightPixel = (this.size.width + spreadPixel)
            val bottomPixel = (this.size.height + spreadPixel)

            if (blur != 0.dp) {
                frameworkPaint.maskFilter =
                    (BlurMaskFilter(blur.toPx(), BlurMaskFilter.Blur.NORMAL))
            }

            frameworkPaint.color = color.toArgb()
            it.drawRoundRect(
                left = leftPixel,
                top = topPixel,
                right = rightPixel,
                bottom = bottomPixel,
                radiusX = blur.toPx(),
                radiusY = blur.toPx(),
                paint
            )
        }
    }
)
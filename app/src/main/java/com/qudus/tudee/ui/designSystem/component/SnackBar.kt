package com.qudus.tudee.designSystem.component

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.qudus.tudee.R
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.ui.designSystem.theme.TudeeTheme
import com.qudus.tudee.ui.util.extension.dropShadow


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
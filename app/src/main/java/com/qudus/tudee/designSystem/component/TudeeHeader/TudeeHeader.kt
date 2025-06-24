package com.qudus.tudee.designSystem.component.TudeeHeader

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.qudus.tudee.R
import com.qudus.tudee.designSystem.component.buttons.ThemeSwitchButton.ThemeSwitchButton
import com.qudus.tudee.designSystem.theme.Theme

@Composable
fun TudeeHeader(
    modifier: Modifier = Modifier,
    endContent: @Composable () -> Unit = {},
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Theme.color.primary)
            .padding(vertical = Theme.dimension.regular, horizontal = Theme.dimension.medium),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Theme.dimension.small)
        ) {
            Image(
                painter = painterResource(R.drawable.image_tudee),
                contentDescription = null,
                contentScale = ContentScale.Inside,
                modifier = Modifier.size(48.dp)
            )
            Column {
                Text(
                    text = "Tudee",
                    style = Theme.textStyle.title.large,
                    color = Theme.color.onPrimary
                )
                Text(
                    text = "Your cute Helper for Every Task",
                    style = Theme.textStyle.label.small,
                    color = Theme.color.onPrimaryCaption
                )
            }
        }
        endContent()
    }
}

@Preview(showBackground = true, widthDp = 360)
@Composable
private fun TudeeHeaderPreview() {
    var isDarkMode by remember { mutableStateOf(false) }
    TudeeHeader(
        endContent = {
            ThemeSwitchButton(
                isDarkMode = isDarkMode,
                onCheckedChange = { isDarkMode = it },
                modifier = Modifier.padding(8.dp)
            )
        }
    )
}

@Preview(showBackground = true, widthDp = 360)
@Composable
private fun TudeeHeaderDarkPreview() {
    var isDarkMode by remember { mutableStateOf(true) }
    TudeeHeader(
        endContent = {
            ThemeSwitchButton(
                isDarkMode = isDarkMode,
                onCheckedChange = { isDarkMode = it },
                modifier = Modifier.padding(8.dp)
            )
        }
    )
}
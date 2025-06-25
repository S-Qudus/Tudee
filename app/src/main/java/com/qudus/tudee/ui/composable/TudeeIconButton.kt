package com.qudus.tudee.ui.composable

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.qudus.tudee.R
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.ui.designSystem.theme.TudeeTheme

@Composable
fun TudeeIconButton(
    onClickIconButton: () -> Unit,
    isEnabled: Boolean,
    isLoading: Boolean,
    modifier: Modifier = Modifier
) {
    val buttonBackgroundColor by animateColorAsState(
        targetValue = if (isEnabled) Theme.color.primary else Theme.color.disable
    )
    val iconColor by animateColorAsState(
        targetValue = if (isEnabled) Theme.color.onPrimary else Theme.color.stroke
    )

    FloatingActionButton(
        onClick = onClickIconButton,
        modifier = modifier.size(64.dp),
        containerColor = buttonBackgroundColor,
        shape = CircleShape,
        elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 0.dp)

    ) {
        AnimatedContent(isLoading) { loading ->
            if (loading) {
                TudeeLoadingIcon(tint = iconColor)
            } else {
                Icon(
                    painter = painterResource(R.drawable.icon_download),
                    contentDescription = "download icon",
                    tint = iconColor
                )
            }
        }
    }

}

@Preview(showSystemUi = true, showBackground = false)
@Composable
private fun TudeeIconButtonPrev() {

    TudeeTheme(isDarkTheme = false) {
        TudeeIconButton(
            modifier = Modifier.padding(top = 128.dp),
            onClickIconButton = {},
            isEnabled = false,
            isLoading = false
        )
    }
}
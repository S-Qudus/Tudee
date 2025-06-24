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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.qudus.tudee.R
import com.qudus.tudee.designSystem.theme.Theme
import com.qudus.tudee.designSystem.theme.TudeeTheme

@Composable
fun TudeeIconButton(
    onClickIconButton: () -> Unit,
    isEnabled: Boolean,
    isLoading: Boolean,
    modifier: Modifier = Modifier
) {
    val isEnableIconButtonColor by animateColorAsState(
        targetValue = if (isEnabled) Theme.color.primary else Theme.color.disable
    )
    val isEnableButtonForIconColor by animateColorAsState(
        targetValue = if (isEnabled) Theme.color.onPrimary else Theme.color.stroke
    )

    FloatingActionButton(
        onClick = onClickIconButton,
        modifier = modifier.size(64.dp),
        containerColor = isEnableIconButtonColor,
        shape = CircleShape,
        elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 0.dp)

    ) {
        AnimatedContent(isLoading) {
            when (it) {
                true -> {
                    TudeeLoadingIcon(
                        tint = isEnableButtonForIconColor
                    )

                }

                false -> {
                    Icon(
                        painter = painterResource(R.drawable.icon_download),
                        contentDescription = stringResource(R.string.download_icon),
                        tint = isEnableButtonForIconColor
                    )
                }
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
package com.qudus.tudee.ui.designSystem.component.buttons

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.qudus.tudee.ui.designSystem.component.TudeeLoadingIcon
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.ui.designSystem.theme.TudeeTheme

@Composable
fun TudeeTextButton(
    onClickTextButton: () -> Unit,
    isEnabled: Boolean,
    isLoading: Boolean,
    modifier: Modifier = Modifier,
    isNegativeButton: Boolean = false,
    content: @Composable () -> Unit
) {
    TextButton(
        onClick = onClickTextButton,
        modifier = modifier,
        enabled = isEnabled
    ) {
        content()

        AnimatedVisibility(isLoading) {
            TudeeLoadingIcon(
                tint = if (isNegativeButton) Theme.color.error
                else if (isEnabled) Theme.color.primary
                else Theme.color.onPrimary,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

@Preview(showBackground = false, showSystemUi = true)
@Composable
private fun TudeeTextButtonPrev() {
    TudeeTheme(false) {
        TudeeTextButton(
            modifier = Modifier.padding(top = 128.dp),
            onClickTextButton = {},
            isEnabled = true,
            isLoading = true,
            isNegativeButton = false
        ) {
            Text(
                text = "Submit",
                style = Theme.textStyle.label.large,
                color = Theme.color.primary
            )
        }
    }
}

@Preview(showBackground = false, showSystemUi = true)
@Composable
private fun TudeeTextButtonPreview() {
    TudeeTheme(false) {
        TudeeTextButton(
            modifier = Modifier.padding(top = 128.dp),
            onClickTextButton = {},
            isEnabled = true,
            isLoading = false,
            isNegativeButton = true
        ) {
            Text(
                text = "Delete",
                style = Theme.textStyle.label.large,
                color = Theme.color.error
            )
        }
    }
}

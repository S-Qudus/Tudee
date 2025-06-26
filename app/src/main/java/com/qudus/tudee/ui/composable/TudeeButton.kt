package com.qudus.tudee.ui.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.ui.designSystem.theme.TudeeTheme

@Composable
fun TudeeButton(
    onClick: () -> Unit,
    isLoading: Boolean,
    isEnabled: Boolean,
    modifier: Modifier = Modifier,
    hasBorder: Boolean = false,
    hasBackGroundColor: Boolean = !hasBorder,
    isNegativeButton: Boolean = false,
    content: @Composable RowScope.() -> Unit
) {
    val buttonBackgroundColor by animateColorAsState(
        targetValue = if (isEnabled) Theme.color.primary else Theme.color.disable
    )
    val backgroundColorWithBasicButton =
        if (hasBackGroundColor) buttonBackgroundColor else Color.Transparent

    val border = if (hasBorder) BorderStroke(1.dp, Theme.color.stroke) else null

    Button(
        onClick = onClick,
        modifier = modifier.height(56.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isNegativeButton) Theme.color.errorVariant else backgroundColorWithBasicButton,
            disabledContainerColor = if (hasBackGroundColor) buttonBackgroundColor else Color.Transparent
        ),
        enabled = isEnabled,
        border = border
    ) {
        content()

        AnimatedVisibility(isLoading) {
            TudeeLoadingIcon(
                tint = if (isNegativeButton) Theme.color.error else if (isEnabled) Theme.color.primary else Theme.color.onPrimary,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun SubmitButtonPrev() {
    TudeeTheme(isDarkTheme = false) {
        TudeeButton(
            onClick = {},
            isLoading = true,
            isEnabled = true,
            hasBorder = false,
            isNegativeButton = true,
            modifier = Modifier.padding(top = 128.dp),
            content = {
                androidx.compose.material3.Text(
                    text = "Submit",
                    style = Theme.textStyle.label.large,
                    color = Theme.color.error
                )
            }
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun DeleteButtonPrev() {
    TudeeTheme(isDarkTheme = false) {
        TudeeButton(
            onClick = {},
            isLoading = false,
            isEnabled = true,
            hasBorder = false,
            hasBackGroundColor = false,
            isNegativeButton = true,
            modifier = Modifier.padding(top = 128.dp),
            content = {
                androidx.compose.material3.Text(
                    text = "Delete",
                    style = Theme.textStyle.label.large,
                    color = Theme.color.error
                )
            }
        )
    }
}

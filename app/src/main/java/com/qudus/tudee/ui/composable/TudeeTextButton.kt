package com.qudus.tudee.ui.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.qudus.tudee.designSystem.theme.Theme
import com.qudus.tudee.designSystem.theme.TudeeTheme

@Composable
fun TudeeTextButton(
    onClickTextButton: () -> Unit,
    isEnabled: Boolean,
    isLoading: Boolean,
    title: String,
    modifier: Modifier = Modifier,
    isNegativeButton: Boolean = false
) {
    val isEnableButtonForTextColor by animateColorAsState(
        targetValue = if (isEnabled) Theme.color.onPrimary else Theme.color.stroke
    )
    val isEnableTextButtonForTextColor by animateColorAsState(
        targetValue = if (isEnabled) Theme.color.primary else Theme.color.stroke
    )

    val textColorWithoutNegativeButton =
        if (isEnabled) isEnableTextButtonForTextColor else isEnableButtonForTextColor

    TextButton(
        onClick = onClickTextButton,
        modifier = modifier,
        enabled = isEnabled,

        ) {
        Text(
            text = title,
            style = Theme.textStyle.label.large,
            color = if (isNegativeButton) Theme.color.error else textColorWithoutNegativeButton

        )
        AnimatedVisibility(isLoading) {
            Spacer(modifier = Modifier.padding(end = 8.dp))
        }
        AnimatedVisibility(isLoading) {
            when (isNegativeButton) {
                true -> {
                    TudeeLoadingIcon(
                        tint = Theme.color.error
                    )
                }

                false -> {
                    TudeeLoadingIcon(
                        tint = if (isEnabled) Theme.color.primary else Theme.color.onPrimary
                    )
                }
            }

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
            title = "submit",
            isLoading = true,
            isNegativeButton = true
        )
    }
}
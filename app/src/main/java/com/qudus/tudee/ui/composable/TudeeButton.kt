package com.qudus.tudee.ui.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.qudus.tudee.R
import com.qudus.tudee.designSystem.theme.Theme
import com.qudus.tudee.designSystem.theme.TudeeTheme

@Composable
fun TudeeButton(
    onClick: () -> Unit,
    isLoading: Boolean,
    isEnabled: Boolean,
    title: String,
    modifier: Modifier = Modifier,
    hasBorder: Boolean = false,
    hasBackGroundColor: Boolean = !hasBorder,
    isNegativeButton: Boolean = false
) {

    val isEnableButtonColor by animateColorAsState(
        targetValue = if (isEnabled) Theme.color.primary else Theme.color.disable
    )
    val isEnableButtonForTextColor by animateColorAsState(
        targetValue = if (isEnabled) Theme.color.onPrimary else Theme.color.stroke
    )
    val isEnableButtonForTextColorWhenHasIcon by animateColorAsState(
        targetValue = if (isEnabled) Theme.color.primary else Theme.color.stroke
    )

    val backgroundColorWithBasicButton =
        if (hasBackGroundColor) isEnableButtonColor else Color.Transparent
    val textColorWithoutNegativeButton =
        if (hasBorder) isEnableButtonForTextColorWhenHasIcon else isEnableButtonForTextColor

    Button(
        onClick = onClick,
        modifier = modifier.height(56.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isNegativeButton) Theme.color.errorVariant else backgroundColorWithBasicButton,
            disabledContainerColor = if (hasBackGroundColor) isEnableButtonColor else Color.Transparent
        ),
        enabled = isEnabled,
        border = if (hasBorder) BorderStroke(width = 1.dp, color = Theme.color.stroke) else null,

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
                        tint = if (hasBorder) Theme.color.primary else Theme.color.onPrimary
                    )
                }
            }

        }

    }
}

@Preview(showSystemUi = true, showBackground = false)
@Composable
private fun SubmitButtonPrev() {
    TudeeTheme(isDarkTheme = false) {
        TudeeButton(
            onClick = {},
            isLoading = true,
            title = stringResource(R.string.submit),
            isEnabled = true,
            hasBorder = false,
            isNegativeButton = true,
            modifier = Modifier.padding(top = 128.dp)
        )
    }


}
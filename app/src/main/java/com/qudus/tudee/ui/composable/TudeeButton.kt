package com.qudus.tudee.ui.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
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
    title: String,
    modifier: Modifier = Modifier,
    hasBorder: Boolean = false,
    hasBackGroundColor: Boolean = !hasBorder,
    isNegativeButton: Boolean = false
) {

    val buttonBackgroundColor by animateColorAsState(
        targetValue = if (isEnabled) Theme.color.primary else Theme.color.disable
    )
    val textColor by animateColorAsState(
        targetValue = if (isEnabled) Theme.color.onPrimary else Theme.color.stroke
    )
    val textColorWhenHasIcon by animateColorAsState(
        targetValue = if (isEnabled) Theme.color.primary else Theme.color.stroke
    )

    val backgroundColorWithBasicButton =
        if (hasBackGroundColor) buttonBackgroundColor else Color.Transparent
    val textColorWithoutNegativeButton =
        if (hasBorder) textColorWhenHasIcon else textColor

    Button(
        onClick = onClick,
        modifier = modifier.height(56.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isNegativeButton) Theme.color.errorVariant else backgroundColorWithBasicButton,
            disabledContainerColor = if (hasBackGroundColor) buttonBackgroundColor else Color.Transparent
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
            if (isNegativeButton) TudeeLoadingIcon(
                tint = Theme.color.error,
                modifier = Modifier.padding(start = 8.dp)
            ) else TudeeLoadingIcon(
                tint = if (isEnabled) Theme.color.primary else Theme.color.onPrimary,
                modifier = Modifier.padding(start = 8.dp)
            )
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
            title = "submit",
            isEnabled = true,
            hasBorder = false,
            isNegativeButton = true,
            modifier = Modifier.padding(top = 128.dp)
        )
    }


}
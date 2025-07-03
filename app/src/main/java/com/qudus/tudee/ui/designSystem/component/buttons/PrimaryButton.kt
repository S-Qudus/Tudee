package com.qudus.tudee.ui.designSystem.component.buttons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.qudus.tudee.ui.designSystem.theme.Theme

@Composable
fun PrimaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    isEnabled: Boolean = true,
    content: @Composable () -> Unit
) {
    TudeeButton(
        onClick = onClick,
        
        isEnabled = isEnabled,
        isLoading = isLoading,
        hasBorder = false,
        hasBackGroundColor = true,
        isNegativeButton = false,
        modifier = modifier,
        content = { content()  }
    )
}
@Preview
@Composable
private fun SaveButtonPreview() {
    PrimaryButton(
        modifier = Modifier.fillMaxWidth(),
        onClick = { }
    ) {
        Text(
            text = "Save",
            style = Theme.textStyle.label.large,
           color = Theme.color.onPrimary
        )
    }
}

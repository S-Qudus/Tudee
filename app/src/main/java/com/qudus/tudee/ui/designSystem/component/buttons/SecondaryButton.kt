package com.qudus.tudee.ui.designSystem.component.buttons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.qudus.tudee.ui.designSystem.theme.Theme

@Composable
fun SecondaryButton(
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
        hasBorder = true,
        hasBackGroundColor = false,
        isNegativeButton = false,
        modifier = modifier,
        content = {  content() }
    )
}
@Preview(showBackground = true)
@Composable
fun CancelButton() {
    SecondaryButton(
        modifier = Modifier.fillMaxWidth(),
        onClick = {}
    ) {
        Text(
            text = "Cancel",
            style = Theme.textStyle.label.large,
            color = Theme.color.primary
        )
    }
}

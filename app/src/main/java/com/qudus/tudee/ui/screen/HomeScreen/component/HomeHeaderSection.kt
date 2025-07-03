package com.qudus.tudee.ui.screen.HomeScreen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.qudus.tudee.ui.designSystem.component.buttons.ThemeSwitchButton.ThemeSwitchButton
import com.qudus.tudee.ui.designSystem.component.TudeeHeader.TudeeHeader
import com.qudus.tudee.ui.designSystem.theme.Theme

@Composable
fun HomeHeaderSection(
    isDarkTheme: Boolean,
    onThemeToggle: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(Theme.color.primary),
        contentAlignment = Alignment.Center
    ) {
        TudeeHeader(
            modifier = Modifier.fillMaxWidth(),
            endContent = {
                ThemeSwitchButton(
                    isDarkMode = isDarkTheme,
                    onCheckedChange = onThemeToggle
                )
            }
        )
    }
}

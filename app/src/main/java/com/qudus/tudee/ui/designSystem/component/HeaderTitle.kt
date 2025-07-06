package com.qudus.tudee.ui.designSystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.qudus.tudee.ui.designSystem.theme.Theme

@Composable
fun HeaderTitle(title: String, modifier: Modifier = Modifier) {
    Text(
        title,
        style = Theme.textStyle.title.large,
        color = Theme.color.title,
        modifier = modifier
            .fillMaxWidth()
            .background(Theme.color.surfaceHigh)
            .statusBarsPadding()
            .padding(horizontal = Theme.dimension.spacing16, vertical = Theme.dimension.spacing20),
    )
}
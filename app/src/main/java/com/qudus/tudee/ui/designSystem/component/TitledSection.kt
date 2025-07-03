package com.qudus.tudee.ui.designSystem.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.qudus.tudee.ui.designSystem.theme.Theme

@Composable
fun TitledSection(
    title: String,
    modifier: Modifier = Modifier,
    titleStyle: TextStyle = Theme.textStyle.title.medium,
    titleColor: Color = Theme.color.title,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            style = titleStyle,
            color = titleColor
        )

        content()
    }
}
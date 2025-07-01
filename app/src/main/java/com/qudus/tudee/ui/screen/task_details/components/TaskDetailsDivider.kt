package com.qudus.tudee.ui.screen.task_details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.qudus.tudee.ui.designSystem.theme.Theme

@Composable
fun TaskDetailsDivider() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Theme.dimension.spacing12)
            .height(1.dp)
            .background(Theme.color.stroke)
    )
}
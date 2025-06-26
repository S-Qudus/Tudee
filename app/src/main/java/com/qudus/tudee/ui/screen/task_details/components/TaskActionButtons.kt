package com.qudus.tudee.ui.screen.task_details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.qudus.tudee.ui.composable.TudeeButton

@Composable
fun TaskActionButtons() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.padding(vertical = 24.dp)
    ) {
        TudeeButton(
            onClick = {},
            isLoading = false,
            isEnabled = true,
            title = "Test"
        )
        TudeeButton(
            onClick = {},
            isLoading = false,
            isEnabled = true,
            title = "Test",
            modifier = Modifier.fillMaxWidth()
        )
    }
}
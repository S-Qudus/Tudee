package com.qudus.tudee.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.qudus.tudee.R
import com.qudus.tudee.designSystem.component.dropShadow
import com.qudus.tudee.ui.designSystem.theme.Theme

@Composable
fun NoTasks() {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .zIndex(1f)
                .height(75.dp)
                .dropShadow(
                    blur = 12.dp,
                    offsetY = 4.dp,
                    offsetX = 0.dp,
                    color = Color.Black.copy(alpha = 0.04f)
                )
                .background(
                    Theme.color.surfaceHigh,
                    shape = RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp,
                        bottomStart = 16.dp,
                        bottomEnd = 2.dp
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 12.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    "No tasks here!",
                    color = Theme.color.body,
                    style = Theme.textStyle.title.small
                )
                Text(
                    "Tap the + button to add your first one.",
                    color = Theme.color.hint,
                    style = Theme.textStyle.body.small
                )
            }

            Image(
                painter = painterResource(R.drawable.progress_indicator),
                contentDescription = "progress indicator",
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .offset(y = 53.dp, x = 33.dp),
            )
        }

        Box {
            Box(
                Modifier
                    .zIndex(0f)
                    .size(136.dp)
                    .background(
                        Theme.color.primary.copy(alpha = 0.16f),
                        shape = CircleShape,
                    )
            )

            Box(
                Modifier
                    .offset(x = (-15).dp, y = (-10).dp)
                    .size(144.dp)
                    .border(
                        1.dp,
                        Theme.color.primary.copy(alpha = 0.16f),
                        shape = CircleShape,
                    )
            )

            Box(
                modifier = Modifier
                    .offset(x = (25).dp, y = (-8).dp)
                    .size(15.dp)
                    .align(Alignment.BottomStart)
                    .background(
                        Theme.color.primary.copy(alpha = 0.16f),
                        shape = CircleShape
                    )
            )

            Image(
                painter = painterResource(R.drawable.image_tudee_no_task),
                contentDescription = "tudee no task",
                modifier = Modifier
                    .size(115.dp)
                    .align(Alignment.CenterEnd)
            )

        }

    }
}

@Preview
@Composable
fun NoTasksPreview() {
    NoTasks()
}

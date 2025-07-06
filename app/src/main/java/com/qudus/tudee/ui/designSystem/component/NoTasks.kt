package com.qudus.tudee.ui.designSystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.qudus.tudee.R
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.ui.util.extension.dropShadow
import com.qudus.tudee.ui.util.extension.mirrorRtl

@Composable
fun NoTasks(
    title: String,
    description: String,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Theme.color.surface),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = Theme.dimension.spacing6)
                .background(Theme.color.surface),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .zIndex(1f)
                    .dropShadow(
                        blur = 12.dp,
                        offsetY = 4.dp,
                        color = Color.Black.copy(alpha = 0.04f)
                    )
                    .background(
                        Theme.color.surfaceHigh,
                        RoundedCornerShape(16.dp)
                    )
            ) {
                Column(
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        title,
                        color = Theme.color.body,
                        style = Theme.textStyle.title.small
                    )
                    Text(
                        description,
                        color = Theme.color.hint,
                        style = Theme.textStyle.body.small
                    )
                }

                Image(
                    painter = painterResource(R.drawable.progress_indicator),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .offset(x = 33.dp, y = 53.dp)
                        .mirrorRtl()
                )
            }

            Box(
                modifier = Modifier.offset(y = 65.dp)
            ) {
                Box(
                    Modifier
                        .size(136.dp)
                        .background(
                            Theme.color.primary.copy(alpha = .16f),
                            CircleShape
                        )
                )
                Box(
                    Modifier
                        .offset(x = (-15).dp, y = (-10).dp)
                        .size(144.dp)
                        .border(
                            1.dp,
                            Theme.color.primary.copy(alpha = .16f),
                            CircleShape
                        )
                )
                Box(
                    Modifier
                        .offset(x = 15.dp, y = (-15).dp)
                        .size(15.dp)
                        .align(Alignment.BottomStart)
                        .background(
                            Theme.color.primary.copy(alpha = .16f),
                            CircleShape
                        )
                )
                Image(
                    painter = painterResource(R.drawable.image_tudee_no_task),
                    contentDescription = null,
                    modifier = Modifier
                        .size(115.dp)
                        .align(Alignment.Center)
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun NoTasksPreview() {
    NoTasks(
        modifier = Modifier,
        title = "No tasks here!",
        description = "Tap the + button to add your first one."
    )
}

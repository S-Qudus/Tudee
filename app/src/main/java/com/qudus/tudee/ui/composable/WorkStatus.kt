package com.qudus.tudee.ui.composable

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.qudus.tudee.R
import com.qudus.tudee.designSystem.theme.TudeeTheme

data class WorkStatus(
    val title: String,
    @DrawableRes val imageFace: Int,
    val subtitle: String,
    @DrawableRes val imageTudee: Int,
)

@Composable
fun WorkStatusC(doneTask: Int, totalTask: Int) {
    val items = listOf(
        WorkStatus(
            title = stringResource(R.string.stay_working),
            imageFace = R.drawable.neutral_face,
            subtitle = stringResource(
                R.string.tasks_completed_message,
                doneTask,
                totalTask
            ),
            imageTudee = R.drawable.image_happy_tudee,
        ),
        WorkStatus(
            title = stringResource(R.string.tadaa),
            imageFace = R.drawable.happy_face,
            subtitle = stringResource(R.string.amazing_message),
            imageTudee = R.drawable.image_shy_tudee,
        ),
        WorkStatus(
            title = stringResource(R.string.zero_progress),
            imageFace = R.drawable.angry_face,
            subtitle = stringResource(R.string.scrolling_warning),
            imageTudee = R.drawable.image_upset_tudee,
        ),
        WorkStatus(
            title = stringResource(R.string.nothing_on_list),
            imageFace = R.drawable.sad_face,
            subtitle = stringResource(R.string.fill_day_message),
            imageTudee = R.drawable.image_happy_tudee,
        ),
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        items(items) { item ->
            WorkStatusItem(
                title = item.title,
                imageFace = item.imageFace,
                subtitle = item.subtitle,
                imageTudee = item.imageTudee
            )
        }
    }
}

@Preview
@Composable
fun WorkStatusPreview() {
    TudeeTheme(isDarkTheme = true, content = {
        WorkStatusC(
            doneTask = 5,
            totalTask = 30,
        )
    })
}

package com.qudus.tudee.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.qudus.tudee.R
import com.qudus.tudee.designSystem.theme.TudeeTheme

@Composable
fun LoadWorkSummaries(completedTasks: Int, totalTasks: Int) {
    val stayWorkingTitle = stringResource(R.string.stay_working)
    var taskCompletedMessage = stringResource(
        R.string.tasks_completed_message,
        completedTasks,
        totalTasks
    )
    val updateWorking = remember(completedTasks, totalTasks) {
        WorkStatus(
            title = stayWorkingTitle,
            imageFace = R.drawable.image_neutral_face,
            subtitle = taskCompletedMessage,
            imageTudee = R.drawable.image_happy_tudee,
        )
    }
    val items = listOf(
        updateWorking,
        WorkStatus(
            title = stringResource(R.string.tadaa),
            imageFace = R.drawable.image_happy_face,
            subtitle = stringResource(R.string.amazing_message),
            imageTudee = R.drawable.image_shy_tudee,
        ),
        WorkStatus(
            title = stringResource(R.string.zero_progress),
            imageFace = R.drawable.image_angry_face,
            subtitle = stringResource(R.string.scrolling_warning),
            imageTudee = R.drawable.image_upset_tudee,
        ),
        WorkStatus(
            title = stringResource(R.string.nothing_on_list),
            imageFace = R.drawable.image_sad_face,
            subtitle = stringResource(R.string.fill_day_message),
            imageTudee = R.drawable.image_happy_tudee,
        ),
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        items(items) { workStatus ->
            WorkStatusItem(workStatus = workStatus)
        }
    }
}

@Preview
@Composable
fun WorkStatusPreview() {
    TudeeTheme(isDarkTheme = true, content = {
        LoadWorkSummaries(
            completedTasks = 5,
            totalTasks = 30,
        )
    })
}

package com.qudus.tudee.ui.designSystem.component

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
import com.qudus.tudee.ui.designSystem.theme.TudeeTheme
import com.qudus.tudee.ui.state.TudeeUiStatus
import com.qudus.tudee.ui.state.getTudeeStatus

@Composable
fun LoadWorkSummaries(completedTasks: Int, totalTasks: Int) {
    val stayWorkingTitle = stringResource(R.string.stay_working)
    var taskCompletedMessage = stringResource(
        R.string.tasks_completed_message_only,
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

@Composable
fun LoadWorkSummarySingle(completedTasks: Int, totalTasks: Int) {
    val status = getTudeeStatus(completedTasks, totalTasks)
    
    val title = when (status) {
        TudeeUiStatus.GOOD -> stringResource(R.string.good_status_message_title)
        TudeeUiStatus.OKAY -> stringResource(R.string.okay_status_message_title)
        TudeeUiStatus.POOR -> stringResource(R.string.poor_status_message_title)
        TudeeUiStatus.BAD -> stringResource(R.string.bad_status_message_title)
    }
    
    val subtitle = when (status) {
        TudeeUiStatus.GOOD -> stringResource(R.string.good_status_message)
        TudeeUiStatus.OKAY -> stringResource(R.string.okay_status_message, completedTasks, totalTasks)
        TudeeUiStatus.POOR -> stringResource(R.string.poor_status_message)
        TudeeUiStatus.BAD -> stringResource(R.string.bad_status_message)
    }
    
    val imageFace = when (status) {
        TudeeUiStatus.GOOD -> R.drawable.image_happy_face
        TudeeUiStatus.OKAY -> R.drawable.image_neutral_face
        TudeeUiStatus.POOR -> R.drawable.image_sad_face
        TudeeUiStatus.BAD -> R.drawable.image_angry_face
    }
    
    val imageTudee = when (status) {
        TudeeUiStatus.GOOD -> R.drawable.image_shy_tudee
        TudeeUiStatus.OKAY -> R.drawable.image_happy_tudee
        TudeeUiStatus.POOR -> R.drawable.image_happy_tudee
        TudeeUiStatus.BAD -> R.drawable.image_upset_tudee
    }
    
    val workStatus = remember(status, completedTasks, totalTasks) {
        WorkStatus(
            title = title,
            imageFace = imageFace,
            subtitle = subtitle,
            imageTudee = imageTudee,
        )
    }
    
    WorkStatusItem(workStatus = workStatus)
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

package com.qudus.tudee.ui.screen.HomeScreen.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.qudus.tudee.R
import com.qudus.tudee.ui.composable.StatusCardItem
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.ui.constant.OverviewCardConstants
import com.qudus.tudee.ui.constant.UserStatus
import com.qudus.tudee.ui.constant.calculateUserStatus
import com.qudus.tudee.ui.constant.getEmoji

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeOverviewCard(
    modifier: Modifier = Modifier,
    completedTasks: Int,
    totalTasks: Int,
    inProgressTasks: Int = 0,
    todayDate: String = "",
) {
    val userStatus = calculateUserStatus(completedTasks, totalTasks)
    
    Column(
        modifier = modifier
            .fillMaxWidth()
            .offset(y = OverviewCardConstants.CARD_OFFSET_Y.dp)
            .padding(
                start = Theme.dimension.small,
                end = Theme.dimension.small,
                top = Theme.dimension.medium,
                bottom = Theme.dimension.medium
            )
            .clip(RoundedCornerShape(Theme.dimension.medium))
            .background(Theme.color.surfaceHigh)
            .padding(Theme.dimension.medium)
    ) {
        DateSection(todayDate)
        Spacer(modifier = Modifier.height(Theme.dimension.regular))
        MotivationSection(userStatus, completedTasks, totalTasks)
        Spacer(modifier = Modifier.height(Theme.dimension.large))
        OverviewSection()
        TaskStatsSection(completedTasks, totalTasks, inProgressTasks)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun DateSection(
    todayDate: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.icon_calendar),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(Theme.dimension.small))
        Text(
            text = todayDate,
            style = Theme.textStyle.label.medium,
            color = Theme.color.body
        )
    }
}

@Composable
private fun MotivationSection(
    userStatus: UserStatus,
    completedTasks: Int,
    totalTasks: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Theme.dimension.regular)
    ) {
        MotivationMessages(
            userStatus = userStatus,
            completedTasks = completedTasks,
            totalTasks = totalTasks,
            modifier = Modifier.weight(1f)
        )
        TudeeRobotImage()
    }
}

@Composable
private fun MotivationMessages(
    userStatus: UserStatus,
    completedTasks: Int,
    totalTasks: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(Theme.dimension.small)
    ) {
        StatusTitleWithEmoji(userStatus)
        StatusMessage(userStatus, completedTasks, totalTasks)
    }
}

@Composable
private fun StatusTitleWithEmoji(
    userStatus: UserStatus,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Theme.dimension.small)
    ) {
        Text(
            text = getUserStatusTitle(userStatus),
            style = Theme.textStyle.title.small,
            color = Theme.color.title,
            fontWeight = FontWeight.Bold
        )
        StatusEmoji(userStatus)
    }
}

@Composable
private fun StatusMessage(
    userStatus: UserStatus,
    completedTasks: Int,
    totalTasks: Int,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier.fillMaxWidth(OverviewCardConstants.MESSAGE_WIDTH_RATIO),
        text = getUserStatusMessage(userStatus, completedTasks, totalTasks),
        style = Theme.textStyle.body.small,
        color = Theme.color.body
    )
}

@Composable
private fun StatusEmoji(
    userStatus: UserStatus,
    modifier: Modifier = Modifier
) {
    val emojiText = userStatus.getEmoji()
    
    Text(
        text = emojiText,
        style = Theme.textStyle.title.small,
        modifier = modifier.size(OverviewCardConstants.EMOJI_SIZE.dp)
    )
}

@Composable
private fun TudeeRobotImage(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .width(OverviewCardConstants.ROBOT_WIDTH.dp)
            .height(OverviewCardConstants.ROBOT_HEIGHT.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Box(
            modifier = Modifier
                .padding(top = 6.dp, start = 6.dp)
                .size(OverviewCardConstants.ROBOT_BACKGROUND_SIZE.dp)
                .clip(CircleShape)
                .background(Theme.color.primary.copy(alpha = 0.16f))
                .align(Alignment.BottomStart)
        )
        
        Image(
            modifier = Modifier
                .padding(start = 3.dp)
                .width(OverviewCardConstants.ROBOT_IMAGE_WIDTH.dp)
                .height(OverviewCardConstants.ROBOT_IMAGE_HEIGHT.dp),
            painter = painterResource(id = R.drawable.image_tudee),
            contentDescription = null
        )
    }
}

@Composable
private fun OverviewSection(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.overview),
        style = Theme.textStyle.title.large,
        color = Theme.color.title,
        modifier = modifier.padding(bottom = Theme.dimension.medium)
    )
}

@Composable
private fun TaskStatsSection(
    completedTasks: Int,
    totalTasks: Int,
    inProgressTasks: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(Theme.dimension.small)
    ) {
        StatusCardItem(
            count = completedTasks,
            label = stringResource(R.string.done),
            color = Theme.color.greenAccent,
            icon = painterResource(id = R.drawable.icon_overview_card_completed),
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(Theme.dimension.medium)
        )
        StatusCardItem(
            count = inProgressTasks,
            label = stringResource(R.string.in_progress),
            color = Theme.color.yellowAccent,
            icon = painterResource(id = R.drawable.icon_overview_card_inprogress),
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(Theme.dimension.medium)
        )
        StatusCardItem(
            count = totalTasks - completedTasks - inProgressTasks,
            label = stringResource(R.string.to_do),
            color = Theme.color.purpleAccent,
            icon = painterResource(id = R.drawable.icom_overview_card_todo),
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(Theme.dimension.medium)
        )
    }
}


@Composable
private fun getUserStatusTitle(userStatus: UserStatus): String = when (userStatus) {
    UserStatus.GOOD -> stringResource(R.string.tadaa_title)
    UserStatus.OKAY -> stringResource(R.string.stay_working_title)
    UserStatus.POOR -> stringResource(R.string.nothing_on_list_title)
    UserStatus.BAD -> stringResource(R.string.zero_progress_title)
}

@Composable
private fun getUserStatusMessage(userStatus: UserStatus, completedTasks: Int, totalTasks: Int): String = when (userStatus) {
    UserStatus.GOOD -> stringResource(R.string.amazing_work_message)
    UserStatus.OKAY -> stringResource(R.string.keep_going_message, completedTasks, totalTasks)
    UserStatus.POOR -> stringResource(R.string.fill_day_message_short)
    UserStatus.BAD -> stringResource(R.string.back_to_work_message)
}


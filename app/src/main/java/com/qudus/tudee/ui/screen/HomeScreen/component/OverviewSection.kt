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
import com.qudus.tudee.ui.designSystem.theme.Dimension.spacing8
import com.qudus.tudee.ui.designSystem.theme.Dimension.spacing12
import com.qudus.tudee.ui.designSystem.theme.Dimension.spacing16
import com.qudus.tudee.ui.designSystem.theme.Dimension.spacing24
import com.qudus.tudee.ui.designSystem.theme.Dimension.spacing3
import com.qudus.tudee.ui.designSystem.theme.Dimension.spacing6
import com.qudus.tudee.ui.constant.OverviewCardConstants
import com.qudus.tudee.ui.constant.UserStatus
import com.qudus.tudee.ui.constant.calculateUserStatus
import com.qudus.tudee.ui.constant.getEmoji
import com.qudus.tudee.ui.util.formatToArabicString

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeOverviewCard(
    modifier: Modifier = Modifier,
    completedTasks: Int,
    totalTasks: Int,
    inProgressTasks: Int = 0,
    todayDate: kotlinx.datetime.LocalDate,
) {
    val userStatus = calculateUserStatus(completedTasks, totalTasks)
    
    Column(
        modifier = modifier
            .fillMaxWidth()
            .offset(y = OverviewCardConstants.CARD_OFFSET_Y.dp)
            .padding(
                start = spacing8,
                end = spacing8,
                top = spacing16,
                bottom = spacing16
            )
            .clip(RoundedCornerShape(spacing16))
            .background(Theme.color.surfaceHigh)
            .padding(spacing16)
    ) {
        DateSection(todayDate)
        Spacer(modifier = Modifier.height(spacing12))
        MotivationSection(userStatus, completedTasks, totalTasks)
        Spacer(modifier = Modifier.height(spacing24))
        OverviewSection()
        TaskStatsSection(completedTasks, totalTasks, inProgressTasks)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun DateSection(
    todayDate: kotlinx.datetime.LocalDate,
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
            modifier = Modifier.size(spacing24)
        )
        Spacer(modifier = Modifier.width(spacing8))
        Text(
            text = todayDate.formatToArabicString(),
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
        horizontalArrangement = Arrangement.spacedBy(spacing12)
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
        verticalArrangement = Arrangement.spacedBy(spacing8)
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
        horizontalArrangement = Arrangement.spacedBy(spacing8)
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
                .padding(top = spacing6, start = spacing6)
                .size(OverviewCardConstants.ROBOT_BACKGROUND_SIZE.dp)
                .clip(CircleShape)
                .background(Theme.color.primary.copy(alpha = 0.16f))
                .align(Alignment.BottomStart)
        )
        
        Image(
            modifier = Modifier
                .padding(start = spacing3)
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
        modifier = modifier.padding(bottom = spacing16)
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
        horizontalArrangement = Arrangement.spacedBy(spacing8)
    ) {
        StatusCardItem(
            count = completedTasks,
            label = stringResource(R.string.done),
            color = Theme.color.greenAccent,
            icon = painterResource(id = R.drawable.icon_overview_card_completed),
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(spacing16)
        )
        StatusCardItem(
            count = inProgressTasks,
            label = stringResource(R.string.in_progress),
            color = Theme.color.yellowAccent,
            icon = painterResource(id = R.drawable.icon_overview_card_inprogress),
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(spacing16)
        )
        StatusCardItem(
            count = totalTasks - completedTasks - inProgressTasks,
            label = stringResource(R.string.to_do),
            color = Theme.color.purpleAccent,
            icon = painterResource(id = R.drawable.icom_overview_card_todo),
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(spacing16)
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


package com.qudus.tudee.ui.screen.HomeScreen.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.qudus.tudee.R
import com.qudus.tudee.ui.designSystem.component.LoadWorkSummarySingle
import com.qudus.tudee.ui.designSystem.component.StatusCardItem
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.ui.designSystem.theme.Dimension.spacing8
import com.qudus.tudee.ui.designSystem.theme.Dimension.spacing12
import com.qudus.tudee.ui.designSystem.theme.Dimension.spacing16
import com.qudus.tudee.ui.designSystem.theme.Dimension.spacing24
import java.util.Locale
import com.qudus.tudee.ui.util.extension.formatMonthToString
import com.qudus.tudee.ui.util.OverviewCardConstants

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeOverviewCard(
    modifier: Modifier = Modifier,
    completedTasks: Int,
    totalTasks: Int,
    inProgressTasks: Int = 0,
    todayDate: kotlinx.datetime.LocalDate,
) {
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
        LoadWorkSummarySingle(completedTasks, totalTasks)
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
            text = "today, " + todayDate.formatMonthToString(locale = Locale.ENGLISH, short = true, withDay = true),
            style = Theme.textStyle.label.medium,
            color = Theme.color.body
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


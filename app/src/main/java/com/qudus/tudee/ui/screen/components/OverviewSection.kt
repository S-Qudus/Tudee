package com.qudus.tudee.ui.screen.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.qudus.tudee.R
import com.qudus.tudee.ui.designSystem.theme.Theme
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import com.qudus.tudee.ui.composable.OverviewStatCard

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun OverviewSection(
    completedTasks: Int,
    totalTasks: Int,
    modifier: Modifier = Modifier
) {
    val today = LocalDate.now()
    val formattedDate = today.format(DateTimeFormatter.ofPattern("EEE, dd MMM yyyy", Locale.getDefault()))
    Box(
        modifier = modifier
            .fillMaxWidth()
            .offset(y = (-32).dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
            )
            .padding(horizontal = 16.dp, vertical = 20.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.icon_calendar),
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = formattedDate,
                    style = Theme.textStyle.label.medium,
                    color = Theme.color.body
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = stringResource(R.string.stay_working),
                        style = Theme.textStyle.title.medium,
                        color = Theme.color.title,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = stringResource(R.string.tasks_completed_message_only, completedTasks, totalTasks),
                        style = Theme.textStyle.body.small,
                        color = Theme.color.body
                    )
                    Text(
                        text = stringResource(R.string.keep_going),
                        style = Theme.textStyle.body.small,
                        color = Theme.color.body
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.image_tudee),
                    contentDescription = null,
                    modifier = Modifier.size(72.dp)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(R.string.overview),
                style = Theme.textStyle.title.large,
                color = Theme.color.title,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OverviewStatCard(
                    count = completedTasks,
                    label = stringResource(R.string.done),
                    color = Theme.color.greenAccent,
                    icon = painterResource(id = R.drawable.icon_overview_card_completed),
                    modifier = Modifier.weight(1f)
                )
                OverviewStatCard(
                    count = 16,
                    label = stringResource(R.string.in_progress),
                    color = Theme.color.yellowAccent,
                    icon = painterResource(id = R.drawable.icon_overview_card_inprogress),
                    modifier = Modifier.weight(1f)
                )
                OverviewStatCard(
                    count = totalTasks - completedTasks,
                    label = stringResource(R.string.to_do),
                    color = Theme.color.purpleAccent,
                    icon = painterResource(id = R.drawable.icom_overview_card_todo),
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
} 
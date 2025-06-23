package com.qudus.tudee.designSystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import com.qudus.tudee.R
import com.qudus.tudee.designSystem.theme.TudeeTheme

@Composable
fun CategoryTaskComponent(
    modifier: Modifier = Modifier,
    title: String,
    description: String?,
    priorityLevel: PriorityLevel,
    taskIcon: @Composable () -> Unit,
    dateText: String? = null,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .border(1.dp, TudeeTheme.color.surfaceHigh, RoundedCornerShape(16.dp))
            .background(TudeeTheme.color.surfaceHigh, RoundedCornerShape(16.dp))
            .padding(top = 4.dp, start = 4.dp, end = 12.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.size(56.dp),
                contentAlignment = Alignment.Center
            ) {
                taskIcon()
            }

            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (dateText != null) {
                    TudeeChip(
                        label = dateText,
                        icon = painterResource(id = R.drawable.icon_calendar),
                        backgroundColor = TudeeTheme.color.surface,
                        labelColor = TudeeTheme.color.body
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                }

                TudeeChip(
                    label = priorityLevel.displayName,
                    icon = painterResource(id = priorityLevel.iconResId),
                    backgroundColor = priorityLevel.getColor(),
                    labelColor = TudeeTheme.color.onPrimary
                )
            }
        }

        CategoryTaskComponentInformation(
            modifier = modifier,
            title = title,
            description = description
        )
    }
}


@Composable
private fun CategoryTaskComponentInformation(
    modifier: Modifier = Modifier,
    title: String,
    description: String?,

) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 8.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Text(
            text = title,
            style = TudeeTheme.textStyle.label.large,
            color = TudeeTheme.color.body
        )
        description?.let {
            Text(
                modifier = Modifier.padding(end = 12.dp, bottom = 12.dp),
                text = it,
                style = TudeeTheme.textStyle.label.small,
                color = TudeeTheme.color.hint,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@PreviewScreenSizes
@Composable
private fun CategoryTaskComponentPreview() {
    CategoryTaskComponent(
        title = stringResource(R.string.default_task_title),
        description = stringResource(R.string.default_task_description),
        priorityLevel = PriorityLevel.MEDIUM,
        taskIcon = {
            Icon(
                painter = painterResource(id = R.drawable.icon_category_book_open),
                contentDescription = "Task Icon",
                modifier = Modifier.size(32.dp),
                tint = TudeeTheme.color.purpleAccent
            )
        },
        onClick = {}
    )
}

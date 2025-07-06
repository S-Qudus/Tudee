package com.qudus.tudee.ui.designSystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.qudus.tudee.R
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.ui.state.PriorityUiState
import com.qudus.tudee.ui.state.getColor
import com.qudus.tudee.ui.state.getIcon
import com.qudus.tudee.ui.state.getLabel
import com.qudus.tudee.ui.util.extension.toPainter

@Composable
fun CategoryTask(
    modifier: Modifier = Modifier,
    title: String,
    description: String?,
    priorityIcon: Painter,
    priorityLabel: String,
    priorityBackground: Color,
    onClick: () -> Unit,
    dateText: String? = null,
    taskRes: @Composable (Modifier) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .border(1.dp, Theme.color.surfaceHigh, RoundedCornerShape(16.dp))
            .background(Theme.color.surfaceHigh, RoundedCornerShape(16.dp))
            .padding(top = 4.dp, start = 4.dp, end = 12.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            taskRes(Modifier.padding(8.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                DateChip(dateText)
                TudeeChip(
                    label = priorityLabel,
                    icon = priorityIcon,
                    activeBackgroundColor = priorityBackground,
                    isActive = true,
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
private fun DateChip(dateText: String?) {
    if (dateText != null) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clip(RoundedCornerShape(100.dp))
                .background(Theme.color.surface)
                .padding(horizontal = 8.dp, vertical = 6.dp)
        ) {
            Icon(
                painter = R.drawable.icon_calendar.toPainter(),
                contentDescription = "Calender",
                modifier = Modifier.size(12.dp),
                tint = Theme.color.body
            )
            Text(
                text = dateText,
                style = Theme.textStyle.label.small,
                color = Theme.color.body
            )
        }
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
            style = Theme.textStyle.label.large,
            color = Theme.color.body
        )
        description?.let {
            Text(
                modifier = Modifier.padding(end = 12.dp, bottom = 12.dp),
                text = it,
                style = Theme.textStyle.label.small,
                color = Theme.color.hint,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview
@Composable
private fun CategoryTaskPreview() {
    CategoryTask(
        title = stringResource(R.string.default_task_title),
        description = stringResource(R.string.default_task_description),
        priorityIcon = PriorityUiState.HIGH.getIcon(),
        priorityLabel = PriorityUiState.HIGH.getLabel(),
        priorityBackground = PriorityUiState.HIGH.getColor(),
        dateText = "22-6-2025",
        onClick = {},
        taskRes = { modifier ->
            Icon(
                painter = painterResource(id = R.drawable.icon_category_book_open),
                contentDescription = "Task Icon",
                modifier = modifier,
                tint = Theme.color.purpleAccent
            )
        }
    )
}

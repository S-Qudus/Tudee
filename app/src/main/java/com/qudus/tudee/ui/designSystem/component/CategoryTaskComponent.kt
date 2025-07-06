package com.qudus.tudee.ui.designSystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.qudus.tudee.R
import com.qudus.tudee.domain.entity.Priority
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.ui.util.getColorForPriority
import com.qudus.tudee.ui.util.getIconForPriority
import com.qudus.tudee.ui.util.getLabelForPriority

@Composable
fun CategoryTask(
    modifier: Modifier = Modifier,
    title: String,
    description: String?,
    priorityLevel: Priority,
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
            verticalAlignment = Alignment.CenterVertically
        ) {
            taskRes(Modifier.padding(8.dp))

            Spacer(modifier = Modifier.weight(1f))

            if (dateText != null){
                TudeeChip(
                    label = dateText,
                    icon = painterResource(id = R.drawable.icon_calendar),
                    activeBackgroundColor = Theme.color.primary,
                    backgroundColor = Theme.color.primary,
                    isActive = true,
                )
                Spacer(modifier = Modifier.width(4.dp))
            }

            TudeeChip(
                label = getLabelForPriority(priorityLevel),
                icon = getIconForPriority(priorityLevel),
                activeBackgroundColor = getColorForPriority(priorityLevel),
                backgroundColor = Theme.color.surface,
                isActive = true,
            )
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
        priorityLevel = Priority.MEDIUM,
        onClick = {},
        taskRes = {
            modifier ->
            Icon(
                painter = painterResource(id = R.drawable.icon_category_book_open),
                contentDescription = "Task Icon",
                modifier = modifier,
                tint = Theme.color.purpleAccent
            )
        }
    )
}

//package com.qudus.tudee.ui.composable.taskComposable
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Icon
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.text.style.TextOverflow
//import androidx.compose.ui.unit.dp
//import com.qudus.tudee.R
//import com.qudus.tudee.domain.entity.Task
//import com.qudus.tudee.ui.designSystem.component.TudeeChip
//import com.qudus.tudee.ui.designSystem.theme.Theme
//import com.qudus.tudee.ui.util.getColorForPriority
//import com.qudus.tudee.ui.util.getIconForPriority
//import com.qudus.tudee.ui.util.getLabelForPriority
//import kotlinx.datetime.*
//
//@Composable
//fun TaskItemCard(
//    task: Task,
//    categoryImagePath: String? = null,
//    onClick: (Task) -> Unit,
//    modifier: Modifier = Modifier
//) {
//    Column(
//        modifier = modifier
//            .clip(RoundedCornerShape(Theme.dimension.medium))
//            .clickable { onClick(task) }
//            .background(Theme.color.surfaceHigh)
//            .padding(
//                start = Theme.dimension.extraSmall,
//                top = Theme.dimension.extraSmall,
//                end = Theme.dimension.regular
//            ),
//        verticalArrangement = Arrangement.spacedBy(2.dp)
//    ) {
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            // Category Thumbnail
//            Box(modifier = Modifier.size(56.dp), contentAlignment = Alignment.Center) {
//                CategoryThumbnail(
//                    imagePath = categoryImagePath,
//                    modifier = Modifier.size(Theme.dimension.extraLarge)
//                )
//            }
//
//            // Priority Tag and Date
//            Row {
//                TaskDateAndPriority(task = task)
//            }
//        }
//
//        // Task Content
//        Column(
//            modifier = Modifier.padding(start = Theme.dimension.small),
//            verticalArrangement = Arrangement.spacedBy(2.dp),
//        ) {
//            Text(
//                text = task.title,
//                color = Theme.color.body,
//                style = Theme.textStyle.label.large,
//                maxLines = 1,
//            )
//
//            task.description?.let {
//                Text(
//                    modifier = Modifier.padding(bottom = Theme.dimension.regular),
//                    text = task.description,
//                    color = Theme.color.hint,
//                    style = Theme.textStyle.label.small,
//                    maxLines = 1,
//                    overflow = TextOverflow.Ellipsis
//                )
//            }
//        }
//    }
//}
//
//@Composable
//private fun CategoryThumbnail(
//    imagePath: String?,
//    modifier: Modifier = Modifier
//) {
//    // Placeholder for category image
//    Box(
//        modifier = modifier
//            .clip(RoundedCornerShape(Theme.dimension.small))
//            .background(Theme.color.primary.copy(alpha = 0.1f)),
//        contentAlignment = Alignment.Center
//    ) {
//        Icon(
//            painter = painterResource(id = R.drawable.icon_category_book_open),
//            contentDescription = null,
//            tint = Theme.color.primary,
//            modifier = Modifier.size(24.dp)
//        )
//    }
//}
//
//@Composable
//private fun TaskDateAndPriority(
//    task: Task,
//    modifier: Modifier = Modifier
//) {
//    Column(
//        modifier = modifier,
//        verticalArrangement = Arrangement.spacedBy(4.dp)
//    ) {
//        // Date Chip
//        TudeeChip(
//            label = formatTaskDate(task.createdAt),
//            icon = painterResource(id = R.drawable.icon_calendar),
//            backgroundColor = Theme.color.surface,
//            labelColor = Theme.color.body
//        )
//
//        // Priority Chip
//        TudeeChip(
//            label = getLabelForPriority(task.priority),
//            icon = getIconForPriority(task.priority),
//            backgroundColor = getColorForPriority(task.priority),
//            labelColor = Theme.color.onPrimary
//        )
//    }
//}
//
//@Composable
//private fun formatTaskDate(date: LocalDate): String {
//    val today = Clock.System.now()
//        .toLocalDateTime(TimeZone.currentSystemDefault())
//        .date
//
//    return when {
//        date == today -> stringResource(R.string.today)
//        date == today.minus(DatePeriod(days = 1)) -> stringResource(R.string.yesterday)
//        date == today.plus(DatePeriod(days = 1)) -> stringResource(R.string.tomorrow)
//        else -> "${date.dayOfMonth}/${date.monthNumber}/${date.year}"
//    }
//}
package com.qudus.tudee.ui.composable

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.qudus.tudee.designSystem.component.DatePicker
import com.qudus.tudee.ui.designSystem.theme.Theme
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MonthNavigationHeader(
    currentMonth: LocalDate,
    onMonthChange: (LocalDate) -> Unit
) {
    var showDatePicker by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        ArrowButton(
            modifier = Modifier,
            onClick = { onMonthChange(currentMonth.minusMonths(1)) },
            imageVector = Icons.Default.KeyboardArrowLeft,
            contentDescription = "Previous Month",
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.clickable {
                showDatePicker = true
            }
        ) {
            Text(
                text = currentMonth.format(DateTimeFormatter.ofPattern("MMM, yyyy")),
                color = Theme.color.body,
                style = Theme.textStyle.label.medium
            )
            ArrowButton(
                modifier = Modifier,
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "date picker",
                showBorder = false,
                size = 16.dp,
                iconSize = 16.dp
            )
        }
        ArrowButton(
            modifier = Modifier,
            onClick = { onMonthChange(currentMonth.plusMonths(1)) },
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = "Next Month",
        )

        if (showDatePicker) {
            DatePicker(
                showDialog = true,
                selectedDate = currentMonth,
                onDismiss = { showDatePicker = false },
                onClear = { showDatePicker = true },
                onDateSelected = {
                    onMonthChange(it)
                    showDatePicker = false
                }
            )
        }
    }
}
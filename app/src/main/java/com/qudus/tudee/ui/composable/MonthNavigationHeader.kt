package com.qudus.tudee.ui.composable

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.qudus.tudee.designSystem.component.DatePicker
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.ui.util.extension.formatMonthToString
import kotlinx.datetime.*
import kotlinx.datetime.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MonthNavigationHeader(
    currentMonth: LocalDate,
    selectedDate: LocalDate,
    onMonthChange: (LocalDate) -> Unit,
    onDatePicked: (LocalDate) -> Unit
) {
    var showDatePicker by remember { mutableStateOf(false) }
    val locale = LocalConfiguration.current.locale
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        ArrowButton(
            modifier = Modifier,
            onClick = {
                val prev = currentMonth.minus(DatePeriod(months = 1))
                onMonthChange(LocalDate(prev.year, prev.month, 1))
            },
            imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
            contentDescription = "Previous Month",
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = currentMonth.formatMonthToString(locale = locale),
                color = Theme.color.body,
                style = Theme.textStyle.label.medium,
                modifier = Modifier.clickable {
                    showDatePicker = true
                }
            )
            ArrowButton(
                modifier = Modifier,
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "date picker",
                showBorder = false,
                size = 16.dp,
                iconSize = 16.dp,
                onClick = { showDatePicker = true }
            )
        }
        ArrowButton(
            modifier = Modifier,
            onClick = {
                val next = currentMonth.plus(DatePeriod(months = 1))
                onMonthChange(LocalDate(next.year, next.month, 1))
            },
            imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
            contentDescription = "Next Month",
        )

        if (showDatePicker) {
            DatePicker(
                showDialog = true,
                selectedDate = selectedDate,
                onDismiss = { showDatePicker = false },
                onClear = { showDatePicker = true },
                onDateSelected = { picked ->
                    onDatePicked(picked)
                    showDatePicker = false
                }
            )
        }
    }
}
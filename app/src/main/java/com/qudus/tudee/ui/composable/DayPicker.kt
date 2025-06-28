package com.qudus.tudee.ui.composable

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DayPicker(
    currentMonth: LocalDate,
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit
) {
    val daysInMonth = remember(currentMonth) {
        val firstDayOfMonth = currentMonth.with(TemporalAdjusters.firstDayOfMonth())
        val lastDayOfMonth = currentMonth.with(TemporalAdjusters.lastDayOfMonth())
        (0 until lastDayOfMonth.dayOfMonth).map { firstDayOfMonth.plusDays(it.toLong()) }
    }

    val listState = rememberLazyListState()

    LaunchedEffect(currentMonth) {
        val today = LocalDate.now()
        if (currentMonth.year == today.year && currentMonth.month == today.month) {
            val index = daysInMonth.indexOf(today)
            if (index != -1) {
                listState.scrollToItem(index)
            }
        }
    }

    LaunchedEffect(selectedDate) {
        val index = daysInMonth.indexOf(selectedDate)
        if (index == -1) return@LaunchedEffect

        val isVisible = listState.layoutInfo.visibleItemsInfo.any { it.index == index }

        if (!isVisible) {
            listState.animateScrollToItem(index, scrollOffset = -150)
        }
    }

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        state = listState
    ) {
        items(daysInMonth) {
            DayItem(it, selectedDate == it) {
                onDateSelected(it)
            }
        }
    }
}
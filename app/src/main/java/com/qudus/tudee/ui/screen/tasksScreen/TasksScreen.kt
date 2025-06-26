package com.qudus.tudee.ui.screen.tasksScreen

import HorizontalCalendar
import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.qudus.tudee.designSystem.component.TabBar
import com.qudus.tudee.ui.composable.HeaderTitle
import com.qudus.tudee.ui.composable.TaskListSection
import com.qudus.tudee.ui.designSystem.theme.Theme
import java.time.LocalDate

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TasksScreen() {
    Scaffold(
        modifier = Modifier
            .background(Theme.color.surface)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Theme.color.surface),
        ) {

            HeaderTitle("Tasks")

            var currentMonth by remember { mutableStateOf(LocalDate.now()) }
            var selectedDate by remember { mutableStateOf(LocalDate.now()) }

            HorizontalCalendar(
                currentMonth = currentMonth,
                onMonthChange = { currentMonth = it },
                selectedDate = selectedDate,
                onDateSelected = { selectedDate = it }
            )

            TabBar(modifier = Modifier)

            TaskListSection(modifier = Modifier.weight(1f))

        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview()
//@PreviewLightDark()
@Composable
private fun TasksScreenPreview() {
    TasksScreen()
}

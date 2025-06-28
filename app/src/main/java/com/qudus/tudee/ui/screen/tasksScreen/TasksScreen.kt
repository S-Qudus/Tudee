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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.qudus.tudee.designSystem.component.TabBar
import com.qudus.tudee.domain.entity.State
import com.qudus.tudee.ui.composable.HeaderTitle
import com.qudus.tudee.ui.composable.TaskListSection
import com.qudus.tudee.ui.designSystem.theme.Theme
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TasksScreen(
    viewModel: TaskViewModel = koinViewModel()
) {

    val tasks by viewModel.tasks.collectAsState(initial = emptyList())
    val selectedDate by viewModel.selectedDate.collectAsState()
    val selectedMonth by viewModel.selectedMonth.collectAsState()
    val selectedState by viewModel.selectedState.collectAsState()

    val countsState = remember(tasks) {
        State.values().associateWith { state ->
            tasks.count { it.state == state }
        }
    }

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

            HorizontalCalendar(
                currentMonth = selectedMonth,
                onMonthChange = { viewModel.selectMonth(it) },
                selectedDate = selectedDate,
                onDateSelected = { viewModel.selectDate(it) }
            )

            TabBar(
                modifier = Modifier,
                selectedState = selectedState,
                onStateSelected = { viewModel.selectState(it) },
                countForState = countsState,
            )

            TaskListSection(
                modifier = Modifier
                    .weight(1f),
                tasks = tasks
            )

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

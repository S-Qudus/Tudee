package com.qudus.tudee.ui.screen.tasksScreen

import HorizontalCalendar
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.qudus.tudee.R
import com.qudus.tudee.ui.designSystem.component.HeaderTitle
import com.qudus.tudee.ui.designSystem.component.TabBar
import com.qudus.tudee.ui.designSystem.component.TaskListSection
import com.qudus.tudee.ui.designSystem.component.buttons.TudeeFloatingActionButton
//import com.qudus.tudee.designSystem.component.TabBar
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.ui.screen.tasksScreen.state.TasksUiState
import com.qudus.tudee.ui.state.StateUiState
import kotlinx.datetime.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TasksScreenContent(
    modifier: Modifier,
    uiState: TasksUiState,
    countsByState: Map<StateUiState, Int>,
    onDateSelected: (LocalDate) -> Unit,
    onMonthChange: (LocalDate) -> Unit,
    onStateSelected: (StateUiState) -> Unit,
    onClickAddNewTask: () -> Unit,
) {
    Box {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Theme.color.surface),
        ) {

            HeaderTitle(stringResource(R.string.tasks))

            HorizontalCalendar(
                currentMonth = uiState.currentMonth,
                onMonthChange = onMonthChange,
                selectedDate = uiState.selectedDate,
                onDateSelected = onDateSelected
            )

            TabBar(
                modifier = Modifier,
                selectedState = uiState.selectedState,
                onStateSelected = onStateSelected,
                countForState = countsByState
            )

            TaskListSection(
                modifier = Modifier
                    .weight(1f),
                tasks = uiState.tasks
            )
        }

        TudeeFloatingActionButton(
            onClickIconButton = onClickAddNewTask,
            painter = painterResource(R.drawable.icon_note_add),
            isEnabled = true,
            isLoading = false,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 42.dp, end = 12.dp)
        )
    }
}
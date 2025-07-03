package com.qudus.tudee.ui.screen.HomeScreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.qudus.tudee.R
import com.qudus.tudee.ui.designSystem.component.buttons.TudeeFloatingActionButton
import com.qudus.tudee.ui.designSystem.component.BottomNavBar
import com.qudus.tudee.ui.composable.TudeeFloatingActionButton
import com.qudus.tudee.ui.designSystem.component.TudeeScaffold
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.ui.screen.HomeScreen.component.HomeContent
import com.qudus.tudee.ui.screen.addTask.AddTaskScreen
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalFoundationApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    
    TudeeScaffold(
        contentBackground = Theme.color.surface,
        floatingActionButton = {
            TudeeFloatingActionButton(
                onClickIconButton = { viewModel.onAddButtonClicked() },
                isEnabled = true,
                isLoading = false,
                contentDescription = stringResource(R.string.add_task),
                hasShadow = true,
                painter = painterResource(id = R.drawable.icon_note_add),
                modifier = Modifier.size(64.dp)
            )
        },
        content = {
            HomeContent(
                viewModel = viewModel
            )
        }
    )
    
    // Show AddTaskScreen when showAddTaskSheet is true
    if (state.showAddTaskSheet) {
        AddTaskScreen(
            onDismiss = { viewModel.onDismissBottomSheet() },
            onTaskAdded = { viewModel.refreshTasks() },
            navController = navController
        )
    }
}



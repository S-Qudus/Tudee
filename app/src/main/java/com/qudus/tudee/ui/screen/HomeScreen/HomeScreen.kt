package com.qudus.tudee.ui.screen.HomeScreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.qudus.tudee.R
import com.qudus.tudee.ui.composable.TudeeIconButton
import com.qudus.tudee.ui.designSystem.component.BottomNavBar
import com.qudus.tudee.ui.designSystem.component.TudeeScaffold
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.ui.screen.getBottomNavItems
import com.qudus.tudee.ui.screen.HomeScreen.component.HomeContent
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalFoundationApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = koinViewModel()
) {
    TudeeScaffold(
        contentBackground = Theme.color.surface,
        floatingActionButton = {
            TudeeIconButton(
                onClickIconButton = { viewModel.onAddButtonClicked() },
                isEnabled = true,
                isLoading = false,
                icon = painterResource(id = R.drawable.icon_note_add),
                contentDescription = stringResource(R.string.add_task),
                hasShadow = true,
                modifier = Modifier.size(64.dp)
            )
        },
        bottomBar = {
            BottomNavBar(
                navController = navController,
                items = getBottomNavItems(),
                selectedRoute = "home"
            )
        },
        content = {
            HomeContent(
                viewModel = viewModel
            )
        }
    )
}



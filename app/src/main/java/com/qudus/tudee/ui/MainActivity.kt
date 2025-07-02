package com.qudus.tudee.ui

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.qudus.tudee.ui.designSystem.theme.Theme
import androidx.annotation.RequiresApi
import androidx.compose.runtime.*
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.qudus.tudee.ui.designSystem.theme.TudeeTheme
import com.qudus.tudee.ui.screen.task_details.TaskDetailsScreen
import androidx.navigation.compose.rememberNavController
import com.qudus.tudee.ui.screen.HomeScreen.HomeScreen
import com.qudus.tudee.ui.screen.HomeScreen.HomeViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TudeeTheme(isDarkTheme = false) { TaskDetailsScreen() }
        }
    }
}
            val homeViewModel: HomeViewModel = koinViewModel()
            val state by homeViewModel.uiState.collectAsStateWithLifecycle()

            TudeeTheme(isDarkTheme = state.isDarkTheme) {
                val navController = rememberNavController()

                HomeScreen(
                    navController = navController,
                    viewModel = homeViewModel
                )
            }
        }
    }
}
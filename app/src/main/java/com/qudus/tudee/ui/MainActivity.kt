package com.qudus.tudee.ui

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.qudus.tudee.ui.designSystem.theme.TudeeTheme
import com.qudus.tudee.ui.screen.HomeScreen.HomeScreen
import com.qudus.tudee.ui.screen.HomeScreen.HomeViewModel
import com.qudus.tudee.ui.screen.HomeScreen.component.HomeContent
import org.koin.androidx.compose.koinViewModel
import com.qudus.tudee.ui.navigation.TudeeApp

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: HomeViewModel = koinViewModel()
            val state by viewModel.uiState.collectAsState()

            TudeeTheme(isDarkTheme = state.isDarkTheme) {
                val navController = rememberNavController()
                HomeScreen(navController = navController, viewModel = viewModel)
            }
        }
    }
}
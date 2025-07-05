package com.qudus.tudee.ui

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.qudus.tudee.ui.navigation.TudeeApp
import com.qudus.tudee.ui.screen.configration.ConfigurationViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        val viewModel: ConfigurationViewModel by viewModel()
        val uiState = viewModel.uiState
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition {
            uiState.value.startDestination == null
        }

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            val isDarkTheme = uiState.collectAsStateWithLifecycle()
            TudeeApp(isDarkTheme.value.isDarkTheme)
        }
    }
}
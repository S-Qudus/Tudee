package com.qudus.tudee.ui

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.qudus.tudee.ui.navigation.NavViewModel
import com.qudus.tudee.ui.navigation.TudeeApp
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewModel: NavViewModel by viewModel()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition {
            viewModel.startDestination.value == null
        }

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            TudeeApp()
        }
    }
}
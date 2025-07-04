package com.qudus.tudee.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import com.qudus.tudee.ui.designSystem.theme.TudeeTheme
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TudeeApp(){
    val navViewModel: NavViewModel = koinViewModel()
    val isDarkTheme by navViewModel.isDarkTheme.collectAsState(initial = false)
    
    TudeeTheme(isDarkTheme = isDarkTheme) {
        val navController = rememberNavController()
        TudeeNavGraph(navController)
    }
}
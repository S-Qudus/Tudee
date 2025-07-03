package com.qudus.tudee.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.qudus.tudee.ui.designSystem.theme.TudeeTheme

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TudeeApp(){
    TudeeTheme(isDarkTheme = false) {
        val navController = rememberNavController()
        TudeeNavGraph(navController)
    }
}
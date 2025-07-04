package com.qudus.tudee.ui.screen.routes

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.qudus.tudee.ui.navigation.Screen
import com.qudus.tudee.ui.screen.tasksScreen.TasksScreen

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.categoriesRoute(navController: NavController){
    composable(
        route = Screen.CategoriesScreen.route
    ) {
        Box(modifier = Modifier.fillMaxSize()
        , contentAlignment = Alignment.Center) {
            Text("Categories Screen")
        }
    }
}
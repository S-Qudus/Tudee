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
import com.qudus.tudee.ui.designSystem.theme.TudeeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen()
        setContent {
            TudeeTheme(isDarkTheme = false) { }
        }
    }
}

@Composable
fun MyComposable() {
    Text(
        text = "Hello Design System!",
        color = Theme.color.title,
        style = Theme.textStyle.headline.medium,
        modifier = Modifier
            .background(Theme.color.primary)
            .padding(12.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun TudeePreview() {
    TudeeTheme(isDarkTheme = true) {
        MyComposable()
    }
}
package com.qudus.tudee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.qudus.tudee.presentation.designSystem.theme.Theme
import com.qudus.tudee.presentation.designSystem.theme.TudeeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
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
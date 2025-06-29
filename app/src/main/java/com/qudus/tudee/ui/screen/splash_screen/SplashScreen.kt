package com.qudus.tudee.ui.screen.splash_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.qudus.tudee.R
import com.qudus.tudee.ui.designSystem.theme.Theme
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashScreen(
    viewModel: SplashViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(true) {
        delay(3000)
        if (!state.isCompleteOnBoarding){
            // navigate to onBoarding
        }else{
            // navigate to Home
        }
    }
    SplashScreenContent(state)
}

@Composable
fun SplashScreenContent(
    state: SplashUiState
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Theme.color.overlay)
    ) {
        val ellipseBackground =
            if (state.isDarkTheme) R.drawable.ellipse_background_dark else R.drawable.ellipse_background
        Image(
            painter = painterResource(ellipseBackground),
            contentDescription = "ellipse_background",
            modifier = Modifier
                .align(Alignment.TopEnd)
        )
        Image(
            painter = painterResource(R.drawable.image_tudee_logo),
            contentDescription = "logo",
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}

@PreviewScreenSizes
@Preview(showSystemUi = true, name = "PIXEL", device = Devices.PIXEL_7_PRO)
@Composable
fun SplashScreenPreview() {
    SplashScreen()
}
package com.qudus.tudee.ui.designSystem.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TudeeScaffold(
    modifier: Modifier = Modifier.systemBarsPadding(),
    topBar: (@Composable () -> Unit)? = null,
    floatingActionButton: (@Composable () -> Unit)? = null,
    contentBackground: Color = Color.White,
    snackbarHostState: SnackbarHostState? = null,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = contentBackground
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding()
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                topBar?.invoke()
                content()
            }
        }

        floatingActionButton?.let {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .navigationBarsPadding()
                    .padding(end = 12.dp, bottom = 8.dp)
                // .padding(end = 12.dp)
                ,
                contentAlignment = Alignment.BottomEnd
            ) {
                it()
            }
        }

        snackbarHostState?.let {
            SnackbarHost(
                hostState = it,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .navigationBarsPadding()
            )
        }
    }
}

package com.qudus.tudee.ui.designSystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TudeeScaffold(
    modifier: Modifier = Modifier,
    topBar: (@Composable () -> Unit)? = null,
    floatingActionButton: (@Composable () -> Unit)? = null,
    bottomBar: (@Composable () -> Unit)? = null,
    contentBackground: Color = Color.White,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = contentBackground
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                topBar?.invoke()
                content()
            }
            
            bottomBar?.invoke()
        }
        
        floatingActionButton?.let {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                it()
            }
        }
    }
} 
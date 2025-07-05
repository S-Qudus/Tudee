package com.qudus.tudee.ui.designSystem.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomSheetDefaults.DragHandle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.qudus.tudee.ui.designSystem.theme.Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TudeeBottomSheet(
    isSheetOpen: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    expanded: Boolean = true,
    content: @Composable ColumnScope.() -> Unit
) {
    val sheetState =
        rememberModalBottomSheetState(
            skipPartiallyExpanded = expanded,
        )

    AnimatedVisibility(
        visible = isSheetOpen,
        enter = slideInVertically(),
        exit = slideOutVertically()
    ) {
        ModalBottomSheet(
            modifier = modifier.fillMaxWidth().statusBarsPadding(),
            sheetState = sheetState,
            onDismissRequest = onDismissRequest,
            containerColor = Theme.color.surface,
            dragHandle = { TudeeDragHandle() }
        ) {
            content()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TudeeDragHandle() {
    DragHandle(
        width = 32.dp,
        height = 4.dp,
        color = Theme.color.body,
        shape = CircleShape
    )
}
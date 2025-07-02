package com.qudus.tudee.ui.screen.ViewTask.composabl

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.qudus.tudee.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksTopAppBar(
    title: String,
    defaultCategoryType: Boolean,
    onBack: () -> Unit,
    onEditCategory: () -> Unit
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    painter = painterResource(R.drawable.icon_arrow_left),
                    contentDescription = "Back"
                )
            }
        },
        actions = {
            if (defaultCategoryType==true ) {
                IconButton(onClick = onEditCategory) {
                    Icon(
                        painter = painterResource(R.drawable.icon_pencil_edit),
                        contentDescription = "Edit"
                    )
                }
            }
        }
    )
}
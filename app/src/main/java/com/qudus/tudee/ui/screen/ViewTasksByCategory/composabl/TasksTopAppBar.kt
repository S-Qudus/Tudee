package com.qudus.tudee.ui.screen.ViewTasksByCategory.composabl



import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.qudus.tudee.R
import com.qudus.tudee.ui.designSystem.theme.Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksTopAppBar(
    title: String,
    isDefaultCategory: Boolean,
    onBack: () -> Unit,
    onEditCategory: () -> Unit
) {
    TopAppBar(
        title = { Text(text = title, style = Theme.textStyle.title.large) },

            navigationIcon = {
                CircularIconButton(
                    iconResId = R.drawable.icon_arrow_left,
                    contentDescription = "Back",
                    onClick = onBack,
                    tint = Theme.color.primary,
                    backgroundColor = Color.Transparent,
                    borderColor = Theme.color.title.copy(alpha = 0.5f)
                )
            },
            actions = {
                if (!isDefaultCategory) {
                    CircularIconButton(
                        iconResId = R.drawable.icon_pencil_edit,
                        contentDescription = "Edit",
                        onClick = onEditCategory,

                        backgroundColor = Color.Transparent,
                        borderColor = Theme.color.title.copy(alpha = 0.5f)
                    )
                }
            }
        ,

        )
}
@Composable
fun CircularIconButton(
    iconResId: Int,
    contentDescription: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    tint: Color = Theme.color.surface,
    backgroundColor: Color = Color.Transparent,
    borderColor: Color = Theme.color.title,
    borderWidth: Dp = 1.dp
) {
    Box(
        modifier = modifier
            .size(35.dp)
            .clip(CircleShape)
            .border(borderWidth, borderColor, CircleShape)
            .background(backgroundColor)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            painter = painterResource(iconResId),
            contentDescription = contentDescription,

            modifier = Modifier.size(24.dp)
        )
    }
}
package com.qudus.tudee.ui.screen.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.qudus.tudee.R
import com.qudus.tudee.ui.designSystem.component.CategoryBadgeItem
import com.qudus.tudee.ui.designSystem.component.SnackBar
import com.qudus.tudee.ui.designSystem.component.SnackBarState
import com.qudus.tudee.ui.designSystem.component.TudeeTextBadge
import com.qudus.tudee.ui.designSystem.component.buttons.TudeeFloatingActionButton
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.ui.designSystem.theme.TudeeTheme
import com.qudus.tudee.ui.screen.addCategoryScreen.AddCategoryScreen
import com.qudus.tudee.ui.util.getIconPainterForCategory
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel
import java.io.File

@Composable
fun CategoriesScreen(
    viewModel: CategoriesViewModel = koinViewModel(),
    onCategoryClick: (Long) -> Unit
) {
    val uiState = viewModel.uiState.collectAsState()

    LaunchedEffect(uiState.value.createSuccessMessage) {
        if (uiState.value.createSuccessMessage != null) {
            delay(3000)
            viewModel.resetMessage()
        }
    }

    CategoriesScreenContent(
        uiState = uiState.value,
        onCategoryClick = { viewModel::navigateToCategoryDetails },
        onClickAddCategory = { viewModel.setShowBottomSheet(true) }
    )
}

@Composable
fun CategoriesScreenContent(
    uiState: CategoriesUiState,
    onCategoryClick: (Long) -> Unit,
    onClickAddCategory: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(Theme.color.surface)
    ) {
        Column {
            // Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Theme.color.surfaceHigh)
                    .padding(horizontal = 16.dp, vertical = 20.dp)
            ) {
                Text(
                    text = stringResource(R.string.categories),
                    style = Theme.textStyle.title.large,
                    color = Theme.color.title,
                    maxLines = 1
                )
            }

            // Error Message
            uiState.errorMessage?.let { error ->
                Text(
                    text = "Error: $error",
                    color = Theme.color.error,
                    modifier = Modifier.padding(16.dp)
                )
            }

            // Loading State
            if (uiState.isLoading) {
                Text(
                    text = "Loading categories...",
                    modifier = Modifier.padding(16.dp)
                )
            }

            // Content
            if (!uiState.isLoading && uiState.errorMessage == null) {
                if (uiState.categories.isEmpty()) {
                    Text(
                        text = "No categories found",
                        modifier = Modifier.padding(16.dp)
                    )
                } else {
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(minSize = 104.dp),
                        contentPadding = PaddingValues(8.dp),
                        verticalArrangement = Arrangement.spacedBy(Theme.dimension.spacing24),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        items(uiState.categories) { category ->
                            CategoryBadgeItem(
                                id = category.id,
                                title = category.title,
                                modifier = Modifier,
                                isClickable = true,
                                onItemClick = { onCategoryClick(category.id) },
                                imagePainter = if (category.defaultCategoryType != null) {
                                    getIconPainterForCategory(category.defaultCategoryType)
                                } else {
                                    rememberAsyncImagePainter(model = File(category.imagePath))
                                },
                                badge = {
                                    TudeeTextBadge(
                                        modifier = Modifier.align(Alignment.TopEnd),
                                        badgeNumber = category.taskCount.toString(),
                                        contentColor = Theme.color.hint,
                                        containerColor = Theme.color.surfaceLow
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }

        // Tudee Floating Action Button
        TudeeFloatingActionButton(
            onClickIconButton = { onClickAddCategory },
            painter = painterResource(R.drawable.icon_add_category),
            isEnabled = true,
            isLoading = false,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        )

        // Success Snackbar
        uiState.createSuccessMessage?.let { message ->
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(bottom = 16.dp)
            ) {
                SnackBar(
                    state = SnackBarState.SUCCESS,
                    message = message,
                    iconColor = Theme.color.greenAccent,
                    background = Theme.color.greenVariant
                )
            }
        }

        // Bottom Sheet
        if (uiState.showBottomSheet) {
            AddCategoryScreen()
        }
    }
}


@Preview
@Composable
fun CategoriesScreenPreview() {
    TudeeTheme(
        isDarkTheme = false
    ) {
        CategoriesScreen { }
    }
}
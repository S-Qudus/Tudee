package com.qudus.tudee.ui.screen.categorysheet

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.qudus.tudee.R
import com.qudus.tudee.designSystem.component.text_field.TudeeTextField
import com.qudus.tudee.designSystem.component.text_field.TudeeTextFieldType
import com.qudus.tudee.ui.designSystem.component.PrimaryButton
import com.qudus.tudee.ui.designSystem.component.SecondaryButton
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.ui.util.UiImage
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel


@Composable
fun AddCategorySheetScreen(
    navController: NavController,
    viewModel: AddCategoryViewModel = koinViewModel()
) {
    val uiState by viewModel.state.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(viewModel.event) {
        viewModel.event.collect { event ->
            when (event) {
                is AddCategoryViewModel.Event.NavigateBack -> navController.popBackStack()
                is AddCategoryViewModel.Event.ShowError -> {
                    coroutineScope.launch {
                        // error
                    }
                }
            }
        }
    }

    AddCategorySheetContent(
        title = stringResource(R.string.add_new_category),
        initialCategoryName = uiState.title,
        initialCategoryImage = UiImage.fromString(uiState.image),
        isBottomSheetVisible = true,
        onBottomSheetDismissed = { viewModel.cancel() },
        onAddButtonClicked = { data ->
            viewModel.updateTitle(data.name)
            viewModel.updateImage(data.uiImage)
            viewModel.saveCategory()
        },
        onCancelButtonClicked = { viewModel.cancel() }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCategorySheetContent(
    modifier: Modifier = Modifier,
    title: String,
    initialCategoryName: String,
    initialCategoryImage: UiImage,
    isBottomSheetVisible: Boolean,
    onBottomSheetDismissed: () -> Unit,
    onAddButtonClicked: (CategoryData) -> Unit,
    onCancelButtonClicked: () -> Unit
) {
    var categoryName by remember(initialCategoryName) { mutableStateOf(initialCategoryName) }
    var selectedUiImage by remember(initialCategoryImage) { mutableStateOf(initialCategoryImage) }

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        uri?.let { selectedUiImage = UiImage.External(it.toString()) }
    }

    val sheetState = rememberModalBottomSheetState()

    LaunchedEffect(isBottomSheetVisible) {
        if (isBottomSheetVisible) sheetState.show()
        else sheetState.hide()
    }

    if (isBottomSheetVisible) {
        ModalBottomSheet(
            modifier = modifier,
            containerColor = Theme.color.surface,
            onDismissRequest = onBottomSheetDismissed,
            sheetState = sheetState
        ) {
            Column {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = title,
                        style = Theme.textStyle.title.large,
                        color = Theme.color.title
                    )

                    TudeeTextField(
                        value = categoryName,
                        onValueChange = { categoryName = it },
                        placeholder = stringResource(R.string.add_new_category),
                        type = TudeeTextFieldType.WithIcon(
                            icon = painterResource(R.drawable.icon_menu_circle)
                        )
                    )

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.category_image_desc),
                            style = Theme.textStyle.title.medium,
                            color = Theme.color.title,
                        )

                        val isImageSelected = selectedUiImage != initialCategoryImage

                        Box(
                            modifier = Modifier
                                .size(113.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(Color(0x1A000000))
                                .clickable {
                                    photoPickerLauncher.launch(
                                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                    )
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            if (isImageSelected) {
                                Image(
                                    painter = selectedUiImage.asPainter(),
                                    contentDescription = stringResource(R.string.category_image_desc),
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clip(RoundedCornerShape(16.dp)),
                                    contentScale = ContentScale.Crop
                                )
                            } else {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Icon(
                                        painter = painterResource(R.drawable.icon_upload),
                                        contentDescription = "Upload icon",
                                        tint = Theme.color.stroke,
                                        modifier = Modifier.size(24.dp)
                                    )
                                    Text(
                                        text = stringResource(R.string.upload),
                                        style = Theme.textStyle.body.small,
                                        color = Theme.color.stroke
                                    )
                                }
                            }
                        }
                    }
                }

                Column(
                    Modifier
                        .fillMaxWidth()
                        .background(Theme.color.surfaceHigh)
                        .padding(horizontal = 16.dp)
                        .padding(vertical = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    PrimaryButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            onAddButtonClicked(
                                CategoryData(
                                    name = categoryName.trim(),
                                    uiImage = selectedUiImage
                                )
                            )
                        },
                        isEnabled = categoryName.trim().isNotEmpty() && selectedUiImage != initialCategoryImage
                    ) {
                        Text(
                            text = stringResource(R.string.add),
                            style = Theme.textStyle.label.large,
                            color = Theme.color.onPrimary
                        )
                    }

                    SecondaryButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = onCancelButtonClicked
                    ) {
                        Text(
                            text = stringResource(R.string.cancel),
                            style = Theme.textStyle.label.large,
                            color = Theme.color.primary
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, wallpaper = Wallpapers.NONE)
@Composable
fun PreviewAddCategorySheetContent() {
    AddCategorySheetContent(
        title = "Add new category",
        initialCategoryName = "",
        initialCategoryImage = UiImage.Drawable(R.drawable.image_shy_tudee),
        isBottomSheetVisible = true,
        onBottomSheetDismissed = {},
        onAddButtonClicked = {},
        onCancelButtonClicked = {}
    )
}
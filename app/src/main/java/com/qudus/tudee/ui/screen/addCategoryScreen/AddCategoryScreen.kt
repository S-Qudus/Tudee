package com.qudus.tudee.ui.screen.addCategoryScreen

import AddCategoryUiState
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.qudus.tudee.R
import com.qudus.tudee.ui.designSystem.component.PrimaryButton
import com.qudus.tudee.ui.designSystem.component.SecondaryButton
import com.qudus.tudee.ui.designSystem.component.text_field.TudeeTextField
import com.qudus.tudee.ui.designSystem.component.text_field.TudeeTextFieldType
import com.qudus.tudee.ui.designSystem.theme.Dimension.spacing12
import com.qudus.tudee.ui.designSystem.theme.Dimension.spacing16
import com.qudus.tudee.ui.designSystem.theme.Dimension.spacing24
import com.qudus.tudee.ui.designSystem.theme.Dimension.spacing4
import com.qudus.tudee.ui.designSystem.theme.Dimension.spacing8
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.ui.screen.taskEditor.composable.getTitleErrorMessage
import com.qudus.tudee.ui.util.UiImage
import org.koin.androidx.compose.koinViewModel


@Composable
fun AddCategoryScreen(
    //navController: NavController,
    viewModel: AddCategoryViewModel = koinViewModel()
) {
    val uiState by viewModel.state.collectAsState()
    val coroutineScope = rememberCoroutineScope()
// todo there we will use navigate to control

  /*  LaunchedEffect(viewModel.event) {
        viewModel.event.collect { event ->
            when (event) {
                AddCategoryViewModel.Event.NavigateBack -> navController.popBackStack()
                is AddCategoryViewModel.Event.ShowError -> {

                }
            }
        }
    }*/

    AddCategorySheetContent(
        state = uiState,
        interaction = viewModel,
        isBottomSheetVisible = uiState.isSheetOpen,
        onBottomSheetDismissed = viewModel::onCancelClicked
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCategorySheetContent(
    modifier: Modifier = Modifier,
    state: AddCategoryUiState,
    interaction: AddCategoryInteraction,
    isBottomSheetVisible: Boolean,
    onBottomSheetDismissed: () -> Unit
) {
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        uri?.let { interaction.onImageSelected(it.toString()) }
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
                        .padding(horizontal = spacing16)
                        .padding(bottom = spacing24),
                    verticalArrangement = Arrangement.spacedBy(spacing12)
                ) {
                    Text(
                        text = stringResource(R.string.add_new_category),
                        style = Theme.textStyle.title.large,
                        color = Theme.color.title
                    )

                    TudeeTextField(
                        value = state.title,
                        onValueChange = interaction::onTitleValueChange,
                        placeholder = stringResource(R.string.add_new_category),
                        type = TudeeTextFieldType.WithIcon(
                            icon = painterResource(R.drawable.icon_menu_circle)
                        ),
                        primaryBordColor = if (state.titleErrorMessageType != null)
                            Theme.color.pinkAccent else Theme.color.primary
                    )

                    if (state.titleErrorMessageType != null) {
                        Text(
                            text = getTitleErrorMessage(state.titleErrorMessageType),
                            style = Theme.textStyle.label.small,
                            color = Theme.color.pinkAccent,
                            modifier = Modifier.padding(top = spacing4)
                        )
                    }

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(spacing8)
                    ) {
                        Text(
                            text = stringResource(R.string.category_image_desc),
                            style = Theme.textStyle.title.medium,
                            color = Theme.color.title,
                        )

                        Box(
                            modifier = Modifier
                                .size(113.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(Theme.color.TransparentBlack10)
                                .clickable {
                                    photoPickerLauncher.launch(
                                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                    )
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            if (state.image.isNotEmpty()) {
                                Image(
                                    painter = UiImage.fromString(state.image).asPainter(),
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
                        .padding(horizontal = spacing16, vertical = spacing12),
                    verticalArrangement = Arrangement.spacedBy(spacing12)
                ) {
                    PrimaryButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = interaction::onAddCategoryClicked,
                        isEnabled = state.isTitleValid && state.isImageValid,
                        isLoading = state.isLoading
                    ) {
                        Text(
                            text = stringResource(R.string.add),
                            style = Theme.textStyle.label.large,
                            color = Theme.color.onPrimary
                        )
                    }

                    SecondaryButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = interaction::onCancelClicked
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
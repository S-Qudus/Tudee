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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.qudus.tudee.R
import com.qudus.tudee.designSystem.component.text_field.TudeeTextField
import com.qudus.tudee.designSystem.component.text_field.TudeeTextFieldType
import com.qudus.tudee.ui.designSystem.component.PrimaryButton
import com.qudus.tudee.ui.designSystem.component.SecondaryButton
import com.qudus.tudee.ui.designSystem.theme.Theme
import com.qudus.tudee.ui.util.UiImage

data class CategoryData(
    val name: String,
    val uiImage: UiImage
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategorySheet(
    modifier: Modifier = Modifier,
    title: String,
    initialCategoryName: String,
    initialCategoryImage: UiImage,
    isBottomSheetVisible: Boolean,
    isEditMode: Boolean,
    onBottomSheetDismissed: () -> Unit,
    onSaveButtonClicked: (CategoryData) -> Unit,
    onCancelButtonClicked: () -> Unit,
    onDeleteButtonClicked: (() -> Unit)? = null,
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
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = title,
                            style = Theme.textStyle.title.large,
                            color = Theme.color.title
                        )
                        if (isEditMode && onDeleteButtonClicked != null) {
                            TextButton(onClick = onDeleteButtonClicked) {
                                Text(
                                    text = stringResource(R.string.delete),
                                    style = Theme.textStyle.label.large,
                                    color = Theme.color.error
                                )
                            }
                        }
                    }

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

                        val isImageSelected = isEditMode || selectedUiImage != initialCategoryImage

                        Box(
                            modifier = Modifier
                                .size(113.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(Color(0x1A000000))
                                .dashedBorder(
                                    color = Theme.color.stroke,
                                    shape = RoundedCornerShape(16.dp),
                                    strokeWidth = 2.dp,
                                    dashLength = 6.dp,
                                    gapLength = 6.dp,
                                    cap = StrokeCap.Round
                                )
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

                                if (isEditMode) {
                                    IconButton(onClick = {
                                        photoPickerLauncher.launch(
                                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                        )
                                    }) {
                                        Icon(
                                            painter = painterResource(R.drawable.icon_pencil_edit),
                                            contentDescription = stringResource(R.string.edit_image),
                                            modifier = Modifier
                                                .clip(RoundedCornerShape(12.dp))
                                                .background(Theme.color.surfaceHigh)
                                                .padding(6.dp)
                                                .size(20.dp),
                                            tint = Theme.color.secondary
                                        )
                                    }
                                }
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
                            onSaveButtonClicked(
                                CategoryData(
                                    name = categoryName.trim(),
                                    uiImage = selectedUiImage
                                )
                            )
                        },
                        isEnabled = isEditMode || (categoryName.trim().isNotEmpty() && selectedUiImage != initialCategoryImage)
                    )

                    {
                        Text(
                            text = stringResource(R.string.save),
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
@Preview(showBackground = true)
@Composable
fun PreviewCategorySheet() {
    CategorySheet(
        title = "Edit category",
        initialCategoryName = "Don't Be Shy :)",
        initialCategoryImage = UiImage.Drawable(R.drawable.image_shy_tudee),
        isBottomSheetVisible = true,
        isEditMode = true,
        onBottomSheetDismissed = {},
        onSaveButtonClicked = {},
        onCancelButtonClicked = {},
        onDeleteButtonClicked = {}
    )
}

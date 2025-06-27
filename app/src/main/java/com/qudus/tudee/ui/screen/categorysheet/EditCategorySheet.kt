package com.qudus.tudee.ui.screen.categorysheet

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.qudus.tudee.R
import com.qudus.tudee.ui.util.UiImage

@Composable
fun EditCategorySheet(
    isVisible: Boolean,
    onDismiss: () -> Unit,
    onSave: (CategoryData) -> Unit,
    onCancel: () -> Unit,
    onDelete: () -> Unit,
    initialName: String,
    initialImage: UiImage
) {
    CategorySheet(
        title = stringResource(R.string.edit_category),
        initialCategoryName = initialName,
        initialCategoryImage = initialImage,
        isBottomSheetVisible = isVisible,
        isEditMode = true,
        onBottomSheetDismissed = onDismiss,
        onSaveButtonClicked = onSave,
        onCancelButtonClicked = onCancel,
        onDeleteButtonClicked = onDelete
    )
}
@Preview(showBackground = true)
@Composable
fun PreviewEditCategorySheet() {
    EditCategorySheet(
        isVisible = true,
        onDismiss = {},
        onSave = {},
        onCancel = {},
        onDelete = {},
        initialName = "Don't Be Shy :)",
        initialImage = UiImage.Drawable(R.drawable.image_shy_tudee)
    )
}
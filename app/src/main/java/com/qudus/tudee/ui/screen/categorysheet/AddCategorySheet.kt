package com.qudus.tudee.ui.screen.categorysheet

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.qudus.tudee.R
import com.qudus.tudee.ui.util.UiImage

@Composable
fun AddCategorySheet(
    isVisible: Boolean,
    onDismiss: () -> Unit,
    onSave: (CategoryData) -> Unit,
    onCancel: () -> Unit
) {
    CategorySheet(
        title = stringResource(R.string.add_new_category),
        initialCategoryName = "",
        initialCategoryImage = UiImage.External(""),
        isBottomSheetVisible = isVisible,
        isEditMode = false,
        onBottomSheetDismissed = onDismiss,
        onSaveButtonClicked = onSave,
        onCancelButtonClicked = onCancel
    )
}
@Preview(showBackground = true)
@Composable
fun PreviewAddCategorySheet() {
    AddCategorySheet(
        isVisible = true,
        onDismiss = {},
        onSave = {},
        onCancel = {}
    )
}

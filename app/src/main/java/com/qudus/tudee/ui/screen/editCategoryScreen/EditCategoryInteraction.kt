package com.qudus.tudee.ui.screen.editCategoryScreen

interface EditCategoryInteraction {
    fun onTitleValueChange(newTitle: String)
    fun onImageSelected(imageUri: String)
    fun onSaveClicked()
    fun onCancelClicked()
    fun onDeleteClicked()
}
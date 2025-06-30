package com.qudus.tudee.ui.screen.addCategorySheet

interface AddCategoryInteraction {
    fun onTitleValueChange(newTitle: String)
    fun onImageSelected(imageUri: String)
    fun onAddCategoryClicked()
    fun onCancelClicked()
}
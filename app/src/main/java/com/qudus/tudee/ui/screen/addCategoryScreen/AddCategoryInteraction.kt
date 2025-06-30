package com.qudus.tudee.ui.screen.addCategoryScreen

interface AddCategoryInteraction {
    fun onTitleValueChange(newTitle: String)
    fun onImageSelected(imageUri: String)
    fun onAddCategoryClicked()
    fun onCancelClicked()
}
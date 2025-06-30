package com.qudus.tudee.ui.screen.addCategoryScreen

interface CategoryInteraction {
    fun onTitleValueChange(newTitle: String)
    fun onImageSelected(imageUri: String)
    fun onCancelClicked()
}

interface AddCategoryInteraction : CategoryInteraction {
    fun onAddCategoryClicked()
}

interface EditCategoryInteraction : CategoryInteraction {
    fun onSaveClicked()
    fun onDeleteClicked()
}
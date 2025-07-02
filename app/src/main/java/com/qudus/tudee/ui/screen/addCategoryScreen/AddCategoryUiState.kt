import com.qudus.tudee.ui.screen.taskEditor.TitleErrorType

data class AddCategoryUiState(
    val title: String = "",
    val image: String = "",
    val isTitleValid: Boolean = false,
    val isImageValid: Boolean = false,
    val isSheetOpen: Boolean = true,
    val isLoading: Boolean = false,
    val titleErrorMessageType: TitleErrorType? = null

) {

    companion object {
        fun validateTitle(title: String): Boolean {
            val trimmed = title.trim()
            return trimmed.length >= 3 && trimmed[0].isLetter()
        }
    }
}
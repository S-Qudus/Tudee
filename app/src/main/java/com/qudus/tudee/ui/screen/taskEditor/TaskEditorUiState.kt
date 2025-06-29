package com.qudus.tudee.ui.screen.taskEditor

import com.qudus.tudee.domain.entity.DefaultCategoryType
import com.qudus.tudee.domain.entity.Priority
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

data class TaskEditorUiState(
    val title: String = "",
    val description: String = "",
    val date: String = getCurrentDate(),
    val priorityUiStates: List<PriorityItemUiState> = PriorityItemUiState.defaultPriorityStates,
    val categoryUiStates: List<CategoryItemUiState> = emptyList(),
    val isSheetOpen: Boolean = true,
    val isDatePickerOpen: Boolean = false,
    val isPrimaryButtonEnabled: Boolean = false,
    val isLoading: Boolean = false,
    val titleErrorMessageType: TitleErrorType? = null,
    val categoryErrorMessageType: CategoryErrorType? = null
) {

    data class PriorityItemUiState(
        val type: Priority,
        val isSelected: Boolean = false
    ) {
        companion object {
            val defaultPriorityStates = listOf(
                PriorityItemUiState(type = Priority.HIGH),
                PriorityItemUiState(type = Priority.MEDIUM),
                PriorityItemUiState(type = Priority.LOW)
            )

            fun List<PriorityItemUiState>.selectByPriorityType(type: Priority): List<PriorityItemUiState> {
                return map { it.copy(isSelected = it.type == type) }
            }

            fun List<PriorityItemUiState>.isSameSelection(type: Priority): Boolean {
                return firstOrNull { it.isSelected }?.type == type
            }
        }
    }

    data class CategoryItemUiState(
        val id: Long,
        val imagePath: String = "",
        val title: String = "",
        val isSelected: Boolean = false,
        val defaultCategoryType: DefaultCategoryType? = null
    ){
        companion object{
            fun List<CategoryItemUiState>.selectById(id: Long): List<CategoryItemUiState> {
                return map { it.copy(isSelected = it.id == id) }
            }

            fun List<CategoryItemUiState>.isSameSelection(newId: Long): Boolean {
                return firstOrNull { it.isSelected }?.id == newId
            }
        }
    }


    companion object {
        fun getCurrentDate(): String {
            return Clock.System.now().toLocalDateTime(TimeZone.Companion.currentSystemDefault()).date.toString()
        }
    }
}
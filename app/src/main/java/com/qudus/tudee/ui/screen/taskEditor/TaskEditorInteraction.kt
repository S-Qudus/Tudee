package com.qudus.tudee.ui.screen.taskEditor

import com.qudus.tudee.domain.entity.Priority
import kotlinx.datetime.LocalDate

interface TaskEditorInteraction {
    fun onTitleValueChange(newTitle: String)
    fun onDescriptionValueChange(newDescription: String)

    fun onDateFieldClick()
    fun onDateSelected(selectedDate: LocalDate)
    fun onDatePickCancel()

    fun onPrioritySelectChange(priority: Priority)

    fun onCategoryTypeSelectChange(id: Long)

    fun onCancelChangeTask()
}
package com.qudus.tudee.ui.screen.editTask

import com.qudus.tudee.ui.screen.taskEditor.TaskEditorInteraction

interface EditTaskInteraction: TaskEditorInteraction {
    fun onEditTaskClicked()
}
package com.qudus.tudee.ui.screen.addTask

import com.qudus.tudee.ui.screen.taskEditor.TaskEditorInteraction

interface AddTaskInteraction: TaskEditorInteraction {
    fun onAddTaskClicked()
}
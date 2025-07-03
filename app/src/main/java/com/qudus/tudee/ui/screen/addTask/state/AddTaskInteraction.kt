package com.qudus.tudee.ui.screen.addTask.state

import com.qudus.tudee.ui.screen.taskEditor.TaskEditorInteraction

interface AddTaskInteraction : TaskEditorInteraction {
    fun onAddTaskClicked()
    fun onCancelAddTask()
} 
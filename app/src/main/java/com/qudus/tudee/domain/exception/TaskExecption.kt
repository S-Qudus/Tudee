package com.qudus.tudee.domain.exception

open class TaskException : TudeeExecption()

class TaskCreationFailedException : TaskException()

class TaskUpdateFailedException : TaskException()

class TaskDeletionFailedException : TaskException()

class TaskFetchAllFailedException : TaskException()

class TaskNotFoundException : TaskException()

class TaskReadFailedException : TaskException()

class EmptyTaskTitleException : TaskException()

class TaskStateChangeFailedException : TaskException()

package com.qudus.tudee.domain.exception

open class CategoryException : Exception()

class CategoryCreationFailedException : CategoryException()

class CategoryUpdateFailedException : CategoryException()

class CategoryDeletionFailedException : CategoryException()

class CategoryFetchAllFailedException : CategoryException()

class CategoryNotFoundException : CategoryException()

class CategoryReadFailedException : CategoryException()

class EmptyCategoryTitleException : CategoryException()


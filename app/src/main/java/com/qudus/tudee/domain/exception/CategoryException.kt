package com.qudus.tudee.domain.exception

open class CategoryException : TudeeExecption()

class CategoryCreationFailedException : CategoryException()

class CategoryUpdateFailedException : CategoryException()

class CategoryDeletionFailedException : CategoryException()

class CategoryFetchAllFailedException : CategoryException()

class CategoryNotFoundException : CategoryException()

class CategoryReadFailedException : CategoryException()

class EmptyCategoryTitleException : CategoryException()

class CategoryTitleMustStartWithLetterException : CategoryException()

class CategoryTitleTooShortException :CategoryException()




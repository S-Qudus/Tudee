package com.qudus.tudee.domain.exception

open class ValidationException() : TudeeExecption()

class EmptyInputException : ValidationException()
class InvalidStartCharacterException : ValidationException()
class InputTooLongException : ValidationException()
class InputTooShortException : ValidationException()
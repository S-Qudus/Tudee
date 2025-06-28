package com.qudus.tudee.data.service

import com.qudus.tudee.domain.exception.EmptyInputException
import com.qudus.tudee.domain.exception.InputTooLongException
import com.qudus.tudee.domain.exception.InputTooShortException
import com.qudus.tudee.domain.exception.InvalidStartCharacterException

class InputValidator {

    private val startWithLetterRegex = Regex("^[a-zA-Z\u0621-\u064A].*")

    fun validateTitle(input: String) {
        if (input.isBlank()) throw EmptyInputException()
        if (!startWithLetterRegex.matches(input)) throw InvalidStartCharacterException()
        if (input.length < 3) throw InputTooShortException()
        if (input.length > 100) throw InputTooLongException()
    }
}
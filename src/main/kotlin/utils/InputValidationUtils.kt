package utils

import kotlin.Exception

class UserRequestedExitException : Exception()

class InvalidChoiceException : Exception()

fun userRequestedExit(input: String): Boolean {
    return input.lowercase() == "exit"
}
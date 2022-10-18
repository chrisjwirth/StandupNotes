package utils

import java.lang.Exception

class UserRequestedExitException : Exception()

fun userRequestedExit(input: String): Boolean {
    return input.lowercase() == "exit"
}
package utils

import java.time.LocalDate

fun getDateToday(): String {
    return LocalDate.now().toString()
}
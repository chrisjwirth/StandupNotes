package notes

import storage.StorageManager.StorageFile
import storage.StorageManager.getStorageFile
import utils.getDateToday
import java.io.FileNotFoundException

object DailyNoteGetter {
    private val dateToday = getDateToday()

    fun getDailyNote() {
        printGetDailyNoteMessage()

        try {
            println(getStorageFile(StorageFile.StandupNote, dateToday).readText())
        } catch (e: FileNotFoundException) {
            println("You do not have a standup note for today.")
            return
        }

        readln()  // Wait for user to enter key before returning
    }

    private fun printGetDailyNoteMessage() {
        println()
        println("""
            Get Daily Standup Note-----------------------------------
            Enter any key to return to the main menu.
        """.trimIndent())
        println()
    }
}
package notes

import config.ConfigManager
import storage.StorageManager.StorageFile
import storage.StorageManager.getStorageFile
import utils.UserRequestedExitException
import utils.userRequestedExit
import java.util.*

object DailyNoteSetter {
    fun setDailyNote() {
        printSetDailyNoteMessage()

        if (ConfigManager.userConfig.freeformFormat) {
            setDailyNoteFreeformMethod()
        } else {
            setDailyNoteGuidedMethod()
        }
    }

    private fun setDailyNoteFreeformMethod() {
        val freeformNote: String

        try {
            freeformNote = getAndValidateNote("What is your freeform standup note?")
        } catch (e: UserRequestedExitException) {
            return
        }

        writeValidatedNoteToFile(freeformNote, StorageFile.Freeform)
    }

    private fun setDailyNoteGuidedMethod() {
        val yesterdayNote: String
        val todayNote: String
        val helpRequestNote: String

        try {
            yesterdayNote = getAndValidateNote("What did you do yesterday?")
            todayNote = getAndValidateNote("What are you planning on doing today?")
            helpRequestNote = getAndValidateNote("What do you need your team's help with?")
        } catch (e: UserRequestedExitException) {
            return
        }

        writeValidatedNoteToFile(yesterdayNote, StorageFile.Yesterday)
        writeValidatedNoteToFile(todayNote, StorageFile.Today)
        writeValidatedNoteToFile(helpRequestNote, StorageFile.HelpRequest)
    }

    private fun getAndValidateNote(message: String): String {
        val scanner = Scanner(System.`in`)
        val note = StringBuilder()

        println(message)

        while (true) {
            val line = scanner.nextLine()
            if (line.isEmpty()) break
            if (userRequestedExit(line)) throw UserRequestedExitException()
            note.append("$line \n")
        }

        return note.toString()
    }

    private fun writeValidatedNoteToFile(note: String, storageFile: StorageFile) {
        getStorageFile(storageFile).writeText(note)
    }

    private fun printSetDailyNoteMessage() {
        println()
        println(
            """
            Set Daily Standup Note-----------------------------------
            Enter a blank line to save.
            Enter 'exit' to leave without saving.
        """.trimIndent()
        )
        println()
    }
}
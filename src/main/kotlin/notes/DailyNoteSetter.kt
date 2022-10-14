package notes

import config.ConfigManager
import storage.StorageManager.getStorageFile
import storage.StorageManager.StorageFile
import utils.UserRequestedExitException
import utils.userRequestedExit

object DailyNoteSetter {
    fun setDailyNote() {
        printSetDailyNoteMessage()

        if (ConfigManager.userConfig.freeformNoteMethod) {
            setDailyNoteFreeformMethod()
        } else {
            setDailyNoteGuidedMethod()
        }
    }

    private fun setDailyNoteFreeformMethod() {
        val freeformNote: String

        try {
            freeformNote = getAndValidateNote("Type your standup note and press 'Enter' to save.")
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
        println(message)
        print("> ")

        val note = readln()

        if (userRequestedExit(note)) throw UserRequestedExitException()

        return note
    }

    private fun writeValidatedNoteToFile(note: String, storageFile: StorageFile) {
        getStorageFile(storageFile).writeText(note)
    }

    private fun printSetDailyNoteMessage() {
        println()
        println("""
            Set Daily Standup Note-----------------------------------
            Enter 'exit' to leave without saving.
        """.trimIndent())
        println()
    }
}

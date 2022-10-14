package notes

import config.ConfigManager
import storage.StorageManager.getStorageFile
import storage.StorageManager.StorageFile

object DailyNoteGetter {
    fun getDailyNote() {
        printGetDailyNoteMessage()

        if (ConfigManager.userConfig.freeformNoteMethod) {
            getDailyNoteFreeformMethod()
        } else {
            getDailyNoteGuidedMethod()
        }

        readln()  // Wait for user to enter key before returning
    }

    private fun getDailyNoteFreeformMethod() {
        printNoteFromFile("Your standup notes:", StorageFile.Freeform)
    }

    private fun getDailyNoteGuidedMethod() {
        printNoteFromFile("The work you completed yesterday:", StorageFile.Yesterday)
        printNoteFromFile("The work you are planning on doing today:", StorageFile.Today)
        printNoteFromFile("The work you need your team's help with:", StorageFile.HelpRequest)
    }

    private fun printNoteFromFile(message: String, storageFile: StorageFile) {
        println(message)
        println(getStorageFile(storageFile).readText())
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

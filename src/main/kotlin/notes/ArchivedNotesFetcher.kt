package notes

import storage.StorageManager.StorageFile
import storage.StorageManager.getStorageFile
import utils.UserRequestedExitException
import utils.userRequestedExit
import java.io.FileNotFoundException

object ArchivedNotesFetcher {
    fun fetchArchivedNotes() {
        printFetchArchivedNotesMessage()

        while (true) {
            printFetchArchivedNotesDatePrompt()
            val date = readln()
            try {
                displayArchivedNote(date)
            } catch (e: UserRequestedExitException) { return }
        }
    }

    private fun displayArchivedNote(date: String) {
        if (userRequestedExit(date)) throw UserRequestedExitException()
        try {
            val archivedStandupNote = getStorageFile(StorageFile.StandupNote, date).readText()
            println("Standup Notes for $date----------------------------------")
            println(archivedStandupNote)
            println("---------------------------------------------------------")
            println()
        } catch (e: FileNotFoundException) {
            println("You do not have any standup notes for that day.")
            println()
        }
    }

    private fun printFetchArchivedNotesDatePrompt() {
        println("""
                Enter the date of the archived note to fetch. 
                The date must be in YYYY-MM-DD format.
                Enter 'exit' to return to the main menu.
            """.trimIndent())
        print("> ")
    }

    private fun printFetchArchivedNotesMessage() {
        println()
        println("""
            Fetch Archived Standup Notes------------------------------
        """.trimIndent())
        println()
    }
}
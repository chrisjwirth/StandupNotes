package notes

import config.ConfigManager
import storage.StorageManager.StorageFile
import storage.StorageManager.getStorageFile
import utils.UserRequestedExitException
import utils.getDateToday
import utils.getExpandedTicket
import utils.userRequestedExit
import java.io.FileNotFoundException
import java.util.*

object DailyNoteSetter {
    private val dateToday = getDateToday()

    fun setDailyNote() {
        printSetDailyNoteMessage()

        displayExistingNoteIfExists()

        if (ConfigManager.userConfig.freeformFormat) {
            setDailyNoteFreeformMethod()
        } else {
            setDailyNoteGuidedMethod()
        }
    }

    private fun setDailyNoteFreeformMethod() {
        try {
            writeValidatedNoteToFile("""
                Your standup notes:
                ${getAndValidateNote("What is your freeform standup note?")}
            """.trimIndent())
        } catch (e: UserRequestedExitException) { return }
    }

    private fun setDailyNoteGuidedMethod() {
        try {
            writeValidatedNoteToFile("""
                The work you completed yesterday:
                ${getAndValidateNote("What did you do yesterday?")}
                The work you are planning on doing today:
                ${getAndValidateNote("What are you planning on doing today?")}
                The work you need your team's help with:
                ${getAndValidateNote("What do you need your team's help with?")}
            """.trimIndent())
        } catch (e: UserRequestedExitException) { return }
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

    private fun writeValidatedNoteToFile(note: String) {
        val noteToWrite = if (ConfigManager.userConfig.ticketTextExpansion) {
            noteWithExpandedTickets(note)
        } else {
            note
        }

        getStorageFile(StorageFile.StandupNote, dateToday).writeText(noteToWrite)
    }

    private fun noteWithExpandedTickets(note: String): String {
        val ticketsToExpand = "@{2}\\d+".toRegex()
        return ticketsToExpand.replace(note) { getExpandedTicket(it.value) }
    }

    private fun displayExistingNoteIfExists() {
        try {
            val existingDailyStandupNote = getStorageFile(StorageFile.StandupNote, dateToday).readText()
            println("""
                You already have the following standup note for today.
                To edit today's standup note, enter the new note below.
            """.trimIndent())
            println("Existing Standup Note------------------------------------")
            println(existingDailyStandupNote)
            println("---------------------------------------------------------")
        } catch (e: FileNotFoundException) { return }
    }

    private fun printSetDailyNoteMessage() {
        println()
        println("""
            Set Daily Standup Note-----------------------------------
            Enter a blank line to save.
            Enter 'exit' to leave without saving.
        """.trimIndent())
        println()
    }
}
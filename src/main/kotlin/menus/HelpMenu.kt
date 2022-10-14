package menus

import storage.StorageManager.getStorageFile
import storage.StorageManager.StorageFile

object HelpMenu {
    enum class HelpMenuChoice {
        Set,
        Get,
        Exit,
        Invalid;

        companion object {
            fun aliasOf(value: String): HelpMenuChoice {
                return when (value.lowercase()) {
                    "s", "set" -> Set
                    "g", "get" -> Get
                    "e", "exit" -> Exit
                    else -> Invalid
                }
            }
        }
    }

    fun launch() {
        while (true) {
            printHelpMenuMessage()
            print("> ")

            val helpFile = when (HelpMenuChoice.aliasOf(readln())) {
                HelpMenuChoice.Set -> getStorageFile(StorageFile.SetNotesHelp)
                HelpMenuChoice.Get -> getStorageFile(StorageFile.GetNotesHelp)
                HelpMenuChoice.Exit -> return
                HelpMenuChoice.Invalid -> {
                    println("Invalid choice")
                    continue
                }
            }
            println(helpFile.readText())
        }
    }

    private fun printHelpMenuMessage() {
        println()
        println("""
                Help Menu------------------------------------------------
                s, set                get help with setting standup notes
                g, get                get help with getting standup notes
                e, exit                           return to the main menu
                ---------------------------------------------------------
            """.trimIndent())
    }
}

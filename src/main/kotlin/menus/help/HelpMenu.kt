package menus.help

import storage.StorageFile
import storage.getStorageFile

fun helpMenu() {
    while (true) {
        println()
        println("Help Menu------------------------------------------------")
        println("s, set                get help with setting standup notes")
        println("g, get                get help with getting standup notes")
        println("e, exit                           return to the main menu")
        println("---------------------------------------------------------")

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
package notes

import config.Config
import storage.StorageFile
import storage.getStorageFile

fun setDailyNote() {
    println()
    println("Set Daily Standup Note-----------------------------------")

    if (Config.userConfig.freeformNoteMethod) {
        setDailyNoteFreeformMethod()
    } else {
        setDailyNoteGuidedMethod()
    }
}

fun setDailyNoteFreeformMethod() {
    println("Type your standup note and press 'Enter' to save.")
    print("> ")
    val freeformFile = getStorageFile(StorageFile.Freeform)
    freeformFile.writeText(readln())
}

fun setDailyNoteGuidedMethod() {
    println("What did you do yesterday?")
    print("> ")
    val yesterdayFile = getStorageFile(StorageFile.Yesterday)
    yesterdayFile.writeText(readln())

    println("What are you planning on doing today?")
    print("> ")
    val todayFile = getStorageFile(StorageFile.Today)
    todayFile.writeText(readln())

    println("What do you need your team's help with?")
    print("> ")
    val helpRequestFile = getStorageFile(StorageFile.HelpRequest)
    helpRequestFile.writeText(readln())
}
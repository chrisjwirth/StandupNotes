package notes

import config.Config
import storage.StorageFile
import storage.getStorageFile

fun getDailyNote() {
    println()
    println("Get Daily Standup Note-----------------------------------")

    if (Config.userConfig.freeformNoteMethod) {
        getDailyNoteFreeformMethod()
    } else {
        getDailyNoteGuidedMethod()
    }
}

fun getDailyNoteFreeformMethod() {
    val freeformFile = getStorageFile(StorageFile.Freeform)
    println(freeformFile.readText())
}

fun getDailyNoteGuidedMethod() {
    println("The work you completed yesterday:")
    val yesterdayFile = getStorageFile(StorageFile.Yesterday)
    println(yesterdayFile.readText())

    println("The work you are planning on doing today:")
    val todayFile = getStorageFile(StorageFile.Today)
    println(todayFile.readText())

    println("The work you need your team's help with:")
    val helpRequestFile = getStorageFile(StorageFile.HelpRequest)
    println(helpRequestFile.readText())
}
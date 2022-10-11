import config.Config
import menus.main.mainMenu
import notes.setDailyNote
import kotlin.system.exitProcess

fun main() {
    printOpeningMessage()

    if (Config.userConfig.dailyNoteMode) setDailyNote()

    mainMenu()

    exitProcess(0)
}

fun printOpeningMessage() {
    println()
    println("---------------------------------------------------------")
    println("                      STANDUP NOTES                      ")
    println("---------------------------------------------------------")
}
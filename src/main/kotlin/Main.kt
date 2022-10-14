import config.ConfigManager
import menus.MainMenu
import notes.DailyNoteSetter.setDailyNote
import kotlin.system.exitProcess

fun main() {
    printApplicationTitle()

    if (ConfigManager.userConfig.dailyNoteMode) setDailyNote()

    MainMenu.launch()

    exitProcess(0)
}

fun printApplicationTitle() {
    println()
    println("""
        ---------------------------------------------------------
                              STANDUP NOTES                      
        ---------------------------------------------------------
    """.trimIndent())
}

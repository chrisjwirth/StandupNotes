package menus

import config.ConfigManager
import notes.DailyNoteGetter.getDailyNote
import notes.DailyNoteSetter.setDailyNote

object MainMenu {
    enum class MainMenuChoice {
        Set,
        Get,
        Config,
        Help,
        Exit,
        Invalid;

        companion object {
            fun aliasOf(value: String): MainMenuChoice {
                return when(value.lowercase()) {
                    "s", "set" -> Set
                    "g", "get" -> Get
                    "c", "config" -> Config
                    "h", "help" -> Help
                    "e", "exit" -> Exit
                    else -> Invalid
                }
            }
        }
    }

    fun launch() {
        while (true) {
            printMainMenuMessage()
            print("> ")

            when (MainMenuChoice.aliasOf(readln())) {
                MainMenuChoice.Set -> setDailyNote()
                MainMenuChoice.Get -> getDailyNote()
                MainMenuChoice.Config -> ConfigManager.setConfig()
                MainMenuChoice.Help -> HelpMenu.launch()
                MainMenuChoice.Exit -> return
                MainMenuChoice.Invalid -> {
                    println("Invalid choice")
                    continue
                }
            }
        }
    }

    private fun printMainMenuMessage() {
        println()
        println("""
                Main Menu------------------------------------------------
                s, set                        set your daily standup note
                g, get                        get your daily standup note
                c, config                       configure the application
                h, help                              visit the help pages
                e, exit                                  exit the program
                ---------------------------------------------------------
            """.trimIndent())
    }
}

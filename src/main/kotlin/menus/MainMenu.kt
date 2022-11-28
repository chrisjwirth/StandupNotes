package menus

import config.ConfigManager
import notes.ArchivedNotesFetcher.fetchArchivedNotes
import notes.DailyNoteGetter.getDailyNote
import notes.DailyNoteSetter.setDailyNote
import utils.InvalidChoiceException
import utils.UserRequestedExitException

object MainMenu {
    enum class MainMenuChoice {
        Set,
        Get,
        Fetch,
        Config,
        Help,
        Exit,
        Invalid;

        companion object {
            fun aliasOf(value: String): MainMenuChoice {
                return when(value.lowercase()) {
                    "s", "set" -> Set
                    "g", "get" -> Get
                    "f", "fetch" -> Fetch
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
            try {
                processMenuChoice()
            } catch (e: UserRequestedExitException) {
                return
            } catch (e: InvalidChoiceException) {
                continue
            }
        }
    }

    private fun processMenuChoice() {
        when (MainMenuChoice.aliasOf(readln())) {
            MainMenuChoice.Set -> setDailyNote()
            MainMenuChoice.Get -> getDailyNote()
            MainMenuChoice.Fetch -> fetchArchivedNotes()
            MainMenuChoice.Config -> ConfigManager.setConfig()
            MainMenuChoice.Help -> HelpMenu.launch()
            MainMenuChoice.Exit -> throw UserRequestedExitException()
            MainMenuChoice.Invalid -> {
                println("Invalid choice")
                throw InvalidChoiceException()
            }
        }
    }

    private fun printMainMenuMessage() {
        println()
        println("""
                Main Menu------------------------------------------------
                s, set                        set your daily standup note
                g, get                        get your daily standup note
                f, fetch                     fetch archived standup notes
                c, config                       configure the application
                h, help                              visit the help pages
                e, exit                                  exit the program
                ---------------------------------------------------------
            """.trimIndent())
    }
}
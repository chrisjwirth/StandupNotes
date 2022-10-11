package menus.main

import config.Config
import menus.help.helpMenu
import notes.getDailyNote
import notes.setDailyNote

fun mainMenu() {
    while (true) {
        println()
        println("Main Menu------------------------------------------------")
        println("s, set                        set your daily standup note")
        println("g, get                        get your daily standup note")
        println("c, config                       configure the application")
        println("h, help                              visit the help pages")
        println("e, exit                                  exit the program")
        println("---------------------------------------------------------")
        print("> ")

        when (MainMenuChoice.aliasOf(readln())) {
            MainMenuChoice.Set -> setDailyNote()
            MainMenuChoice.Get -> getDailyNote()
            MainMenuChoice.Config -> Config.setConfig()
            MainMenuChoice.Help -> helpMenu()
            MainMenuChoice.Exit -> return
            MainMenuChoice.Invalid -> {
                println("Invalid choice")
                continue
            }
        }
    }
}
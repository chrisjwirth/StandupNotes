package menus.main

import menus.help.helpMenu
import store.getDailyNote
import store.setDailyNote

fun mainMenu() {

    while (true) {
        println()
        println("Main Menu--------------------------------------")
        println("s, set              set your daily standup note")
        println("g, get              get your daily standup note")
        println("h, help                    visit the help pages")
        println("e, exit                        exit the program")
        println("-----------------------------------------------")
        print("> ")

        when (MainMenuChoice.aliasOf(readln())) {
            MainMenuChoice.SET -> setDailyNote()
            MainMenuChoice.GET -> getDailyNote()
            MainMenuChoice.HELP -> helpMenu()
            MainMenuChoice.EXIT -> return
            MainMenuChoice.INVALID -> {
                println("Invalid choice")
                continue
            }
        }
    }
}
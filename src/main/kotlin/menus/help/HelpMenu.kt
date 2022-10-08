package menus.help

fun helpMenu() {

    println()
    println("Help Menu--------------------------------------")
    println("s, set      get help with setting standup notes")
    println("g, get      get help with getting standup notes")
    println("e, exit                 return to the main menu")
    println("-----------------------------------------------")

    while (true) {
        print("> ")

        when (HelpMenuChoice.aliasOf(readln())) {
            HelpMenuChoice.SET -> println("set help")
            HelpMenuChoice.GET -> println("get help")
            HelpMenuChoice.EXIT -> return
            HelpMenuChoice.INVALID -> {
                println("Invalid choice")
                continue
            }
        }
    }
}
import menus.main.mainMenu
import kotlin.system.exitProcess

fun main() {
    printOpeningMessage()
    mainMenu()
    exitProcess(0)
}

fun printOpeningMessage() {
    println()
    println("-----------------------------------------------")
    println("                 STANDUP NOTES                 ")
    println("-----------------------------------------------")
}
package store

import store.storage.getStorageFile

fun setDailyNote() {
    println()
    println("Set Daily Standup Note-------------------------")

    println("What did you do yesterday?")
    print("> ")
    val yesterday = getStorageFile("yesterday")
    yesterday.writeText(readln())

    println("What are you planning on doing today?")
    print("> ")
    val today = getStorageFile("today")
    today.writeText(readln())

    println("What do you need your team's help with?")
    print("> ")
    val helpRequest = getStorageFile("helpRequest")
    helpRequest.writeText(readln())
}
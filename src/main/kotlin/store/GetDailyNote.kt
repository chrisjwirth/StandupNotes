package store

import store.storage.getStorageFile

fun getDailyNote() {
    println()
    println("Get Daily Standup Note-------------------------")

    println("The work you completed yesterday:")
    val yesterday = getStorageFile("yesterday")
    println(yesterday.readText())

    println("The work you are planning on doing today:")
    val today = getStorageFile("today")
    println(today.readText())

    println("The work you need your team's help with:")
    val helpRequest = getStorageFile("helpRequest")
    println(helpRequest.readText())
}
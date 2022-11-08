package utils

import config.ConfigManager
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import kotlinx.coroutines.runBlocking

private const val TICKET_EXPANSION_MICROSERVICE_ENDPOINT = "http://127.0.0.1:5000/data"

fun getExpandedTicket(ticket: String): String = runBlocking {
    val client = HttpClient(CIO)
    val response: String = client.get(TICKET_EXPANSION_MICROSERVICE_ENDPOINT) {
        url {
            parameters.append("prefix", ConfigManager.userConfig.jiraPrefix!!)
            parameters.append("project", ConfigManager.userConfig.jiraProject!!)
            parameters.append("ticket", ticket.removePrefix("@@"))
        }
    }.body()

    val expandedTicket = "https[^\"']+".toRegex()
    return@runBlocking expandedTicket.find(response)!!.value
}
package config

import com.google.gson.Gson
import storage.StorageManager.StorageFile
import storage.StorageManager.getStorageFile

object ConfigManager {
    data class UserConfig(
        val freeformFormat: Boolean,
        val dailyMode: Boolean,
        val ticketTextExpansion: Boolean,
        val jiraPrefix: String?,
        val jiraProject: String?
    )

    private val userConfigFile = getStorageFile(StorageFile.UserConfig)
    var userConfig: UserConfig = Gson().fromJson(userConfigFile.readText(), UserConfig::class.java)

    private val setConfigMessage = """
            Configure Application------------------------------------
            Enter 'y' or 'n' to set each of the following options.
        """.trimIndent()
    private val freeformFormatMessage = """
            **Freeform Note Format**
            
            This format provides a single unprompted note
            opportunity.
            
            Enabling this format will disable the guided note
            prompts (enabled by default).

            Would you like to enable the freeform note format?
        """.trimIndent()
    private val dailyModeMessage = """
            **Daily Note Mode**
            This mode causes the application to initially bypass 
            the main menu, allowing you to more quickly set your
            daily standup note.
            
            Enabling this mode will require you to enter your 
            daily standup note (or exit without saving) to access
            the main menu.
           
            Would you like to enable the daily note mode?
        """.trimIndent()
    private val ticketTextExpansionMessage = """
            **Ticket Text Expansion**
            Ticket text expansion provides a streamlined way to 
            add the URL of your JIRA tickets to your daily 
            standup notes. When enabled and configured, the prefix 
            '@@' followed by your JIRA ticket number will be 
            replaced with the URL of that ticket.
            
            Would you like to enable ticket text expansion?
        """.trimIndent()
    private val jiraPrefixMessage = """
            **JIRA Ticket Prefix**
            The JIRA ticket prefix is the part of the URL before 
            'atlassian.net'. For example, for the following ticket 
            URL, the ticket prefix is 'chrisjwirth'. This is 
            required for ticket text expansion to function.
            
            'https://chrisjwirth.atlassian.net/browse/STANDUP-1'
            
            What is your JIRA ticket prefix?
        """.trimIndent()
    private val jiraProjectMessage = """
            **JIRA Project Prefix**
            The JIRA project is the part of the URL after 
            'atlassian.net/browse/'. For example, for the following 
            ticket URL, the JIRA prefix is 'STANDUP'.
            
            'https://chrisjwirth.atlassian.net/browse/STANDUP-1'
            
            What is your JIRA project?
        """.trimIndent()

    fun setConfig() {
        printMessage(setConfigMessage)

        val freeformFormat = enableConfigOption(freeformFormatMessage)
        val dailyNoteMode = enableConfigOption(dailyModeMessage)
        val ticketTextExpansion = enableConfigOption(ticketTextExpansionMessage)
        val jiraPrefix = if (ticketTextExpansion) getConfigData(jiraPrefixMessage) else null
        val jiraProject = if (ticketTextExpansion) getConfigData(jiraProjectMessage) else null

        userConfig = UserConfig(freeformFormat, dailyNoteMode, ticketTextExpansion, jiraPrefix, jiraProject)
        userConfigFile.writeText(Gson().toJson(userConfig, UserConfig::class.java))
    }

    private fun enableConfigOption(message: String): Boolean {
        printMessage(message)
        print("> ")
        while (true) {
            when (readln()) {
                "y" -> return true
                "n" -> return false
                else -> println("Invalid choice. Please enter 'y' or 'n'.")
            }
        }
    }

    private fun getConfigData(message: String): String {
        printMessage(message)
        print("> ")
        while (true) {
            val option = readln()
            if (option.isNotEmpty()) return option
        }
    }

    private fun printMessage(message: String) {
        println()
        println(message)
    }
}
package config

import com.google.gson.Gson
import storage.StorageManager.StorageFile
import storage.StorageManager.getStorageFile

object ConfigManager {
    data class UserConfig(
        val freeformFormat: Boolean,
        val dailyMode: Boolean
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

    fun setConfig() {
        printMessage(setConfigMessage)

        val freeformFormat = enableConfigOption(freeformFormatMessage)
        val dailyNoteMode = enableConfigOption(dailyModeMessage)

        userConfig = UserConfig(freeformFormat, dailyNoteMode)
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

    private fun printMessage(message: String) {
        println()
        println(message)
    }
}
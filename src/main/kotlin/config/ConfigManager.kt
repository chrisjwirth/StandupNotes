package config

import com.google.gson.Gson
import storage.StorageManager.getStorageFile
import storage.StorageManager.StorageFile

object ConfigManager {
    data class UserConfig(
        val freeformNoteMethod: Boolean,
        val dailyNoteMode: Boolean
    )

    private val userConfigFile = getStorageFile(StorageFile.UserConfig)
    var userConfig: UserConfig = Gson().fromJson(userConfigFile.readText(), UserConfig::class.java)

    fun setConfig() {
        printSetConfigMessage()

        println("Would you like to use the freeform note method?")
        print("> ")
        val guidedNoteMethod = enableConfigOption()

        println("Would you like StandupNotes to open in daily note mode?")
        print("> ")
        val dailyNoteMode = enableConfigOption()

        userConfig = UserConfig(guidedNoteMethod, dailyNoteMode)
        userConfigFile.writeText(Gson().toJson(userConfig, UserConfig::class.java))
    }

    private fun enableConfigOption(): Boolean {
        while (true) {
            when (readln().lowercase()) {
                "y" -> return true
                "n" -> return false
                else -> println("Invalid choice. Please enter 'y' or 'n'.")
            }
        }
    }

    private fun printSetConfigMessage() {
        println()
        println("""
            Configure Application------------------------------------
            Enter 'y' or 'n' to set each of the following options.
            Enter 'h' or 'help' to get more info about an option.
        """.trimIndent())
        println()
    }
}

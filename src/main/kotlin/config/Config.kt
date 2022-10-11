package config

import com.google.gson.Gson
import storage.StorageFile
import storage.getStorageFile

class Config {
    companion object {
        private val userConfigFile = getStorageFile(StorageFile.UserConfig)
        var userConfig: UserConfig = Gson().fromJson(userConfigFile.readText(), UserConfig::class.java)

        fun setConfig() {
            println()
            println("Configure Application------------------------------------")
            println("Enter 'y' or 'n' to set each of the following options.")
            println("Enter 'h' or 'help' to get more info about an option.")
            println()

            println("Would you like to use the freeform note method?")
            print("> ")
            val guidedNoteMethod = getValidatedUserInput()

            println("Would you like StandupNotes to open in daily note mode?")
            print("> ")
            val dailyNoteMode = getValidatedUserInput()

            userConfig = UserConfig(guidedNoteMethod, dailyNoteMode)
            userConfigFile.writeText(Gson().toJson(userConfig, UserConfig::class.java))
        }

        private fun getValidatedUserInput(): Boolean {
            while (true) {
                when (readln().lowercase()) {
                    "y" -> return true
                    "n" -> return false
                    else -> println("Invalid choice. Please enter 'y' or 'n'.")
                }
            }
        }
    }
}
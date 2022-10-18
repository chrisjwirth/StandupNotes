package storage

import java.io.File

object StorageManager {
    enum class StorageFile(val fileName: String) {
        Yesterday("notes/yesterday.txt"),
        Today("notes/today.txt"),
        HelpRequest("notes/helpRequest.txt"),
        Freeform("notes/freeform.txt"),
        GetNotesHelp("help/getNotesHelp.txt"),
        SetNotesHelp("help/setNotesHelp.txt"),
        UserConfig("userConfig.json"),

    }

    fun getStorageFile(storageFile: StorageFile): File {
        return File("src/main/kotlin/storage/files/${storageFile.fileName}")
    }
}
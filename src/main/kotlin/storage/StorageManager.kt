package storage

import java.io.File

object StorageManager {
    enum class StorageFile(val fileName: String, val fileType: String) {
        StandupNote("notes/", "txt"),
        GetNotesHelp("help/getNotesHelp", "txt"),
        SetNotesHelp("help/setNotesHelp", "txt"),
        UserConfig("userConfig", "json")
    }

    fun getStorageFile(storageFile: StorageFile, date: String? = null): File {
        return if (storageFile == StorageFile.StandupNote) {
            File("src/main/kotlin/storage/files/${storageFile.fileName}$date.${storageFile.fileType}")
        } else {
            File("src/main/kotlin/storage/files/${storageFile.fileName}.${storageFile.fileType}")
        }
    }
}
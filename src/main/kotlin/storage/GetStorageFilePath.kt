package storage

import java.io.File

const val STORAGE_DIRECTORY_PATH = "src/main/kotlin/storage/files/"

fun getStorageFile(storageFile: StorageFile): File {
    return File("$STORAGE_DIRECTORY_PATH${storageFile.fileName}")
}
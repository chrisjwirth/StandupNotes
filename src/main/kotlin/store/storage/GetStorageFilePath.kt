package store.storage

import java.io.File

const val STORAGE_DIRECTORY_PATH = "src/main/kotlin/store/storage/"

fun getStorageFile(fileName: String): File {
    return File("$STORAGE_DIRECTORY_PATH$fileName.txt")
}
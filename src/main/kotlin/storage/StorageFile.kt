package storage

enum class StorageFile(val fileName: String) {
    Yesterday("notes/yesterday.txt"),
    Today("notes/today.txt"),
    HelpRequest("notes/helpRequest.txt"),
    Freeform("notes/freeform.txt"),
    GetNotesHelp("help/getNotesHelp.txt"),
    SetNotesHelp("help/setNotesHelp.txt"),
    UserConfig("userConfig.json"),

}
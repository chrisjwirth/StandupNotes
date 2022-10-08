package menus.main

enum class MainMenuChoice {
    SET,
    GET,
    HELP,
    EXIT,
    INVALID;

    companion object {
        fun aliasOf(value: String): MainMenuChoice {
            return when(value.lowercase()) {
                "s", "set" -> SET
                "g", "get" -> GET
                "h", "help" -> HELP
                "e", "exit" -> EXIT
                else -> INVALID
            }
        }
    }
}
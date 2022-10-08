package menus.help

enum class HelpMenuChoice {
    SET,
    GET,
    EXIT,
    INVALID;

    companion object {
        fun aliasOf(value: String): HelpMenuChoice {
            return when(value.lowercase()) {
                "s", "set" -> SET
                "g", "get" -> GET
                "e", "exit" -> EXIT
                else -> INVALID
            }
        }
    }
}
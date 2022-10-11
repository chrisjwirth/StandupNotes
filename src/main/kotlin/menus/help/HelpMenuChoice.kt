package menus.help

enum class HelpMenuChoice {
    Set,
    Get,
    Exit,
    Invalid;

    companion object {
        fun aliasOf(value: String): HelpMenuChoice {
            return when(value.lowercase()) {
                "s", "set" -> Set
                "g", "get" -> Get
                "e", "exit" -> Exit
                else -> Invalid
            }
        }
    }
}
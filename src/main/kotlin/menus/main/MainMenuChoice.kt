package menus.main

enum class MainMenuChoice {
    Set,
    Get,
    Config,
    Help,
    Exit,
    Invalid;

    companion object {
        fun aliasOf(value: String): MainMenuChoice {
            return when(value.lowercase()) {
                "s", "set" -> Set
                "g", "get" -> Get
                "c", "config" -> Config
                "h", "help" -> Help
                "e", "exit" -> Exit
                else -> Invalid
            }
        }
    }
}
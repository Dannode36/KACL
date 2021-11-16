package command

data class Command(val name: String, val desc: String, val action: (List<String>) -> String)

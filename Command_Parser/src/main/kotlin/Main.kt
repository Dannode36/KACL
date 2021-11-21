import command.Command
import command.CommandInit

val foundCommands = mutableListOf<Triple<Command?, MutableList<String>, Int?>>()
val categories: Map<String, Map<String, Command>> = CommandInit.comInit()
fun main() {
    println("------------------------------------------------------------------------")
    println("Welcome to Dannode36's command.Command Line. Type '.help' for a list of commands")
    println("------------------------------------------------------------------------")

    while (true){
        var input = readLine()?.trim()?.split(Regex(" +"))?.toMutableList()!!

        if (input[0] == "stop" || input[0] == "exit" || input[0] == "exeunt"){
            println("Process Terminated...")
            break
        }

        while (true){
            var hasFoundCommand = false
            val tempInput = input.toMutableList()
            for (i in input.asReversed()) {
                val command = findCommand(i, categories)
                val index = tempInput.indexOf(i)
                val arguments: MutableList<String> = emptyList<String>().toMutableList()

                var parsed: Triple<Command?, MutableList<String>, Int?>

                if (i.contains(Regex(";\$"))) {
                    arguments.clear()
                    arguments.add(i.removeSuffix(";"))
                    println("Semicolon Found")
                }
                else if (command == null) {
                    println("Found Argument")
                    arguments.add(i)
                    continue
                }
                else {
                    hasFoundCommand = true
                    parsed = Triple(command, arguments, index)
                    foundCommands.add(parsed)
                    var replaceString = ""
                    for (str in parsed.second.asReversed()) {
                        replaceString += str + ""
                    }
                    input[tempInput.indexOf(i)] =
                        i.replace(replaceString.trim(), parsed.first.action.invoke(parsed.second))
                }
                println(arguments)
                println(input)
                println(foundCommands)
            }
            println(hasFoundCommand)
            if (!hasFoundCommand){
                println("Output $input")
                break
            }
        }
    }
}

fun findCommand(input: String, categories: Map<String, Map<String, Command>>): Command? {
    for (cat in categories){
        if(cat.value.containsKey(input)){
            return cat.value[input]
        }
    }
    return null
}

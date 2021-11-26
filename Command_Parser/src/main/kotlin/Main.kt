import kotlinx.coroutines.*
import command.Command
import command.CommandInit

val categories: Map<String, Map<String, Command>> = CommandInit.comInit()


 @DelicateCoroutinesApi
fun main(args: Array<String>) {
    println("------------------------------------------------------------------------")
    println("Welcome to Dannode36's Command Line. Type '.help' for a list of commands")
    println("------------------------------------------------------------------------")

    while (true) {
        var input = readLine()?.trim()?.split(Regex(" +")) ?: continue

        input = input.toMutableList()

        if (input[0] == "stop" || input[0] == "exit" || input[0] == "exeunt") {
            println("Process Terminated...")
            break
        }

        val modInput = input.toMutableList()
        var output = ""

        while (true) {
            var hasFoundCommand = false
            val arguments = emptyList<String>().toMutableList()
            val removableArguments = mutableListOf<String>()

            for (i in modInput.reversed()) {
                val foundCommand = findCommand(i, categories)

                if (foundCommand == null) {
                    if (i.contains(Regex(";\$"))) {
                        arguments.clear()
                        removableArguments.clear()
                        arguments.add(i.removeSuffix(";"))
                    }
                    arguments.add(i.removeSuffix(";"))
                    removableArguments.add(i)
                    output = i
                }
                else {
                    hasFoundCommand = true

                    for (str in removableArguments) {
                        modInput.asReversed().removeAt(modInput.asReversed().indexOf(str))
                    }
                    removableArguments.clear()

                    modInput.asReversed()[modInput.asReversed().indexOf(i)] =
                        foundCommand.action.invoke(arguments.asReversed())
                    break
                }
            }
            //input = modInput.toMutableList()
            if (!hasFoundCommand) {
                println("Out: $output")
                break
            }
        }
    }
}

//   .repeat .repeat hello 2; 2
fun findCommand(input: String, categories: Map<String, Map<String, Command>>): Command? {
    for (cat in categories){
        if(cat.value.containsKey(input)){
            return cat.value[input]
        }
    }
    return null
}

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

        GlobalScope.launch {
            while (true) {
                var hasFoundCommand = false
                val arguments = emptyList<String>().toMutableList()
                val removableArguments = mutableListOf<String>()
                for (i in input.reversed()) {
                    val foundCommand = findCommand(i, categories)

                    if (foundCommand == null) {
                        if (i.contains(Regex(";\$"))) {
                            arguments.clear()
                            removableArguments.clear()

                            arguments.add(i.removeSuffix(";"))
                        } else {
                            arguments.add(i)
                        }
                        removableArguments.add(i)
                        output = i
                    } else {
                        hasFoundCommand = true
                        //println(modInput)
                        //println(arguments)

                        for (str in removableArguments) {
                            modInput.removeAt(modInput.indexOf(str))
                        }
                        modInput.asReversed()[modInput.asReversed().indexOf(i)] =
                            foundCommand.action.invoke(arguments.asReversed())
                    }
                }
                input = modInput.toMutableList()
                if (!hasFoundCommand) {
                    println(output)
                    break
                }
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

import command.Command
import command.CommandInit
import command.tools.ConfigParser
import kotlinx.coroutines.*

val categories: Map<String, Map<String, Command>> = CommandInit.comInit()


 @DelicateCoroutinesApi
suspend fun main(args: Array<String>){

    ConfigParser.setPrefs()

    println("------------------------------------------------------------------------")
    println("Welcome to Dannode36's Command Line. Type '.help' for a list of commands")
    println("------------------------------------------------------------------------")
    while (true) {
        var input = readLine()?.trim()?.split(Regex(" +")) ?: continue

        if (input[0].isEmpty()){
            continue
        }
        if (input[0] == "stop" || input[0] == "exit" || input[0] == "exeunt") {
            println("Process Terminated...")
            break
        }

        input = input.toMutableList()

        val modInput = input.toMutableList()
        /*var count = 0
        while (count < modInput.count()){
            modInput[count] += " "
            count++
        }*/

        var output = ""

        var containsCommand = false
        while (true) {
            var foundCommandThisIter = false
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
                    foundCommandThisIter = true
                    containsCommand = true
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
            if (!foundCommandThisIter) {
                if (containsCommand){
                    println(output)
                    break
                }
                else{
                    println("ERROR: No command found. Type '.help' for a list of commands")
                    break
                }
            }
        }
    }
    println("Press enter to continue...")
    readLine()
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

import command.Command
import command.CommandInit

val categories: Map<String, Map<String, Command>> = CommandInit.comInit()
fun main() {
    println("------------------------------------------------------------------------")
    println("Welcome to Dannode36's command.Command Line. Type '.help' for a list of commands")
    println("------------------------------------------------------------------------")

    while (true){
        var input = readLine()?.trim()?.split(Regex(" +")) ?: continue

        input = input.toMutableList()

        if (input[0] == "stop" || input[0] == "exit" || input[0] == "exeunt"){
            println("Process Terminated...")
            break
        }

        var modInput = input.toMutableList()
        var output = ""
        while (true){
            var hasFoundCommand = false
            val arguments = emptyList<String>().toMutableList()
            for (i in input.reversed()){
                val foundCommand = findCommand(i, categories)

                if (foundCommand == null) {
                    if (i.contains(Regex(";\$"))){
                        arguments.clear()
                        arguments.add(i.removeSuffix(";"))
                       // println("Semicoloned argument found")
                    }
                    else{
                        //println("Found Argument")
                        arguments.add(i)
                    }
                    output = i
                    modInput.removeAt(modInput.indexOf(i))
                }
                else{
                    hasFoundCommand = true
                    //arguments.add("herlp")
                    //println("Index: " + modInput.indexOf(i))
                    modInput[modInput.indexOf(i)] = foundCommand.action.invoke(arguments)

                }
            }
            input = modInput.toMutableList()
            if (!hasFoundCommand){
                println(output)
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

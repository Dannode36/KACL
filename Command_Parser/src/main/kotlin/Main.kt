import command.Command
import command.CommandInit

fun main() {
    val categories = CommandInit.comInit()

    println("------------------------------------------------------------------------")
    println("Welcome to Dannode36's command.Command Line. Type '.help' for a list of commands")
    println("------------------------------------------------------------------------")

    while (true){
        var input = readLine()?.trim()?.split(Regex(" +"))

        input = input?.toMutableList()
        if (input == null){
            continue
        }
        else if (input[0] == "stop" || input[0] == "exit" || input[0] == "exeunt"){
            println("Process Terminated...")
            break
        }

        val tempInput = input.toMutableList()

        //First is the parsed command, Second are the arguments, third is the index of the command
        val foundCommands = mutableListOf<Triple<Command?, MutableList<String>, Int?>>()

//        for (i in tempInput){
//            for (cat in categories){
//                if(cat.value.containsKey(i)){
//                    foundCommands.add(Triple(cat.value[i], "", tempInput.indexOf(i)))
//                    tempInput[tempInput.indexOf(i)] = i.replace('.', ' ', true)
////                    println("i")
////                    println(Pair(cat.value[i], input.indexOf(i)))
//                }
//            }
//        }
//        println(foundCommands)
//        println(tempInput)
//        println(input)

        for (i in input.asReversed()){
            val command = findCommand(i, categories)

            val index = tempInput.indexOf(i)

            val arguments: MutableList<String> = emptyList<String>().toMutableList()

            var parsed: Triple<Command?, MutableList<String>, Int?>


            if (command == null){
                arguments.add(i)
                continue
            }
            else{
                parsed = Triple(command, arguments, index)
                foundCommands.add(parsed)
                input[tempInput.indexOf(i)] = i.replace(parsed.first.name, parsed.first.action.invoke(parsed.second))
            }
            println(arguments)
        }
        println(foundCommands)
        println(tempInput)
        println(input)
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

import command.Command
import command.CommandInit

fun main() {
    val categories = CommandInit.comInit()

    //categories["Tools"]?.get("build")?.action?.invoke(listOf(""))
    //categories["Tools"]?.get("build")?.action?.invoke(listOf(""))
    println("------------------------------------------------------------------------")
    println("Welcome to Dannode36's command.Command Line. Type 'help' for a list of commands")
    println("------------------------------------------------------------------------")
    while (true){
        var input = readLine()?.trim()?.split(Regex(" +"))

        input = input?.toMutableList()

        if (input == null){
            continue
        }

        if (input[0] == "stop" || input[0] == "exit" || input[0] == "exeunt"){
            break
        }

        var curCom: Command? = null

        for (cat in categories){
            if(cat.value.containsKey(input[0])){
                curCom = cat.value[input[0]]
                break
            }
        }

        if (curCom != null){
            println(curCom.action.invoke(input))
        }
        else{
            println("command.Command '${input[0]}' not found. Try the 'help' command for a list of all commands")
        }
    }
}

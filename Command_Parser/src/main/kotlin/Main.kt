import command.CommandInit
import command.lib.Tools

fun main() {
    var categories = CommandInit.comInit()

    //categories["Tools"]?.get("build")?.action?.invoke(listOf(""))
    //categories["Tools"]?.get("build")?.action?.invoke(listOf(""))
    println("------------------------------------------------------------------------")
    println("Welcome to Danndode36's Command Line. Type 'help' for a list of commands")
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
            curCom.action.invoke(input)
        }
        else{
            println("Command '${input[0]}' not found. Try the 'help' command for a list of all commands")
        }
    }
}

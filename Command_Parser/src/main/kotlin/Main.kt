import command.CommandInit

fun main() {
    //<Commands>
    var categories = CommandInit.comInit()
    println("------------------------------------------------------------------------")
    println("Welcome to Danndode36's Command Line. Type 'help' for a list of commands")
    println("------------------------------------------------------------------------")
    while (true){
        var input = readLine()?.trim()?.split(Regex(" +"))

        input = input?.toMutableList()

        if (input == null){
            continue
        }

        var curCom: Command? = null

        for (cat in categories){
            if(cat.value.containsKey(input[0])){
                println("Found Command")
                curCom = cat.value[input[0]]
                break
            }
            println("Checked")
        }

        if (curCom != null){
            curCom.action.invoke(input)
        }
        else{
            println("Command '${input[0]}' not found. Try the 'help' command for a list of all commands")
        }
    }
}

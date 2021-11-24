fun main() {
    //<Commands>
    var commands = CommandInit.comInit()
    println("------------------------------------------------------------------------")
    println("Welcome to Danndode36's Command Line. Type 'help' for a list of commands")
    println("------------------------------------------------------------------------")
    while (true){
        var input = readLine()?.trim()?.split(Regex(" +"))

        input = input?.toMutableList()

        if (input == null){
            continue
        }
        var curCom = commands[input[0]]

        if (curCom != null){
            curCom.action.invoke(input)
        }
        else{
            println("Command '${input[0]}' nost found. Try the 'help' command for a list of all commands")
        }
    }
}

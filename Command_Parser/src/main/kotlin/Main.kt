fun main() {
    //<Commands>
    var commands = CommandInit.Com_Init()

    while (true){
        var input = readLine()?.split(" ")
        if (input == null){
            continue
        }
        var curCom = commands[input[0]]

        if (curCom != null){
            curCom.action.invoke(input)
        }
        else{
            println("Command '${input[0]}' not found. Try the 'help' command for a list of all commands")
        }
    }
}

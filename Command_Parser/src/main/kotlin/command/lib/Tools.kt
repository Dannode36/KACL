package command.lib

import Command
import command.CommandInit
import command.tools.DateTimeGetter

object Tools {
    const val name = "Tools"

    val help = Command("help", "list all available commands") { input ->
        println("------------------------------------------------------------------------")
        for (category in CommandInit.categories) {
            println(category.key + ":")
            for (command in category.value){
                println("    " + command.value.name + ": " + command.value.desc)
            }
        }
        println("------------------------------------------------------------------------")
    }

    val dt = Command("dt", "" +
            "(dt) prints OS date and time [-t prints only time] [-d prints only date]") { input ->

        if (input.contains("-d")) {
            println("Current date: ${DateTimeGetter.GetDate()}")
        }
        else if (input.contains("-t")) {
            println("Current time: ${DateTimeGetter.GetTime()}")
        }
        else {
            println("Current date and time: ${DateTimeGetter.GetDateTime()}")
        }
    }

    val commands = mapOf<String, Command>(
        help.name to help,
        dt.name to dt
    )
}

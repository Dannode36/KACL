package command.lib

import Command
import command.CommandInit
import java.time.LocalDateTime

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
        val timeNow = LocalDateTime.now()
        //var date = LocalDateTime.now()

        if (input.contains("-d")) {
            var time = timeNow.toString()
            time = time.substring(0, time.indexOf("T"))
            time = time.replace('.', ' ')
            println("Current date: $time")
        } else if (input.contains("-t")) {
            var time = timeNow.toString()
            time = time.substring(time.indexOf("T"))
            time = time.substring(0, time.indexOf("."))
            time = time.replace('T', ' ').trim()
            println("Current time: $time")
        } else {
            var time = timeNow.toString().replace('T', ' ')
            time = time.substring(0, time.indexOf("."))
            println("Current date and time: $time")
        }
    }

    val commands = mapOf<String, Command>(
        help.name to help,
        dt.name to dt
    )
}
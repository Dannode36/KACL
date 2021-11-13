package command.lib

import Command
import command.CommandInit
import command.tools.DateTimeGetter
import java.io.File

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

    val build = Command("build", "Creates a .bat file for running the skcp.jar") {
        File("run.bat").writeText("java -jar Command_Parser-${java.io.File("build.gradle.kts").readLines(kotlin.text.Charsets.UTF_8)[8].split('"')[1]}.jar")
    }

    val commands = mapOf<String, Command>(
        help.name to help,
        dt.name to dt,
        build.name to build
    )
}

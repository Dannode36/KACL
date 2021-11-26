package command.lib

import command.Command
import command.CommandInit
import command.tools.DateTimeGetter

object Tools {
    const val name = "Tools"

    private val help = Command(".help", "list all available commands") {
        var output = ""
        output += "------------------------------------------------------------------------" + System.lineSeparator()
        for (category in CommandInit.categories) {
            output += category.key + ":" + System.lineSeparator()
            for (command in category.value){
                output += "    " + command.value.name + ": " + command.value.desc + System.lineSeparator()
            }
        }
        output += "------------------------------------------------------------------------" + System.lineSeparator()
        return@Command output
    }

    private val dt = Command(".dt", "" +
            "(dt) prints OS date and time [-t prints only time] [-d prints only date]") { args ->

        if (args.contains("-d")) {
            return@Command "Current date: ${DateTimeGetter.getDate()}"
        }
        else if (args.contains("-t")) {
            return@Command "Current time: ${DateTimeGetter.getTime()}"
        }
        else {
            return@Command "Current date and time: ${DateTimeGetter.getDateTime()}"
        }
    }

//    private val build = Command("build", "Creates a .bat file for running the skcp.jar") {
//        File("run.bat").writeText("java -jar
//        Command_Parser-${File("build.gradle.kts").readLines(Charsets.UTF_8)[8].split('"')[1]}.jar")
//    }

    val commands = mapOf(
        help.name to help,
        dt.name to dt,
        //build.name to build
    )
}
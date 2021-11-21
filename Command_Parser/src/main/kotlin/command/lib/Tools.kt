package command.lib

import command.Command
import command.CommandInit
import command.tools.DateTimeGetter

object Tools {
    const val name = "Tools"

    private val help = Command(".help", "list all available commands") {
        var print = ""
        print += "------------------------------------------------------------------------" + System.lineSeparator()
        for (category in CommandInit.categories) {
            print += category.key + ":" + System.lineSeparator()
            for (command in category.value){
                print += "    " + command.value.name + ": " + command.value.desc + System.lineSeparator()
            }
        }
        print += "------------------------------------------------------------------------" + System.lineSeparator()
        return@Command print
    }

    private val dt = Command(".dt", "" +
            "(dt) prints OS date and time [-t prints only time] [-d prints only date]") { input ->

        if (input.contains("-d")) {
            return@Command "Current date: ${DateTimeGetter.getDate()}"
        }
        else if (input.contains("-t")) {
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

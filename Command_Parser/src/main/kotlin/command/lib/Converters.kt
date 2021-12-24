package command.lib

import command.Command
import command.tools.StringToBinary

object Converters {
    const val name = "Converters"

    private val tbin = Command(".tbin", "(tbin <input>) converts a string to a binary sequence"){ args ->
        val strtb = StringToBinary()
        var content = ""
        // println(Integer.toBinaryString(input.toInt()));
        if (args.count() > 1) {
            for (string in args) {
                content += "$string "
            }
            return@Command strtb.strToBinary(content.trim())
        }
        else if (args.count() == 1) {
            return@Command strtb.strToBinary(args[0])
        }
        else if (args.count() < 2) {
            //println("ERROR: Please enter the text you want to convert")
            return@Command "ERROR: Please enter the text you want to convert"
        }
        return@Command "Something bad happened"
    }

//    private val tstr = Command(
//        "!tstr", "" +
//                "(!tstr <input>) converts a binary sequence to a string !NOT IMPLEMENTED!"
//    ) { input ->
//        StringToBinary.strToBinary(input[1])
//        return@Command Integer.toBinaryString(input.toInt())
//    }

    val commands = mapOf(
        tbin.name to tbin,
        //tstr.name to tstr
    )
}

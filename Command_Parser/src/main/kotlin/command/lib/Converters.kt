package command.lib

import command.Command
import command.tools.StringToBinary

object Converters {
    const val name = "Converters"

    private val tbin = Command("tbin", "(tbin <input>) converts a string to a binary sequence"){ input ->
        var i = 0
        var content = ""
        // println(Integer.toBinaryString(input.toInt()));
        if (input.count() > 2) {
            for (string in input) {
                if (i > 0) {
                    content += "$string "
                }
                i++
            }
            StringToBinary.strToBinary(content.trim())
            return@Command "/n"
        }
        else if (input.count() == 2) {
            StringToBinary.strToBinary(input[1])
            return@Command "/n"
        }
        else if (input.count() < 3) {
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

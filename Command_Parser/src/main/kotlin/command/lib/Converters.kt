package command.lib

import Command
import command.tools.StringToBinary
import java.io.File

object Converters {
    const val name = "Converters"

    val tbin = Command("tbin", "(tbin <input>) converts a string to a binary sequence") { input ->

        var i = 0
        var content: String = ""
        // println(Integer.toBinaryString(input.toInt()));
        if (input.count() > 2) {
            for (string in input) {
                if (i > 0) {
                    content += "$string "
                }
                i++
            }
            StringToBinary.strToBinary(content.trim())
            println("")
        }
        else if (input.count() == 2) {
            StringToBinary.strToBinary(input[1])
            println("")
        }
        else if (input.count() < 3) {
            println("ERROR: Please enter the text you want to convert")
        }
    }

    val tstr = Command(
        "!tstr", "" +
                "(!tstr <input>) converts a binary sequence to a string !NOT IMPLEMENTED!"
    ) { input ->
        StringToBinary.strToBinary(input[1])
        // println(Integer.toBinaryString(input.toInt()));
    }

    val commands = mapOf(
        tbin.name to tbin,
        tstr.name to tstr
    )
}

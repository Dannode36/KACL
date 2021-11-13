package command.lib

import Command
import command.tools.StringToBinary

object Converters {
    const val name = "Converters"

    val tbin = Command("tbin", "(tbin <input>) converts a string to a binary sequence") { input ->

        StringToBinary.strToBinary(input[1])
        // println(Integer.toBinaryString(input.toInt()));
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

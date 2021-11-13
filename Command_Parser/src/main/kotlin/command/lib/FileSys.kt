package command.lib

import Command
import java.io.File
import java.io.FileNotFoundException

object FileSys {
    const val name = "File System"

    val wf = Command(
        "wf", "" +
                "(wf <file name> <content>) Writes to a new text file with the specified name and content"
    ) { input ->
        var content = ""
        var i = 0
        if (input.count() > 3) {
            for (string in input) {
                if (i > 1) {
                    content += "$string "
                }
                i++
            }
            File("${input[1]}.txt").writeText(content.trim())
        } else if (input.count() == 3) {
            content = input[2]
            File("${input[1]}.txt").writeText(content.trim())
        } else if (input.count() < 3) {
            println("ERROR: Please specify a file name and the file content")
        }
    }

    val rf = Command(
        "rf", "" +
                "(rf <file path>) Reads a text file with from specified path and prints the content"
    ) { input ->
        try {
            if (input.count() >= 2) {
                println(File(input[1]).readText(Charsets.UTF_8))
            } else {
                println("ERROR: Please specify a file name")
            }
        } catch (e: FileNotFoundException) {
            println("ERROR: Could not find the file \"${input[1]}\"")
        }
    }

    val commands = mapOf(
        wf.name to wf,
        rf.name to rf
    )
}
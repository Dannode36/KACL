package command.lib

import command.Command
import java.io.File
import java.io.FileNotFoundException

object FileSys {
    const val name = "File System"

    private val wf = Command(
        ".wf", "" +
                "(wf <file name> <content>) Writes to a new text file with the specified name and content"
    ) { input ->
        val index = (input.count() - input.indexOf(this.name)) - 1
        var content = ""
        var i = 0
        if (input.count() - index > 3) {
            for (string in input) {
                if (i > index + 1) {
                    content += "$string "
                }
                i++
            }
            val file = File("${input[index + 1]}.txt").writeText(content.trim())
            return@Command "File Written to $file"
        }
        else if (input.count() - index == 3) {
            content = input[index + 2]
            val file = File("${input[index + 1]}.txt").writeText(content.trim())
            return@Command "File Written to $file"
        }
        else if (input.count() - index < 3) {
            return@Command "ERROR: Please specify a file name and the file content"
        }
        return@Command "Something bad happened"
    }

    private val rf = Command(
        ".rf", "" +
                "(rf <file path>) Reads a text file with from specified path and prints the content"
    ) { input ->
        val index = (input.count() - input.indexOf(this.name)) - 1
        try {
            if (input.count() - index >= 2) {
                return@Command File(input[index + 1]).readText(Charsets.UTF_8)
            }
            else {
                return@Command "ERROR: Please specify a file name"
            }
        }
        catch (e: FileNotFoundException) {
            return@Command "ERROR: Could not find the file \"${input[index + 1]}\""
        }
    }

    val commands = mapOf(
        wf.name to wf,
        rf.name to rf
    )
}

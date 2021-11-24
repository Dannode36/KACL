package command.lib

import command.Command
import java.io.File
import java.io.FileNotFoundException

object FileSys {
    const val name = "File System"

    private val wf = Command(
        ".wf", "" +
                "(wf <file name> <content>) Writes to a new text file with the specified name and content"
    ) { args ->
        var content = ""
        var i = 0
        if (args.count() > 2) {
            for (string in args) {
                if (i > 0) {
                    content += "$string "
                }
                i++
            }
            val file = File("${args[0]}.txt").writeText(content.trim())
            return@Command "File Written to $file"
        }
        else if (args.count() == 2) {
            content = args[1]
            val file = File("${args[0]}.txt").writeText(content.trim())
            return@Command "File Written to ${file}"
        }
        else if (args.count() < 2) {
            return@Command "ERROR: Please specify a file name and the file content"
        }
        return@Command "Something bad happened"
    }

    private val rf = Command(
        ".rf", "" +
                "(rf <file path>) Reads a text file with from specified path and prints the content"
    ) { args ->
        try {
            if (args.isNotEmpty()) {
                return@Command File(args[0]).readText(Charsets.UTF_8)
            }
            else {
                return@Command "ERROR: Please specify a file name"
            }
        }
        catch (e: FileNotFoundException) {
            return@Command "ERROR: Could not find the file \"${args[0]}\""
        }
    }

    val commands = mapOf(
        wf.name to wf,
        rf.name to rf
    )
}

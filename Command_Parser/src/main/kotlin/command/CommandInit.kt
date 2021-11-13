package command

import Command
import command.lib.Converters
import command.lib.FileSys
import command.lib.Misc
import command.lib.Tools

object CommandInit{
    var categories = emptyMap<String, Map<String, Command>>()

    fun comInit(): Map<String, Map<String, Command>>{
        categories = mapOf(
            Converters.name to Converters.commands,
            FileSys.name to FileSys.commands,
            Misc.name to Misc.commands,
            Tools.name to Tools.commands
        )
        return categories
    }
}

package command.lib

import command.Command

object Misc {
    const val name = "Misc"
    private const val length = 2
    private val hello = Command(".hello", "(hello) hi") { return@Command "hi" }

    private val repeat = Command(".repeat", "(repeat <n times> <input>) repeats input") { input ->
        val isDigit = run{
            try {
                input[0].trim().toInt()
                return@run true
            }
            catch (e: NumberFormatException){
                return@run false
            }
        }
        if (input.count() == length && isDigit) {
            try {
                var i = 0
                var print = ""
                while (i < input[0].trim().toInt()) {
                    print += input[1] + " "
                    i++
                }
                return@Command print.trim()
            }
            catch (e: NumberFormatException) {
                return@Command "ERROR: Expected \"Int\" found \"${input[0]::class.simpleName}\""
            }
        }
        else if (input.count() > length && isDigit){
            try {
                var i = 0
                var k = 0
                var print = ""
                while (i < input[0].trim().toInt()) {
                    while (k < input.count()){
                        if(k > 0){
                            print += input[k] + " "
                        }
                        k++
                    }
                    k = 0
                    i++
                }
                return@Command print.trim()
            }
            catch (e: NumberFormatException) {
                return@Command "ERROR: Expected \"Int\" found \"${input[0]::class.simpleName}\""
            }
        }
        else if (input.count() > 1 && !isDigit){
            var k = 0
            var print = ""
                while (k < input.count()){
                    print += input[k] + " "
                    k++
                }
            return@Command print.trim()
        }
        else if (input.count() == 1){
            return@Command input[0]
        }
        else{
            return@Command "ERROR: Please enter something to repeat"
        }
    }

    private val weeb = Command("uwu", "hehe") {
        return@Command "" +
            "  ░░      ░░        ░░░░    ▓▓██████████████████████████                              \n" +
                    "░░░░██▓▓▓▓              ████░░░░░░░░░░░░░░░░░░░░░░░░░░░░████                          \n" +
                    "░░▓▓░░░░▒▒████▓▓    ████░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░██▓▓                      \n" +
                    "  ██▓▓░░░░░░░░▒▒████░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▓▓      ░░████████████\n" +
                    "░░▓▓░░▓▓░░░░░░▒▒██░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▓▓░░██▓▓░░░░░░░░░░██\n" +
                    "░░▓▓░░▓▓░░░░▒▒▓▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▓▓░░░░░░░░░░░░████\n" +
                    "░░▓▓░░░░▓▓▒▒▓▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▓▓░░░░▒▒░░██░░██\n" +
                    "░░▓▓░░░░░░▓▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▓▓░░░░░░▓▓░░░░██\n" +
                    "░░▓▓░░░░░░▓▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▓▓░░░░░░░░░░░░▓▓░░░░██░░░░██\n" +
                    "░░▓▓░░░░▓▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▓▓░░░░░░░░░░▓▓▒▒██░░░░░░██\n" +
                    "  ░░▓▓░░▓▓░░░░░░░░░░░░██░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░██░░░░░░░░░░▒▒▓▓██░░░░██  \n" +
                    "  ░░▓▓░░▓▓░░░░░░░░░░░░▓▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▓▓░░░░░░░░░░██░░░░░░██  \n" +
                    "    ░░▓▓░░░░░░░░░░░░████░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░██░░░░░░░░░░▓▓░░░░██    \n" +
                    "    ░░▓▓░░░░░░░░░░░░████░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▓▓░░░░░░░░░░▓▓░░░░██    \n" +
                    "  ░░░░▓▓░░  ░░░░░░██  ██░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▓▓░░░░░░░░▒▒▓▓██      \n" +
                    "    ░░▓▓░░░░░░░░░░██  ██░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▓▓░░░░░░░░▒▒██  ░░    \n" +
                    "  ░░▓▓░░░░░░░░░░░░██  ▓▓░░░░  ░░░░  ░░░░░░░░▓▓░░░░░░░░░░░░░░░░░░▓▓░░░░░░░░▒▒▓▓  ░░    \n" +
                    "  ░░▓▓░░░░░░░░▒▒▓▓    ██░░░░░░░░░░░░░░░░░░░░██░░░░░░░░░░░░░░░░░░▓▓░░░░░░░░▒▒██        \n" +
                    "  ░░▓▓░░░░░░░░▒▒██░░  ██░░░░░░░░░░░░░░░░░░░░░░██░░░░░░░░░░░░░░░░░░▓▓░░░░░░▒▒██        \n" +
                    "  ░░▓▓░░░░░░░░▒▒██      ██░░░░░░░░░░░░░░░░░░░░██░░░░░░░░░░░░░░░░▒▒▓▓░░░░░░▒▒██        \n" +
                    "  ░░▓▓░░░░░░▒▒▒▒░░  ░░  ██░░░░░░░░░░░░░░░░░░░░██░░░░░░░░░░░░░░░░▒▒▓▓░░░░░░▒▒██        \n" +
                    "  ░░▓▓░░░░░░▒▒▓▓░░      ██░░░░░░░░░░░░░░░░░░▒▒░░██░░░░░░░░░░░░░░▒▒▓▓░░░░░░▒▒██        \n" +
                    "  ░░██░░░░░░▒▒▓▓        ░░▓▓░░▒▒░░░░░░░░░░░░░░░░██░░░░░░░░░░░░░░▒▒▓▓░░░░░░▒▒██        \n" +
                    "  ░░██░░░░░░▒▒▓▓░░        ██▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓██▓▓▓▓▓▓▓▓▓▓░░░░▒▒██░░░░░░▒▒██        \n" +
                    "  ░░▓▓░░░░▒▒▓▓▓▓░░▓▓▓▓▓▓▓▓▓▓                ░░    ▓▓▓▓▓▓▓▓▓▓▓▓▓▓░░▓▓░░░░░░▒▒██        \n" +
                    "  ░░▓▓▒▒▒▒▒▒▓▓░░▓▓▓▓▓▓▓▓▓▓▓▓                      ▓▓▓▓▓▓▓▓▓▓▓▓  ▓▓▓▓░░▒▒▒▒▒▒██        \n" +
                    "  ░░▓▓▒▒▒▒▒▒██▓▓▓▓▓▓▓▓▓▓▓▓▓▓                      ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓░░▒▒▒▒▒▒██        \n" +
                    "  ░░▓▓▒▒▒▒▒▒▓▓▒▒  ▓▓▓▓▓▓▓▓▓▓                      ▓▓▓▓▓▓▓▓▓▓  ▓▓▓▓░░▒▒▒▒▒▒▒▒██        \n" +
                    "  ░░▓▓▒▒░░▒▒▓▓    ▓▓▓▓▓▓▓▓▓▓                      ▓▓▓▓▓▓▓▓▓▓    ▓▓░░▒▒░░░░▒▒██        \n" +
                    "  ░░▓▓▒▒▒▒▒▒▓▓    ▓▓░░░░░░▓▓                      ▓▓░░░░░░▓▓    ▓▓▒▒▒▒▒▒▒▒▒▒██        \n" +
                    "  ░░▓▓▒▒▒▒▒▒▓▓░░░░░░▓▓▓▓▓▓                          ▓▓▓▓▓▓░░░░░░▓▓▒▒▒▒▒▒▒▒▒▒██        \n" +
                    "    ▓▓▒▒▒▒▒▒▓▓░░░░░░░░░░░░                          ░░░░░░░░░░░░▓▓▒▒▒▒▒▒▒▒▒▒██        \n" +
                    "  ░░▓▓▒▒▒▒▒▒▒▒▒▒  ░░                              ░░      ░░░░▓▓▒▒▒▒▒▒▒▒▒▒▓▓██        \n" +
                    "    ▓▓▒▒▒▒▒▒▒▒▓▓                  ▓▓▒▒    ░░              ░░  ▓▓▒▒▒▒▒▒▒▒▒▒▒▒██        \n" +
                    "  ░░▓▓▒▒▒▒▒▒▒▒▒▒▓▓            ▓▓▓▓░░░░▓▓░░                  ▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒██        \n" +
                    "  ░░▓▓▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓      ▓▓░░░░▓▓  ░░▒▒              ▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒██        \n" +
                    "  ░░▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓░░░░░░░░▓▓░░▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒██        \n" +
                    "    ░░▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒██░░░░░░░░░░▓▓    ██▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒██        \n" +
                    "    ░░▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓░░░░░░░░░░░░▓▓    ██▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒██        \n" +
                    "    ░░▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒██░░░░░░░░░░░░░░▓▓░░▓▓      ████▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒██        \n" +
                    "    ░░██▒▒▒▒▒▒▒▒▒▒▒▒▒▒██░░░░░░░░░░░░▓▓  ▓▓          ██▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▒▒██        \n" +
                    "    ░░▓▓▒▒▒▒▒▒▒▒▒▒▒▒██░░░░░░░░░░░░░░██▓▓░░██      ██░░██▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▒▒██        \n" +
                    "    ░░▓▓▒▒▒▒▓▓▒▒▒▒▒▒██░░░░░░░░░░░░▓▓▓▓      ██  ██░░░░██▒▒▒▒▒▒▒▒▒▒▒▒▒▒██▓▓▓▓██        \n" +
                    "    ░░▓▓▒▒▒▒▓▓▒▒▒▒▒▒██░░░░░░░░░░░░▓▓▓▓      ████░░░░░░░░██▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▒▒██        \n" +
                    "      ░░▓▓▒▒██▓▓▒▒▒▒██░░░░░░░░░░▓▓░░░░▓▓░░██░░░░░░░░░░░░██▒▒▒▒▒▒▒▒▒▒▓▓░░▓▓▒▒██        \n" +
                    "        ▓▓▒▒▓▓▓▓▒▒▒▒██░░░░░░░░░░██░░░░▓▓░░██░░░░░░░░██░░██▒▒▒▒▒▒▒▒▒▒▓▓░░▓▓▓▓██        \n" +
                    "      ░░▓▓▒▒▓▓░░██▒▒▒▒██░░░░░░██░░░░░░░░▓▓░░░░░░░░░░██░░░░██▒▒▒▒▒▒▓▓░░▓▓▒▒▓▓          \n" +
                    "      ░░▓▓▒▒▓▓░░▓▓▒▒▒▒▒▒██████░░░░░░░░░░▓▓░░░░░░░░░░██░░░░▓▓▒▒▒▒▒▒▓▓░░██▒▒▒▒          \n" +
                    "        ░░▓▓▓▓  ░░██▒▒▒▒▒▒██░░░░░░░░░░░░▓▓░░░░░░░░░░██░░░░██▒▒▒▒▓▓  ░░▓▓▒▒▓▓          \n" +
                    "          ░░▓▓    ░░▓▓▒▒▒▒██░░░░░░░░░░░░▓▓░░▓▓░░░░░░░░▓▓░░░░▓▓▓▓░░  ░░▓▓▓▓░░          \n" +
                    "            ░░▒▒    ░░▓▓▓▓▓▓░░░░░░░░░░░░▓▓░░░░░░░░░░░░██░░░░▓▓░░    ░░▓▓░░            \n" +
                    "            ░░░░▓▓  ░░  ░░██░░░░░░░░░░░░▓▓░░░░░░░░░░░░██░░░░██    ░░▓▓░░              \n" +
                    "                        ██░░░░░░░░░░░░▒▒▓▓░░██░░░░░░░░██░░░░░░▓▓░░                    \n" +
                    "                        ██░░░░░░░░░░░░░░▓▓░░░░░░░░░░░░██░░░░░░██                      \n" +
                    "                        ██░░░░░░░░░░░░▒▒▒▒░░░░░░░░░░░░░░██░░░░░░▓▓                    \n" +
                    "                        ▓▓░░░░░░░░░░░░░░▓▓░░░░░░░░░░░░░░██░░░░░░▓▓                    \n" +
                    "                        ██░░░░░░░░░░░░░░▓▓░░░░░░░░░░░░░░▓▓██▓▓██▓▓                    \n" +
                    "                        ██████████████▓▓▓▓████████████████      ▓▓                    \n" +
                    "                      ▓▓▒▒▒▒▒▒▓▓▒▒▒▒▓▓▒▒▒▒▒▒▓▓▒▒▒▒▓▓▒▒▒▒▒▒▓▓██▓▓░░░░                  \n" +
                    "                      ▓▓▒▒▒▒▓▓▒▒▒▒▒▒▓▓▒▒▒▒▒▒██▒▒▒▒▒▒▓▓▒▒▒▒██░░░░                      \n" +
                    "                      ▓▓▒▒▒▒▓▓▒▒▒▒▒▒▓▓▒▒▒▒▒▒██▒▒▒▒▒▒██▒▒▒▒██                          \n" +
                    "                      ██▓▓▓▓██▓▓▓▓▓▓▓▓▓▓██▓▓██▓▓▓▓▓▓██▓▓▓▓██                          \n" +
                    "                      ░░░░░░▓▓░░░░░░▓▓░░░░░░██░░░░░░▓▓░░░░░░                          \n" +
                    "                            ▓▓      ▓▓      ██      ▓▓                                \n" +
                    "                            ██▓▓▓▓▓▓▓▓  ░░  ██▓▓▓▓▓▓██                                \n" +
                    "                            ▓▓▒▒▒▒▒▒▓▓      ██▒▒▒▒▒▒██                                \n" +
                    "                            ██▒▒▒▒▒▒▓▓░░    ██▒▒▒▒▒▒██                                \n" +
                    "                            ██▒▒▒▒▒▒▓▓      ██▒▒▒▒▒▒██                                \n" +
                    "                            ██▒▒▒▒▒▒▓▓      ██▒▒▒▒▒▒██                                \n" +
                    "                            ▓▓▒▒▒▒▒▒▓▓      ██▒▒▒▒▒▒██                                \n" +
                    "                            ▓▓▒▒▒▒▒▒▒▒▓▓░░██▒▒▒▒▒▒▒▒██                                \n" +
                    "                            ██▒▒▒▒▒▒▒▒▓▓  ▓▓▒▒▒▒▒▒▒▒██                                \n" +
                    "                            ░░██▓▓▓▓██▓▓░░▓▓▓▓▓▓▓▓██░░                                \n"
    }
    val commands = mapOf(
        hello.name to hello,
        repeat.name to repeat,
        weeb.name to weeb
    )
}

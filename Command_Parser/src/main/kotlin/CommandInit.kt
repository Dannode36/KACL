//package com.mkyong.crypto.bytes;

import command.tools.StringToBinary
import java.time.LocalDateTime

class CommandInit{
    companion object {
        fun Com_Init(): Map<String, Command>{
            var commands = emptyMap<String, Command>()

            var help = Command("help", "list all available commands") { input ->
                println("-----------------------------------------------------------------")
                for (command in commands){
                    println(command.value.name + ": " + command.value.desc)
                }
                println("-----------------------------------------------------------------")
            }

            var hello = Command("hello", "(hello) hi") { input -> println("hi") }

            var repeat = Command("repeat", "(repeat <input>) repeats input") { input ->
                if(input.count() == 3){
                    println("Found Increment")
                    try {
                        var i = 0
                        while(i < input[2].trim().toInt()){
                            println(input[1])
                            i++
                        }
                    }
                    catch(e: NumberFormatException){
                        println("Expected \"Int\" found \"${input[2]::class.simpleName}\"")
                    }
                }
                else {
                    println(input[1])
                }
            }

            var dt = Command("dt", "(dt) prints OS date and time [-t prints only time] [-d prints only date]") { input ->
                var timeNow = LocalDateTime.now();
                //var date = LocalDateTime.now();

                if (input.contains("-d")){
                    var time = timeNow.toString()
                    time = time.substring(0, time.indexOf("T"))
                    time = time.replace('.', ' ')
                    println("Current date: $time");
                }
                else if(input.contains("-t")){
                    var time = timeNow.toString()
                    time = time.substring(time.indexOf("T"))
                    time = time.substring(0, time.indexOf("."))
                    time = time.replace('T', ' ').trim()
                    println("Current time: $time");
                }
                else{
                    var time = timeNow.toString().replace('T', ' ')
                    time = time.substring(0, time.indexOf("."))
                    println("Current date and time: $time");
                }
            }

            var tbin = Command("tbin", "(tbin <input>) converts a string to a binary sequence") { input ->

                var converter = StringToBinary()
                converter.strToBinary(input[1])
               // println(Integer.toBinaryString(input.toInt()));
            }

            var tstr = Command("!tstr", "(!tstr <input>) converts a binary sequence to a string !NOT IMPLEMENTED!") { input ->
                var converter = StringToBinary()
                converter.strToBinary(input[1])
                // println(Integer.toBinaryString(input.toInt()));
            }

            commands = mapOf<String, Command>(
                hello.name to hello,
                help.name to help,
                repeat.name to repeat,
                dt.name to dt,
                tbin.name to tbin,
                tstr.name to tstr
            )
            return commands
        }
    }
}

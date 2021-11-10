//package com.mkyong.crypto.bytes;

import command.tools.StringToBinary
import java.time.LocalDateTime

class CommandInit{
    companion object {
        fun Com_Init(): Map<String, Command>{
            var commands = emptyMap<String, Command>()

            var help = Command("help", "list all available commands") { input ->
                for (command in commands){
                    println(command.value.name + ": " + command.value.desc)
                }
            }

            var hello = Command("hello", "(hello) hi") { input -> println("hi") }

            var repeat = Command("repeat", "(repeat <input>) repeats input") { input -> println(input[1]) }

            var time = Command("time", "(time) prints OS date and time") { input ->
                var timeNow = LocalDateTime.now();
                var time = timeNow.toString().replace('T', ' ')
                time = time.substring(0, time.indexOf(".") + 1);
                println("Current time: $time");
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
                time.name to time,
                tbin.name to tbin,
                tstr.name to tstr
            )
            return commands
        }
    }
}

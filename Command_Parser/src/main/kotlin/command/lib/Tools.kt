package command.lib

import command.Command
import command.CommandInit
import command.tools.DateTimeGetter
import io.ktor.client.engine.cio.*
import kotlinx.coroutines.*
import io.ktor.*
import io.ktor.client.*
import io.ktor.client.features.observer.*
import io.ktor.client.request.*
import io.ktor.http.*
import java.net.InetSocketAddress
import io.ktor.application.*
import io.ktor.client.response.*
import io.ktor.client.statement.*
import io.ktor.client.utils.EmptyContent.status
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.netty.handler.codec.http.HttpResponse
import java.net.BindException
import kotlin.coroutines.startCoroutine


object Tools {
    const val name = "Tools"
    var servers = mutableListOf<NettyApplicationEngine>()
    private val help = Command(".help", "list all available commands") {
        var output = ""
        output += "------------------------------------------------------------------------" + System.lineSeparator()
        for (category in CommandInit.categories) {
            output += category.key + ":" + System.lineSeparator()
            for (command in category.value){
                output += "    " + command.value.name + ": " + command.value.desc + System.lineSeparator()
            }
        }
        output += "------------------------------------------------------------------------"
        return@Command output
    }


    @DelicateCoroutinesApi
    private val netget = Command(".netget", "(.netget <host>) Sends a get request to the specified address"){ args ->
        if (args.isNotEmpty()){
            val client = HttpClient(CIO)
            var response = ""
            val coroutine = GlobalScope.launch {
                response = client.request("http://${args[0]}") {
                    method = HttpMethod.Get
                }
            }
            while (!coroutine.isCompleted){
                Thread.sleep(20)
            }
            return@Command response
        }
        return@Command "Please Specify an address"
    }


    private val netserver = Command(".netserver", "(.netserver <\"start\", \"stop\", \"stopall\",\"status\"> <port>) Manages a local Http server at 127.0.0.1:8080 or on the specified port"){ args ->
        if (args.isEmpty()){
            return@Command "ERROR: .netserver found no arguments"
        }
        // Starts on 8080
        if (args.count() == 1 && args[0].contains("start")){
            try {
                val server = embeddedServer(Netty, port = 8080) {
                    routing {
                        get("/") {
                            call.respondText("Hello, world!")
                        }
                    }
                }.start(wait = false)
                servers.add(server)
                return@Command "Server Successfully Started"
            }
            catch (e: BindException){
                return@Command "ERROR: The specified address is in use, or the address could not be assigned. ($e)"
            }
        }
        // Starts with specific port
        else if (args[0].contains("start")){
            try {
                val server = embeddedServer(Netty, port = args[1].toInt()) {
                    routing {
                        get("/") {
                            call.respondText("Hello, world!")
                        }
                    }
                }.start(wait = false)
                servers.add(server)
                return@Command "Server Successfully Started"
            }
            catch (e: BindException){
                return@Command "ERROR: The specified address is in use, or the address could not be assigned. ($e)"
            }
        }
        // Stops specific server
        else if (args[0].contains("stop") && args.count() == 2){
            servers[args[1].toInt()].stop(1000,10000)
            servers.removeAt(args[1].toInt())
            return@Command "Server Successfully Stopped"
        }
        // Stops all servers
        else if (args[0].contains("stopall")){
            val count = servers.count()
            var i = 0
            for (server in servers) {
                i++
                server.stop(300,5000)
                println("$i out of $count stopped...")
            }
            return@Command "All servers successfully stopped"
        }
        // Prints a list of all servers and their details and active status
        else if (args[0].contains("status")){
            var status = "|     Server Port     | Active | " + System.lineSeparator()

            for (server in servers){
                status += "| ${server.environment.connectors} |  ${server.application.isActive}  |" + System.lineSeparator()
            }
            return@Command status
        }
        return@Command "An interesting error occurred..."
    }


    private val dt = Command(".dt", "" +
            "(dt) prints OS date and time [-t prints only time] [-d prints only date]") { args ->
        val dateTimeGetter = DateTimeGetter()
        if (args.contains("-d")) {
            return@Command "Current date: ${dateTimeGetter.getDate()}"
        }
        else if (args.contains("-t")) {
            return@Command "Current time: ${dateTimeGetter.getTime()}"
        }
        else {
            return@Command "Current date and time: ${dateTimeGetter.getDateTime()}"
        }
    }

//    private val build = Command("build", "Creates a .bat file for running the skcp.jar") {
//        File("run.bat").writeText("java -jar
//        Command_Parser-${File("build.gradle.kts").readLines(Charsets.UTF_8)[8].split('"')[1]}.jar")
//    }

    val commands = mapOf(
        help.name to help,
        dt.name to dt,
        netget.name to netget,
        netserver.name to netserver
    )
}
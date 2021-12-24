package command.lib
import main
import command.Command
import command.CommandInit
import command.tools.DateTimeGetter
import httpCount
import io.ktor.client.engine.cio.*
import kotlinx.coroutines.*
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.application.*
import io.ktor.client.utils.EmptyContent.status
import io.ktor.features.*
import io.ktor.http.content.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import java.io.File
import java.net.BindException


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


    private val http = Command(".http", "(.http <\"start\", \"stop\", \"stopall\",\"status\",\"count\"> <port>) Manages a local HTTP servers"){ args ->
        if (args.isEmpty()){
            return@Command "ERROR: \".http\" found no arguments"
        }
        if (args[0].contains("help")){
            return@Command """
                |start: By default, creates a HTTP server bound to 127.0.0.1:8080 unless a port is specified and or a serving file (can not change ip for now)
                |stop: Stops the specified server (Use status to find the position of the server (zero indexed))
                |stopall: Stops all active servers 
                |status: Prints a list of all active servers and their details
                |count: Prints the number of active servers
                |route: Routes a server to serve a different file
            """.trimMargin()
        }
        // Starts on 8080
        else if (args.count() == 1 && args[0].contains("start")){
            try {
                val server = embeddedServer(Netty, port = 25565) {
                    install(CallLogging){
                        format { call ->
                            val status = call.response.status()
                            val httpMethod = call.request.httpMethod.value
                            val origin = call.request.origin.remoteHost
                            "Status: $status, HTTP method: $httpMethod, Origin: $origin"
                        }
                    }

                    routing {
                        get("/") {
                            val content = "index.zip"
                            val file = File(content)
                            //call.respondText(file.readText())
                            //call.application.environment.log.info("${call.request.origin.remoteHost} tried to get $content")
                            call.response.header("Content-Disposition", "attachment; filename=\"${file.name}\"")
                            call.respondFile(file)
                        }
                        static("static") {
                            file("image.jpg")
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
        // Starts server with specific port
        else if (args.count() == 2 && args[0].contains("start")){
            try {
                val server = embeddedServer(Netty, port = args[1].toInt()) {
                    routing {
                        get("/") {
                            call.respondText(File("index.txt").readText())
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
        // Starts server with specific port and serving file
        else if (args.count() > 2 && args[0].contains("start")){
            try {
                val server = embeddedServer(Netty, port = args[1].toInt()) {
                    routing {
                        get("/") {
                            call.respondText(File(args[2]).readText())
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
            servers[args[1].toInt()].stop(300,5000)
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
            servers.clear()
            return@Command "All servers successfully stopped"
        }
        // Prints a list of all servers and their details and active status
        else if (args[0].contains("status")){
            var status = ""
            if (servers.isEmpty()){
                status += "No running servers could be found"
            }
            else{
                status = "|     Server Port     | Active | " + System.lineSeparator()
                for (server in servers){
                    status += "| ${server.environment.connectors} |  ${server.application.isActive}  |" + System.lineSeparator()
                }
            }
            return@Command status
        }
        else if (args[0].contains("count")){
            var count = 0

            for (server in servers){
                count++
            }
            return@Command "There are $count HTTP servers running!"
        }
        //Change the file a server serves
//        else if (args[0].contains("route")){
//            println(args.count())
//            if(args.count() == 3){
//                servers[0].application.routing {
//                    get("/") {
//                        call.respondText(File(args[2]).readText())
//                    }
//                }
//            }
//            return@Command "Route Successful"
//        }
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
        http.name to http
    )
}
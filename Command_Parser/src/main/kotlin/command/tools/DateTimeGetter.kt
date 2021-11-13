package command.tools

import java.time.LocalDateTime

object DateTimeGetter {
    fun GetDate(): String {
        val timeNow = LocalDateTime.now()
        var time = timeNow.toString()
        time = time.substring(0, time.indexOf("T"))
        return time.replace('.', ' ')
    }

    fun GetTime(): String {
        val timeNow = LocalDateTime.now()
        var time = timeNow.toString()
        time = time.substring(time.indexOf("T"))
        time = time.substring(0, time.indexOf("."))
        return time.replace('T', ' ').trim()
    }

    fun GetDateTime(): String {
        val timeNow = LocalDateTime.now()
        var time = timeNow.toString().replace('T', ' ')
        return time.substring(0, time.indexOf("."))
    }
}

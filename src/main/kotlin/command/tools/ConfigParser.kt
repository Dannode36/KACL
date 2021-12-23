package command.tools

import java.io.*
import java.util.*


object ConfigParser {
    private var prefs = Properties()

    public fun setPrefs(){
        prefs.setProperty("bHOF", "0")
        prefs.setProperty("TextColour", "0")

        val out = FileOutputStream(
            "schoolGrades.properties"
        )
        prefs.store(out, null)
        out.close()

        val `in` = FileInputStream("schoolGrades.properties")
        prefs.load(`in`)
        `in`.close()

        for (key in prefs.stringPropertyNames()) {
            val value: String = prefs.getProperty(key)
            println("The grade in $key is: $value")
        }

        val str: String = prefs.getProperty("History", "No grade")
        println("The grade in History is $str")
    }
}

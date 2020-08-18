package solid_design_principles.single_responsibility_principle

import java.io.File
import java.io.PrintStream

class Persistence {
    fun saveToFile(journal: Journal, filename: String, overwrite: Boolean) {
        if (overwrite || File(filename).exists()) {
            val printStream = PrintStream(filename)
            printStream.use { it.println(journal.toString()) }
        }
    }

    // fun load(filename: String): Journal { }
    // fun load(url: URL): Journal { }
}

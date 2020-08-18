package creational_patterns.singleton

import java.io.File
import java.io.IOException
import java.lang.Exception

class StaticBlockSingleton @Throws(IOException::class) private constructor() {
    init {
        println("Singleton is initialising")
        File.createTempFile(".", ".")
    }

    companion object {
        lateinit var instance: StaticBlockSingleton
        private set

        init {
            try {
                instance = StaticBlockSingleton()
            } catch (error: Exception) {
                error("failed to create singleton")
            }
        }
    }
}

fun main() {
    val singleton = StaticBlockSingleton.instance
}

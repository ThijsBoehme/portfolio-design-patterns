package creational_patterns.singleton

import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

enum class EnumBasedSingleton {
    INSTANCE;

    var value: Int

    init {
        value = 42
    }
}

private fun saveToFile(singleton: EnumBasedSingleton, filename: String) {
    val fileOutputStream = FileOutputStream(filename)
    fileOutputStream.use {
        val objectOutputStream = ObjectOutputStream(fileOutputStream)
        objectOutputStream.use {
            it.writeObject(singleton)
        }
    }
}

private fun readFromFile(filename: String): EnumBasedSingleton {
    val fileInputStream = FileInputStream(filename)
    fileInputStream.use {
        val objectInputStream = ObjectInputStream(fileInputStream)
        objectInputStream.use {
            return it.readObject() as EnumBasedSingleton
        }
    }
}

fun main() {
    val filename = "myfile.bin"

    // First run: save and read, second run: only read
//    val singleton = EnumBasedSingleton.INSTANCE
//    singleton.value = 111
//    saveToFile(singleton, filename)

    val singleton2 = readFromFile(filename)
    println(singleton2.value)

    // The value is not saved, only the name "INSTANCE"
}

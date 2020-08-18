package creational_patterns.singleton

import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.io.Serializable

// 1. Reflection
// 2. Serialisation

class SerialisableSingleton private constructor(var value: Int = 0): Serializable {
    companion object {
        val instance = SerialisableSingleton()
    }

    protected fun readResolve(): Any {
        return instance
    }
}

private fun saveToFile(singleton: SerialisableSingleton, filename: String) {
    val fileOutputStream = FileOutputStream(filename)
    fileOutputStream.use {
        val objectOutputStream = ObjectOutputStream(fileOutputStream)
        objectOutputStream.use {
            it.writeObject(singleton)
        }
    }
}

private fun readFromFile(filename: String): SerialisableSingleton {
    val fileInputStream = FileInputStream(filename)
    fileInputStream.use {
        val objectInputStream = ObjectInputStream(fileInputStream)
        objectInputStream.use {
            return it.readObject() as SerialisableSingleton
        }
    }
}

fun main() {
    val singleton = SerialisableSingleton.instance
    singleton.value = 111

    val filename = "singleton.bin"
    saveToFile(singleton, filename)

    singleton.value = 222

    val singleton2 = readFromFile(filename)

    println(singleton === singleton2)
    println(singleton.value)
    println(singleton2.value)
}

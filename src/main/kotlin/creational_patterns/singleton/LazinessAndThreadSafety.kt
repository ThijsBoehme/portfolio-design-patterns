package creational_patterns.singleton

class LazySingleton {
    companion object {
        val instance: LazySingleton by lazy {
            println("Inside the lazy")
            LazySingleton()
        }

        init {
            println("Initialising a lazy singleton")
        }
    }
}

fun main() {
    println("Getting the instance")
    val singleton = LazySingleton.instance
    println("Got the instance")

    val singleton2 = LazySingleton.instance
    println("Got instance 2")
}

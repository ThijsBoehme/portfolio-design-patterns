package creational_patterns.singleton

class BasicSingleton private constructor(var value: Int = 0) {
    companion object {
        val instance = BasicSingleton()
    }
}

fun main() {
    val singleton = BasicSingleton.instance
    singleton.value = 123

    println(singleton.value)
}

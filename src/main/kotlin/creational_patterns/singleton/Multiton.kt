package creational_patterns.singleton

enum class Subsystem {
    PRIMARY,
    AUXILIARY,
    FALLBACK
}

class Printer private constructor() {
    init {
        instanceCount++
        println("A total of $instanceCount instances have been created so far")
    }
    companion object {
        private val instances = HashMap<Subsystem, Printer>()
        private var instanceCount = 0

        fun get(subsystem: Subsystem): Printer {
            if (instances.containsKey(subsystem)) {
                return instances[subsystem]!!
            }

            val instance = Printer()
            instances[subsystem] = instance
            return instance
        }
    }
}

fun main() {
    val main = Printer.get(Subsystem.PRIMARY)
    val auxiliary = Printer.get(Subsystem.AUXILIARY)
    val auxiliary2 = Printer.get(Subsystem.AUXILIARY)
}

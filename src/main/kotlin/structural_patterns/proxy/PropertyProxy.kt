package structural_patterns.proxy

// Not very relevant in Kotlin, you can use the field getter and setters
class Creature {
    var agility: Int = 0
        set(value) {
            println("Setting the agility to $value")
            field = value
        }

    init {
        agility = 123
    }
}

fun main() {
    val creature = Creature()
    creature.agility = 12
}

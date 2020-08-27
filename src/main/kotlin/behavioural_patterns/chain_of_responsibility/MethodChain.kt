package behavioural_patterns.chain_of_responsibility

class Creature(
    val name: String,
    var attack: Int,
    var defense: Int
) {
    override fun toString(): String {
        return "Creature(name='$name', attack=$attack, defense=$defense)"
    }
}

open class CreatureModifier(
    protected val creature: Creature
) {
    protected var next: CreatureModifier? = null

    fun add(creatureModifier: CreatureModifier) {
        if (next != null) {
            next!!.add(creatureModifier)
        } else next = creatureModifier
    }

    open fun handle() {
        next?.handle()
    }
}

class DoubleAttackModifier(creature: Creature): CreatureModifier(creature) {
    override fun handle() {
        println("Doubling ${creature.name}'s attack")
        creature.attack *= 2
        super.handle()
    }
}

class IncreaseDefenseModifier(creature: Creature): CreatureModifier(creature) {
    override fun handle() {
        println("Increasing ${creature.name}'s defense")
        creature.defense += 3
        super.handle()
    }
}

class NoBonusesModifier(creature: Creature): CreatureModifier(creature) {
    override fun handle() {
        println("No bonuses for you!")
        // No 'super.handle()' to stop modifier chain here
    }
}

fun main() {
    val goblin = Creature("Goblin", 2, 2)
    println(goblin)

    val rootModifier = CreatureModifier(goblin)

    rootModifier.add(NoBonusesModifier(goblin))

    println("Let's double the goblin's attack...")
    rootModifier.add(DoubleAttackModifier(goblin))
    println("Let's increase the goblin's defense...")
    rootModifier.add(IncreaseDefenseModifier(goblin))

    rootModifier.handle()
    println(goblin)
}

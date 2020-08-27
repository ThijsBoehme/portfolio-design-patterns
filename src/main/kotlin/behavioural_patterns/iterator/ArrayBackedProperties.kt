package behavioural_patterns.iterator

import java.util.*
import java.util.function.Consumer
import java.util.stream.IntStream
import kotlin.math.max

class SimpleCreature(
    private val strength: Int,
    private val agility: Int,
    private val intelligence: Int
) {

    fun max(): Int = max(strength, max(agility, intelligence))
    fun sum(): Int = strength + agility + intelligence
    fun average(): Double = sum() / 3.0
}

class Creature: Iterable<Int> {
    private val stats = IntArray(3)
    private val strengthIndex = 0
    private val agilityIndex = 1
    private val intelligenceIndex = 2

    fun getStrength(): Int = stats[strengthIndex]
    fun setStrength(strength: Int) {
        stats[strengthIndex] = strength
    }

    fun getAgility(): Int = stats[agilityIndex]
    fun setAgility(agility: Int) {
        stats[agilityIndex] = agility
    }

    fun getIntelligence(): Int = stats[intelligenceIndex]
    fun setIntelligence(intelligence: Int) {
        stats[intelligenceIndex] = intelligence
    }

    fun max(): Int = Arrays.stream(stats).max().asInt
    fun sum(): Int = Arrays.stream(stats).sum()
    fun average(): Double = Arrays.stream(stats).average().asDouble

    override fun iterator(): Iterator<Int> {
        return Arrays.stream(stats).iterator()
    }

    override fun forEach(action: Consumer<in Int>?) {
        for (stat in stats) {
            action?.accept(stat)
        }
    }

    override fun spliterator(): Spliterator<Int> {
        return Arrays.stream(stats).spliterator()
    }
}

fun main() {
    val creature = Creature()
    creature.setAgility(12)
    creature.setIntelligence(13)
    creature.setStrength(17)

    println("Creature has a max statistic of ${creature.max()}," +
            "total stats = ${creature.sum()}," +
            "average stat = ${creature.average()}")
}

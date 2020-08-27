package behavioural_patterns.chain_of_responsibility

import java.io.Closeable
import java.util.function.Consumer

// Chain of Responsibility + Observer + Mediator (+ Memento)

class Event<Args> {
    private var index = 0
    private val handlers = HashMap<Int, Consumer<Args>>()

    fun subscribe(handler: Consumer<Args>): Int {
        val i = index
        handlers[index++] = handler
        return i
    }

    fun unsubscribe(key: Int) {
        handlers.remove(key)
    }

    fun fire(args: Args) {
        for (handler in handlers.values) {
            handler.accept(args)
        }
    }
}

class Query(
    val creatureName: String,
    val argument: Argument,
    var result: Int
) {
    enum class Argument {
        ATTACK, DEFENSE
    }
}

class Game {
    val queries = Event<Query>()
}

class Creature2(
    private val game: Game,
    val name: String,
    baseAttack: Int,
    baseDefense: Int
) {

    val baseAttack: Int = baseAttack
        get() {
            val query = Query(name, Query.Argument.ATTACK, field)
            game.queries.fire(query)
            return query.result
        }
    val baseDefense: Int = baseDefense
        get() {
            val query = Query(name, Query.Argument.DEFENSE, field)
            game.queries.fire(query)
            return query.result
        }

    override fun toString(): String {
        return "Creature2(name='$name', attack='$baseAttack', defense='$baseDefense')"
    }
}

open class CreatureModifier2(
    protected val game: Game,
    protected val creature: Creature2
)

// AutoCloseable is not yet part of standard Kotlin, but Closeable is
class DoubleAttackModifier2(game: Game, creature: Creature2): CreatureModifier2(game, creature), Closeable {
    private val token: Int

    init {
        token = game.queries.subscribe { query ->
            if (query.creatureName == creature.name && query.argument == Query.Argument.ATTACK) {
                query.result *= 2
            }
        }
    }

    override fun close() {
        game.queries.unsubscribe(token)
    }
}

class IncreaseDefenseModifier2(game: Game, creature: Creature2): CreatureModifier2(game, creature), Closeable {
    private val token: Int

    init {
        token = game.queries.subscribe { query ->
            if (query.creatureName == creature.name && query.argument == Query.Argument.DEFENSE) {
                query.result += 3
            }
        }
    }

    override fun close() {
        game.queries.unsubscribe(token)
    }
}

fun main() {
    val game = Game()
    val goblin = Creature2(game, "Strong Goblin", 2, 2)
    println(goblin)

    val increaseDefenseModifier = IncreaseDefenseModifier2(game, goblin)
    DoubleAttackModifier2(game, goblin).use { println(goblin) }
    println(goblin)
}

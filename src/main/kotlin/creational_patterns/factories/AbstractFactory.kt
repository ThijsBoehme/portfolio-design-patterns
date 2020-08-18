package creational_patterns.factories

import org.reflections.Reflections
import java.io.BufferedReader
import java.io.InputStreamReader

interface HotDrink {
    fun consume()
}

class Tea: HotDrink {
    override fun consume() {
        println("This tea is delicious")
    }
}

class Coffee: HotDrink {
    override fun consume() {
        println("This coffee is delicious")
    }
}

interface HotDrinkFactory {
    fun prepare(amount: Int): HotDrink
}

class TeaFactory: HotDrinkFactory {
    override fun prepare(amount: Int): HotDrink {
        println("Put in the tea bag, boil some water, pour $amount ml, add lemon and enjoy!")
        return Tea()
    }
}

class CoffeeFactory: HotDrinkFactory {
    override fun prepare(amount: Int): HotDrink {
        println("Grind some beans, boil some water, pour $amount ml, add cream and sugar and enjoy!")
        return Coffee()
    }
}

class HotDrinkMachine {
    private var namedFactories = ArrayList<Pair<String, HotDrinkFactory>>()

    init {
        val types = Reflections("")
            .getSubTypesOf(HotDrinkFactory::class.java)

        for (type in types) {
            namedFactories.add(
                Pair(
                    type.simpleName.replace("Factory", ""),
                    type.getDeclaredConstructor().newInstance()
                )
            )
        }
    }

    fun makeDrink(): HotDrink {
        println("Available drinks:")
        for ((index, factory) in namedFactories.withIndex()) {
            println("$index: ${factory.first}")
        }

        while (true) {
            val string = readLine()
            if (string != null) {
                val index = string.toInt()
                if (index >= 0 && index < namedFactories.size) {
                    println("Specify amount:")
                    val amountString = readLine()
                    if (amountString != null) {
                        val amount = amountString.toInt()
                        if (amount > 0) {
                            return namedFactories[index].second.prepare(amount)
                        }
                    }
                }
            }
            println("Incorrect input, try again")
        }
    }
}

fun main() {
    val machine = HotDrinkMachine()
    val drink = machine.makeDrink()
    drink.consume()
}

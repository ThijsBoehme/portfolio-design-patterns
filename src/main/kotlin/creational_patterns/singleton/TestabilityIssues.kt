package creational_patterns.singleton

import com.google.common.collect.Iterables
import org.junit.Assert.assertEquals
import org.junit.Test
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

class SingletonDatabase private constructor() {
    private val capitals = Hashtable<String, Int>()

    init {
        instanceCount++
        println("Initialising database")

        val url = javaClass.getResource("/capitals.txt")
        val path = Paths.get(url.toURI())
        val lines = Files.readAllLines(path)

        Iterables.partition(lines, 2)
            .forEach { kv -> capitals.put(kv.get(0).trim(), kv.get(1).toInt()) }
    }

    companion object {
        var instanceCount = 0
        private set

        val instance: SingletonDatabase by lazy { SingletonDatabase() }
    }

    fun getPopulation(capital: String): Int {
        return capitals[capital]!!
    }
}

class SingletonRecordFinder {
    fun getTotalPopulation(capitals: List<String>): Int {
        var result = 0
        for (capital in capitals) {
            result += SingletonDatabase.instance.getPopulation(capital)
        }
        return result
    }
}

class Tests {
    @Test
    fun singletonTotalPopulationTest() {
        val recordFinder = SingletonRecordFinder()
        val cities = listOf("Seoul", "Mexico City")
        val totalPopulation = recordFinder.getTotalPopulation(cities)
        assertEquals(1750000+1740000, totalPopulation)
    }
}

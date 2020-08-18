package creational_patterns.singleton

import org.junit.Assert
import org.junit.Test
import java.util.*

interface Database {
    fun getPopulation(capital: String): Int
}

// Singleton Database class also implements Database interface

class ConfigurableRecordFinder(private var database: Database) {
    fun getTotalPopulation(capitals: List<String>): Int {
        var result = 0
        for (capital in capitals) {
            result += database.getPopulation(capital)
        }
        return result
    }
}

class DummyDatabase: Database {
    private val dummyData = Hashtable<String, Int>()

    init {
        dummyData["alpha"] = 1
        dummyData["beta"] = 2
        dummyData["gamma"] = 3
    }

    override fun getPopulation(capital: String): Int {
        return dummyData[capital]!!
    }
}

class Tests2 {
    @Test
    fun dependentPopulationTest() {
        val dummyDatabase = DummyDatabase()
        val recordFinder = ConfigurableRecordFinder(dummyDatabase)
        Assert.assertEquals(4, recordFinder.getTotalPopulation(listOf("alpha", "gamma")))
    }
}

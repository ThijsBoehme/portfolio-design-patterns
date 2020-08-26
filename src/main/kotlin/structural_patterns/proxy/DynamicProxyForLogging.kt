package structural_patterns.proxy

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

interface Human {
    fun walk()
    fun talk()
}

class Person: Human {
    override fun walk() {
        println("I am walking")
    }

    override fun talk() {
        println("I am talking")
    }
}

class LoggingHandler(private val target: Any?): InvocationHandler {
    private val calls: HashMap<String, Int> = HashMap()

    override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any {
        val name = method!!.name

        if (name.contains("toString"))
            return calls.toString()

        calls.merge(name, 1, Int::plus)
        return if (args == null) {
            method.invoke(target) ?: Any()
        } else {
            method.invoke(target, args) ?: Any()
        }
    }
}

fun main() {
    val person = Person()
    val logged = withLogging(person, Human::class.java)
    logged.talk()
    logged.walk()
    logged.walk()
    println(logged)
}

@Suppress("UNCHECKED_CAST")
fun <T> withLogging(target: T, itf: Class<T>): T {
    return Proxy.newProxyInstance(
        itf.classLoader,
        arrayOf(itf),
        LoggingHandler(target)
    ) as T
}

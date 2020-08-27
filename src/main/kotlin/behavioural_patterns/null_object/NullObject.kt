package behavioural_patterns.null_object

import java.lang.reflect.Proxy

interface Logger {
    fun info(message: String)
    fun warn(message: String)
}

class ConsoleLogger: Logger {
    override fun info(message: String) {
        println(message)
    }

    override fun warn(message: String) {
        println("WARNING: $message")
    }
}

// Final by default in Kotlin
class NullLogger: Logger {
    override fun info(message: String) {}

    override fun warn(message: String) {}
}

class BankAccount(
    private val logger: Logger
) {
    private var balance: Int = 0

    fun deposit(amount: Int) {
        balance += amount
        logger.info("Deposited $amount")
    }
}

fun main() {
    // val logger = ConsoleLog()
    // val account = BankAccount(logger)

    // val account = BankAccount(NullLog())

    val logger = noOp(Logger::class.java)
    val account = BankAccount(logger)
    account.deposit(50)
}

@Suppress("UNCHECKED_CAST")
fun <T> noOp(itf: Class<T>): T {
    return Proxy.newProxyInstance(
        itf.classLoader,
        arrayOf(itf)
    ) { _, method, _ ->
        return@newProxyInstance if (method.returnType == Void.TYPE) null
        else method.returnType.getConstructor().newInstance()
    } as T
}

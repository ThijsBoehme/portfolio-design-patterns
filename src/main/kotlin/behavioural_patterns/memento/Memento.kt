package behavioural_patterns.memento

// Or "BankAccountToken"
class Memento(val balance: Int)

class BankAccount(private var balance: Int) {
    fun deposit(amount: Int): Memento {
        balance += amount
        return Memento(balance)
    }

    fun restore(memento: Memento) {
        balance = memento.balance
    }

    override fun toString(): String {
        return "BankAccount(balance=$balance)"
    }
}

fun main() {
    val account = BankAccount(100)
    val memento1 = account.deposit(50)
    val memento2 = account.deposit(25)
    println(account)

    // Restore to memento1
    account.restore(memento1)
    println(account) // Should be 150

    // Restore to memento2
    account.restore(memento2)
    println(account) // Should be 175
}

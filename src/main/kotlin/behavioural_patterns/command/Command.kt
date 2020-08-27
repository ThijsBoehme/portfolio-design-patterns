package behavioural_patterns.command

class BankAccount {
    private var balance = 0
    private val overdraftLimit = -500

    fun deposit(amount: Int): Boolean {
        balance += amount
        println("Deposited $amount, balance is now $balance")
        return true
    }

    fun withdraw(amount: Int): Boolean {
        if (balance - amount >= overdraftLimit) {
            balance -= amount
            println("Withdrew $amount, balance is now $balance")
            return true
        }

        println("Insufficient funds")
        return false
    }

    override fun toString(): String {
        return "BankAccount(balance=$balance)"
    }
}

interface Command {
    fun call()
    fun undo()
}

class BankAccountCommand(
    private val account: BankAccount,
    private val action: Action,
    private val amount: Int
): Command {
    private var succeeded = false

    override fun call() {
        succeeded = when (action) {
            Action.DEPOSIT -> account.deposit(amount)
            Action.WITHDRAW -> account.withdraw(amount)
        }
    }

    override fun undo() {
        if (!succeeded) { return }

        when (action) {
            Action.DEPOSIT -> account.withdraw(amount)
            Action.WITHDRAW -> account.deposit(amount)
        }
    }

    enum class Action {
        DEPOSIT, WITHDRAW
    }
}

fun main() {
    val account = BankAccount()
    println(account)

    val commands = listOf(
        BankAccountCommand(account, BankAccountCommand.Action.DEPOSIT, 100),
        BankAccountCommand(account, BankAccountCommand.Action.WITHDRAW, 1000)
    )

    for (command in commands) {
        command.call()
        println(account)
    }

    for (command in commands.reversed()) {
        command.undo()
        println(account)
    }
}

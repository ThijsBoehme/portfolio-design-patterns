package behavioural_patterns.template_method

abstract class Game(protected val numberOfPlayers: Int) {
    protected var currentPlayer = 0;

    fun run() {
        start()
        while (!hasWinner())
            takeTurn()
        println("Player ${getWinningPlayer()} wins!")
    }

    protected abstract fun getWinningPlayer(): Int
    protected abstract fun takeTurn()
    protected abstract fun hasWinner(): Boolean
    protected abstract fun start()
}

class Chess: Game(2) {
    private val maxTurns = 10
    private var turn = 1

    override fun getWinningPlayer(): Int {
        return 0
    }

    override fun takeTurn() {
        println("Turn ${turn++} taken by player $currentPlayer")
        currentPlayer = (currentPlayer + 1) % numberOfPlayers
    }

    override fun hasWinner(): Boolean {
        return turn == maxTurns
    }

    override fun start() {
        println("Starting a game of chess.")
    }
}

fun main() {
    Chess().run()
}

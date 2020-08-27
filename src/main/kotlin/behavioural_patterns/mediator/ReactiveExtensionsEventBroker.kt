package behavioural_patterns.mediator

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer

// Event Broker with reactive extensions

class EventBroker: Observable<Int>() {
    private val observers = ArrayList<Observer<in Int>>()

    fun publish(n: Int) {
        for (observer in observers) {
            observer.onNext(n)
        }
    }

    override fun subscribeActual(observer: Observer<in Int>?) {
        if (observer != null) {
            observers.add(observer)
        }
    }
}

class FootballPlayer(
    private val broker: EventBroker,
    val name: String
) {
    private var goalsScored = 0

    fun score() {
        broker.publish(++goalsScored)
    }
}

class FootballCoach(broker: EventBroker) {
    init {
        broker.subscribe { println("Hey, you scored $it goals!") }
    }
}

fun main() {
    val broker = EventBroker()
    val player = FootballPlayer(broker, "John")
    val coach = FootballCoach(broker)

    player.score()
    player.score()
    player.score()
}

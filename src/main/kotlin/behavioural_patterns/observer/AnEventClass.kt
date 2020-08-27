package behavioural_patterns.observer

import java.util.function.Consumer

class Event<TArgs> {
    private var count = 0
    val handlers = HashMap<Int, Consumer<TArgs>>()

    fun addHandler(handler: Consumer<TArgs>): Subscription<TArgs> {
        val i = count
        handlers[count++] = handler
        return Subscription(this, i)
    }

    fun fire(args: TArgs) {
        for (handler in handlers.values) {
            handler.accept(args)
        }
    }

    class Subscription<TArgs>(
        private val event: Event<TArgs>,
        private val id: Int
    ): AutoCloseable {

        override fun close() {
            event.handlers.remove(id)
        }
    }
}

class PropertyChangedEventArgs2(
    val source: Any,
    val propertyName: String
)

class Person2 {
    val propertyChanged = Event<PropertyChangedEventArgs2>()

    var age = 0
        set(value) {
            if (field == value) return

            val oldCanVote = canVote

            field = value
            propertyChanged.fire(PropertyChangedEventArgs2(this, "age"))

            if (oldCanVote != canVote) {
                propertyChanged.fire(PropertyChangedEventArgs2(this, "canVote"))
            }
        }

    // Dependent property, big issues here
    val canVote: Boolean
        get() = age >= 18
}

fun main() {
    val person = Person2()
    val subscription = person.propertyChanged
        .addHandler { x -> println("Person's ${x.propertyName} has changed") }

    person.age = 17
    person.age = 18
    subscription.close()
    person.age = 19
}

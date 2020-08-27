package behavioural_patterns.observer

class PropertyChangedEventArgs<T>(
    val source: T,
    val propertyName: String,
    val newValue: Any
)

interface Observer<T> {
    fun handle(args: PropertyChangedEventArgs<T>)
}

open class Observable<T> {
    private val observers = ArrayList<Observer<T>>()

    fun subscribe(observer: Observer<T>) {
        observers.add(observer)
    }

    protected fun propertyChanged(source: T, propertyName: String, newValue: Any) {
        for (observer in observers) {
            observer.handle(PropertyChangedEventArgs(source, propertyName, newValue))
        }
    }
}

class Person: Observable<Person>() {
    var age = 0
    set(value) {
        if (field == value) return
        field = value
        propertyChanged(this, "age", field)
    }
}

class Demo: Observer<Person> {
    init {
        val person = Person()
        person.subscribe(this)

        for (i in 20 until 24) {
            person.age = i
        }
    }

    override fun handle(args: PropertyChangedEventArgs<Person>) {
        println("Person's ${args.propertyName} has changed to ${args.newValue}")
    }
}

fun main() {
    Demo()
}

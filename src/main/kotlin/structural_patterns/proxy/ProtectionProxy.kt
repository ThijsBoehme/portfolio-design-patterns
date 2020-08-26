package structural_patterns.proxy

interface Drivable {
    fun drive()
}

open class Car(protected val driver: Driver): Drivable {
    override fun drive() {
        println("Car being driven")
    }
}

class Driver(val age: Int)

class CarProxy(driver: Driver): Car(driver) {
    override fun drive() {
        if (driver.age >= 16) {
            super.drive()
        } else {
            println("Driver too young!")
        }
    }
}

fun main() {
    var car = Car(Driver(12))
    car.drive() // Would still drive, doesn't check

    // Now use the new proxy
    car = CarProxy(Driver(12))
    car.drive() // Doesn't drive
}

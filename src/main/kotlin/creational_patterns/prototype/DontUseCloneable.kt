package creational_patterns.prototype

class Address(var streetName: String, var houseNumber: Int): Cloneable {
    override fun toString(): String {
        return "Address(streetName='$streetName', houseNumber=$houseNumber)"
    }

    // Deep copy
    public override fun clone(): Address {
        return Address(streetName, houseNumber)
    }
}

class Person(var names: Array<String>, var address: Address): Cloneable {
    override fun toString(): String {
        return "Person(names=${names.contentToString()}, address=$address)"
    }

    public override fun clone(): Person {
        return Person(names.clone(), address.clone())
    }
}

fun main() {
    val john = Person(arrayOf("John", "Smith"), Address("London Road", 123))
    val jane = john.clone()
    jane.names[0] = "Jane"
    jane.address.houseNumber = 124

    println(john)
    println(jane)
}

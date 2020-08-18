package creational_patterns.prototype

class Address2(var streetAddress: String, var city: String, var country: String) {
    constructor(address: Address2): this(address.streetAddress, address.city, address.country)

    override fun toString(): String {
        return "Address2(streetAddress='$streetAddress', city='$city', country='$country')"
    }
}

class Employee(var name: String, var address: Address2) {
    constructor(employee: Employee): this(employee.name, employee.address)

    override fun toString(): String {
        return "Employee(name='$name', address=$address)"
    }
}

fun main() {
    val john = Employee("John", Address2("123 London Road", "London", "UK"))

    val chris = Employee(john)
    chris.name = "Chris"

    println(john)
    println(chris)
}

package creational_patterns.builder

fun main() {
    val employeeBuilder = EmployeeBuilder()
    val dmitri = employeeBuilder.withName("Dmitri")
        .worksAt("Developer")
        .build()
    println(dmitri)
}

class Person {
    var name = ""
    var position = ""

    override fun toString(): String {
        return "Person(name='$name', position='$position')"
    }
}

open class PersonBuilder<SELF : PersonBuilder<SELF>> {
    protected var person = Person()

    fun withName(name: String): SELF {
        person.name = name
        return self()
    }

    fun build(): Person {
        return person
    }

    protected open fun self(): SELF {
        return this as SELF
    }
}

class EmployeeBuilder : PersonBuilder<EmployeeBuilder>() {
    fun worksAt(position: String): EmployeeBuilder {
        person.position = position
        return this
    }

    override fun self(): EmployeeBuilder {
        return this
    }
}

package creational_patterns.builder

class Person2 {
    var streetAddress = ""
    var postcode = ""
    var city = ""

    var companyName = ""
    var position = ""
    var annualIncome = 0

    override fun toString(): String {
        return "Person(streetAddress='$streetAddress', postcode='$postcode', city='$city', companyName='$companyName', position='$position', annualIncome=$annualIncome)"
    }
}

open class PersonBuilder2 {
    var person = Person2()

    fun build(): Person2 {
        return person
    }

    fun lives(): PersonAddressBuilder {
        return PersonAddressBuilder(person)
    }

    fun works(): PersonJobBuilder {
        return PersonJobBuilder(person)
    }
}

class PersonAddressBuilder(person: Person2) : PersonBuilder2() {
    init {
        this.person = person
    }

    fun at(streetAddress: String): PersonAddressBuilder {
        person.streetAddress = streetAddress
        return this
    }

    fun withPostcode(postCode: String): PersonAddressBuilder {
        person.postcode = postCode
        return this
    }

    fun inCity(city: String): PersonAddressBuilder {
        person.city = city
        return this
    }
}

class PersonJobBuilder(person: Person2) : PersonBuilder2() {
    init {
        this.person = person
    }

    fun at(companyName: String): PersonJobBuilder {
        person.companyName = companyName
        return this
    }

    fun asA(postCode: String): PersonJobBuilder {
        person.position = postCode
        return this
    }

    fun earning(annualIncome: Int): PersonJobBuilder {
        person.annualIncome = annualIncome
        return this
    }
}

fun main() {
    val personBuilder = PersonBuilder2()
    val person = personBuilder
        .lives()
            .at("123 London Road")
            .inCity("London")
            .withPostcode("SW12BC")
        .works()
            .at("Fabrikam")
            .asA("Engineer")
            .earning(123000)
        .build()

    println(person)
}

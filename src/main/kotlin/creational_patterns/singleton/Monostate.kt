package creational_patterns.singleton

class ChiefExecutiveOfficer {
    companion object {
        private var name: String = ""
        private var age: Int = 0
    }

    fun getName(): String {
        return name
    }

    fun setName(name: String) {
        ChiefExecutiveOfficer.name = name
    }

    fun getAge(): Int {
        return age
    }

    fun setAge(age: Int) {
        ChiefExecutiveOfficer.age = age
    }

    override fun toString(): String {
        return "ChiefExecutiveOfficer(name='$name', age=$age)"
    }
}

fun main() {
    val ceo = ChiefExecutiveOfficer()
    ceo.setName("Adam Smith")
    ceo.setAge(55)

    val ceo2 = ChiefExecutiveOfficer()
    println(ceo2)
}

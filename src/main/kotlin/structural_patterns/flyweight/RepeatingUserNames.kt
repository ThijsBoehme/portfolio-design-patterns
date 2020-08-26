package structural_patterns.flyweight

class User(
    private val fullName: String
)

class User2(
    fullName: String
) {
    private val names: IntArray

    init {
        names = fullName.split(" ").stream()
            .mapToInt { getOrAdd(it) }
            .toArray()
    }

    companion object {
        val strings = ArrayList<String>()
    }

    private fun getOrAdd(string: String): Int {
        val index = strings.indexOf(string)
        return if (index != -1) index
        else {
            strings.add(string)
            strings.size - 1
        }
    }
}

fun main() {
    val john = User("John Smith")
    val jane = User("Jane Smith")

    // Saves a few bytes
    val john2 = User2("John Smith")
    val jane2 = User2("Jane Smith")
}

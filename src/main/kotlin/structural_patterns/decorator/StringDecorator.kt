package structural_patterns.decorator

class MagicString(var string: String) {
    fun getNumberOfVowels(): Long {
        return string.chars()
            .mapToObj { c -> c.toChar() }
            .filter { c -> "aeiou".contains(c.toString()) }
            .count()
    }

    override fun toString(): String {
        return string
    }

    // Delegated methods
    // ...
    // ...
}

fun main() {
    val hello = MagicString("hello")
    println("$hello has ${hello.getNumberOfVowels()} vowels")
}

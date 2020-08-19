package structural_patterns.decorator

class MyStringBuilder() {
    private lateinit var stringBuilder: StringBuilder

    init {
        stringBuilder = StringBuilder()
    }

    constructor(string: String): this() {
        stringBuilder = StringBuilder(string)
    }

    // Adapter stuff:

    // Return a new MyStringBuilder (vs append which returns the same MyStringBuilder that has been altered)
    fun concatenate(string: String): MyStringBuilder {
        return MyStringBuilder(stringBuilder.toString() + string)
    }

    fun appendLine(string: String): MyStringBuilder {
        stringBuilder.append(string).append(System.lineSeparator())
        return this
    }

    override fun toString(): String {
        return stringBuilder.toString()
    }

    // Decorator stuff:
    // Implement all delegation methods
    // and replace all return types and statements with `MyStringBuilder` and `return this`
    // ...
    // ...
    fun append(string: String): MyStringBuilder {
        stringBuilder.append(string)
        return this
    }
}

fun main() {
    val builder = MyStringBuilder()
    builder.append("hello").appendLine(" world")
    println(builder.concatenate("and this too"))
}

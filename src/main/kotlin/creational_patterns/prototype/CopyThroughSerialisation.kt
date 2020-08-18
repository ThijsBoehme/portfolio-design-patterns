package creational_patterns.prototype

import org.apache.commons.lang3.SerializationUtils
import java.io.Serializable

class Foo(var stuff: Int, var whatever: String): Serializable {
    override fun toString(): String {
        return "Foo(stuff=$stuff, whatever='$whatever')"
    }
}

fun main() {
    val foo = Foo(42, "life")

    val foo2 = SerializationUtils.roundtrip(foo)
    foo2.whatever = "xyz"

    println(foo)
    println(foo2)
}

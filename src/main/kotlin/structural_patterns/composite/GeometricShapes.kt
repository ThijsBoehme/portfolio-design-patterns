package structural_patterns.composite

import java.util.*
import kotlin.collections.ArrayList

open class GraphicObject {
    var name = "Group"
    var colour: String? = null
    var children = ArrayList<GraphicObject>()

    override fun toString(): String {
        val stringBuilder = StringBuilder()
        print(stringBuilder, 0)
        return stringBuilder.toString()
    }

    private fun print(stringBuilder: StringBuilder, depth: Int) {
        stringBuilder.append(Collections.nCopies(depth, "*").joinToString(""))
            .append(if (depth > 0) " " else "")
            .append(if (colour.isNullOrEmpty()) "" else "$colour ")
            .append(name)
            .append(System.lineSeparator())

        for (child in children) {
            child.print(stringBuilder, depth + 1)
        }
    }
}

class Circle(colour: String): GraphicObject() {
    init {
        name = "Circle"
        this.colour = colour
    }
}

class Square(colour: String): GraphicObject() {
    init {
        name = "Square"
        this.colour = colour
    }
}

fun main() {
    val drawing = GraphicObject()
    drawing.name = "My Drawing"
    drawing.children.add(Square("Red"))
    drawing.children.add(Circle("Yellow"))

    val group = GraphicObject()
    group.children.add(Circle("Blue"))
    group.children.add(Square("Blue"))

    drawing.children.add(group)

    println(drawing)
}

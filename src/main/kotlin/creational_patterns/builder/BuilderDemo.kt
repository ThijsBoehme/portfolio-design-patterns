package creational_patterns.builder

import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val hello = "hello"
    println("<p>$hello</p>")

    val words = arrayOf("hello", "world")
    val builder = StringBuilder()
    builder.append("<ul>\n")
    for (word in words) {
        builder.append("  <li>$word</li>\n")
    }
    builder.append("</ul>")

    println(builder)

    val htmlBuilder = HTMLBuilder("ul")
    htmlBuilder.addChild("li", "hello")
        .addChild("li", "world")
    println(htmlBuilder)

    // Fluent interface:
    val stringBuilder = StringBuilder()
    stringBuilder.append("").append("").append("")
}

class HTMLElement(var name: String = "", var text: String = "") {
    val elements = ArrayList<HTMLElement>()
    private val indentSize = 2
    private val newLine = System.lineSeparator()

    private fun toStringImplementation(indent: Int): String {
        val stringBuilder = StringBuilder()
        val i = Collections.nCopies(indent * indentSize, " ").joinToString("")
        stringBuilder.append("$i<$name>$newLine")

        if (text.isNotEmpty()) {
            stringBuilder.append(Collections.nCopies(indentSize*(indent+1), " ").joinToString(""))
                .append(text)
                .append(newLine)
        }

        for (element in elements) {
            stringBuilder.append(element.toStringImplementation(indent + 1))
        }

        stringBuilder.append("$i</$name>$newLine")
        return stringBuilder.toString()
    }

    override fun toString(): String {
        return toStringImplementation(0)
    }
}

class HTMLBuilder(private val rootName: String) {
    private var root = HTMLElement()

    init {
        root.name = rootName
    }

    fun addChild(childName: String, childText: String): HTMLBuilder {
        val element = HTMLElement(childName, childText)
        root.elements.add(element)
        return this
    }

    fun clear() {
        root = HTMLElement()
        root.name = rootName
    }

    override fun toString(): String {
        return root.toString()
    }
}

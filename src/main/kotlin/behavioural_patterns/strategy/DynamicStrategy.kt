package behavioural_patterns.strategy

enum class OutputFormat {
    MARKDOWN, HTML
}

interface ListStrategy {
    fun start(builder: StringBuilder) {}
    fun addListItem(builder: StringBuilder, item: String)
    fun end(builder: StringBuilder) {}
}

class MarkdownListStrategy: ListStrategy {
    override fun addListItem(builder: StringBuilder, item: String) {
        builder.append(" * $item")
            .append(System.lineSeparator())
    }
}

class HTMLListStrategy: ListStrategy {
    override fun start(builder: StringBuilder) {
        builder.append("<ul>")
            .append(System.lineSeparator())
    }

    override fun addListItem(builder: StringBuilder, item: String) {
        builder.append("  <li>$item</li>")
            .append(System.lineSeparator())
    }

    override fun end(builder: StringBuilder) {
        builder.append("</ul>")
            .append(System.lineSeparator())
    }
}

class TextProcessor(format: OutputFormat) {
    private val builder = StringBuilder()
    private lateinit var listStrategy: ListStrategy

    init {
        setOutputFormat(format)
    }

    fun setOutputFormat(format: OutputFormat) {
        listStrategy = when (format) {
            OutputFormat.MARKDOWN -> MarkdownListStrategy()
            OutputFormat.HTML -> HTMLListStrategy()
        }
    }

    fun appendList(items: List<String>) {
        listStrategy.start(builder)
        for (item in items) {
            listStrategy.addListItem(builder, item)
        }
        listStrategy.end(builder)
    }

    fun clear() {
        builder.setLength(0)
    }

    override fun toString(): String {
        return builder.toString()
    }
}

fun main() {
    val processor = TextProcessor(OutputFormat.MARKDOWN)
    processor.appendList(listOf("liberté", "égalité", "fraternité"))
    println(processor)

    processor.clear()
    processor.setOutputFormat(OutputFormat.HTML)
    processor.appendList(listOf("inheritance", "encapsulation", "polymorphism"))
    println(processor)
}

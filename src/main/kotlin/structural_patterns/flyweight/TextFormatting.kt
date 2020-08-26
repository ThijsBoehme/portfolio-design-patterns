package structural_patterns.flyweight

class FormattedText(
    private val plainText: String
) {
    // Memory-heavy implementation: boolean flag for every character
    private val capitalised = BooleanArray(plainText.length)

    fun capitalise(start: Int, end: Int) {
        for (i in start..end) {
            capitalised[i] = true
        }
    }

    override fun toString(): String {
        val builder = StringBuilder()
        for (i in plainText.indices) {
            val character = plainText[i]
            builder.append(
                if (capitalised[i]) Character.toUpperCase(character) else character
            )
        }
        return builder.toString()
    }
}

class BetterFormattedText(
    private val plainText: String
) {
    private val formatting = ArrayList<TextRange>()

    fun getRange(start: Int, end: Int): TextRange {
        val range = TextRange(start, end)
        formatting.add(range)
        return range
    }

    override fun toString(): String {
        val builder = StringBuilder()
        for (i in plainText.indices) {
            var character = plainText[i]
            for (range in formatting)
                if (range.covers(i) && range.capitalise)
                    character = Character.toUpperCase(character)

            builder.append(character)
        }
        return builder.toString()
    }


    class TextRange(
        val start: Int,
        val end: Int
    ) {
        var capitalise = false
        var bold = false
        var italic = false

        fun covers(position: Int): Boolean {
            return position in start..end
        }
    }
}

fun main() {
    val formattedText = FormattedText("This is a brave new world")
    formattedText.capitalise(10, 15)
    println(formattedText)

    val betterFormattedText = BetterFormattedText("Make America Great Again")
    betterFormattedText.getRange(13, 18).capitalise = true
    println(betterFormattedText)
}

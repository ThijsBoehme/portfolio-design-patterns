package solid_design_principles.single_responsibility_principle

class Journal {
    private val entries = ArrayList<String>()

    companion object {
        private var count = 0
    }

    fun addEntry(text: String) {
        entries.add("${++count}: $text")
    }

    fun removeEntry(index: Int) {
        entries.removeAt(index)
    }

    // Not single responsibility > Move to Persistence class
    // fun save(filename: String) {
    //     val printStream = PrintStream(filename)
    //     printStream.use {
    //         it.println(toString())
    //     }
    // }

    // fun load(filename: String) { }
    // fun load(url: URL) { }

    override fun toString(): String {
        return entries.joinToString(System.lineSeparator())
    }
}

package solid_design_principles.single_responsibility_principle

fun main() {
    val journal = Journal()
    journal.addEntry("I cried today")
    journal.addEntry("I ate a bug")

    println(journal)

    val persistence = Persistence()
    val filename = "journal.txt"
    persistence.saveToFile(journal, filename, true)
}

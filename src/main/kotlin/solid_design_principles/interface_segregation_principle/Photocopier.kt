package solid_design_principles.interface_segregation_principle

class Photocopier : Printer, Scanner {
    override fun print(document: Document) {
        TODO("Not yet implemented")
    }

    override fun scan(document: Document) {
        TODO("Not yet implemented")
    }
}

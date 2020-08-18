package solid_design_principles.interface_segregation_principle

class MultiFunctionMachine(private val printer: Printer, private val scanner: Scanner) : MultiFunctionDevice {
    override fun print(document: Document) {
        printer.print(document)
    }

    override fun scan(document: Document) {
        scanner.scan(document)
    }
}

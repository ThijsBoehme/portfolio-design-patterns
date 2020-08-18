package solid_design_principles.interface_segregation_principle

interface Machine {
    fun print(document: Document)
    fun fax(document: Document)
    fun scan(document: Document)
}

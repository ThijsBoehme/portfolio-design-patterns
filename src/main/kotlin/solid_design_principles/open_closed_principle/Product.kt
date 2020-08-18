package solid_design_principles.open_closed_principle

class Product(val name: String, val colour: Colour, val size: Size) {
    override fun toString(): String {
        return "$name is $size and $colour"
    }
}

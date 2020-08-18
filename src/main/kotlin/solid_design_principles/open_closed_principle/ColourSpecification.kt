package solid_design_principles.open_closed_principle

class ColourSpecification(val colour: Colour): Specification<Product> {
    override fun isSatisfied(item: Product): Boolean {
        return item.colour == colour
    }
}

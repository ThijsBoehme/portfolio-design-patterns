package solid_design_principles.open_closed_principle

class SizeSpecification(val size: Size): Specification<Product> {
    override fun isSatisfied(item: Product): Boolean {
        return item.size == size
    }
}

package solid_design_principles.open_closed_principle

class AndSpecification<T>(val first: Specification<T>, val second: Specification<T>): Specification<T> {
    override fun isSatisfied(item: T): Boolean {
        return first.isSatisfied(item) && second.isSatisfied(item)
    }
}

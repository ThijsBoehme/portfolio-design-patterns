package solid_design_principles.open_closed_principle

interface Specification<T> {
    fun isSatisfied(item: T): Boolean
}

package solid_design_principles.open_closed_principle

import java.util.stream.Stream

class BetterFilter: Filter<Product> {
    override fun filter(items: List<Product>, specification: Specification<Product>): Stream<Product> {
        return items.stream().filter { specification.isSatisfied(it) }
    }
}

package solid_design_principles.open_closed_principle

import java.util.stream.Stream

interface Filter<T> {
    fun filter(items: List<T>, specification: Specification<T>): Stream<T>
}

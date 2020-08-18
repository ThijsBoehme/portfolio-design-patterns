package solid_design_principles.open_closed_principle

import java.util.stream.Stream

class ProductFilter {
    fun filterByColour(products: List<Product>, colour: Colour): Stream<Product> {
        return products.stream().filter { it.colour == colour }
    }

    fun filterBySize(products: List<Product>, size: Size): Stream<Product> {
        return products.stream().filter { it.size == size }
    }

    fun filterByColourAndSize(products: List<Product>, colour: Colour, size: Size): Stream<Product> {
        return products.stream().filter { it.colour == colour && it.size == size }
    }
}

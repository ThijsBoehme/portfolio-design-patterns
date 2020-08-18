package solid_design_principles.open_closed_principle

fun main() {
    val apple = Product("Apple", Colour.GREEN, Size.SMALL)
    val tree = Product("Tree", Colour.GREEN, Size.LARGE)
    val house = Product("House", Colour.BLUE, Size.LARGE)

    val products = listOf(apple, tree, house)
    val productFilter = ProductFilter()

    println("Green products (old implementation):")
    productFilter.filterByColour(products, Colour.GREEN)
        .forEach { println(it) }
    println()

    val betterFilter = BetterFilter()
    println("Green products (new implementation):")
    betterFilter.filter(products, ColourSpecification(Colour.GREEN))
        .forEach { println(it) }
    println()

    println("Large blue items:")
    betterFilter.filter(
        products,
        AndSpecification(
            ColourSpecification(Colour.BLUE),
            SizeSpecification(Size.LARGE)
        )
    )
        .forEach { println(it) }
    println()
}

package solid_design_principles.liskov_substitution_principle

fun useIt(rectangle: Rectangle) {
    val width = rectangle.width
    rectangle.height = 10

    // Area = width * 10
    println("Expected area of ${width * 10}, got ${rectangle.getArea()}")
}

fun main() {
    val rectangle = Rectangle(2, 3)
    useIt(rectangle)

    val square: Rectangle = Square()
    square.width = 5
    useIt(square)
}

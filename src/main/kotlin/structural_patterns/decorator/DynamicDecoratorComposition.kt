package structural_patterns.decorator

interface Shape {
    fun info(): String
}

class Circle(): Shape {
    private var radius: Double? = null

    constructor(radius: Double): this() {
        this.radius = radius
    }

    fun resize(factor: Double) {
        radius = radius?.times(factor)
    }

    override fun info(): String {
        return "A circle of radius $radius"
    }
}

class Square(): Shape {
    private var side: Double? = null

    constructor(side: Double): this() {
        this.side = side
    }

    override fun info(): String {
        return "A square with side $side"
    }
}

class ColouredShape(private val shape: Shape, private val colour: String): Shape {
    override fun info(): String {
        return "${shape.info()} has the colour $colour"
    }
}

class TransparentShape(private val shape: Shape, private val transparency: Int): Shape {
    override fun info(): String {
        return "${shape.info()} has $transparency % transparency"
    }
}

fun main() {
    val circle = Circle(10.0)
    println(circle.info())

    val blueSquare = ColouredShape(Square(20.0), "blue")
    println(blueSquare.info())

    val greenTransparentCircle = TransparentShape(ColouredShape(Circle(5.0), "green"), 50)
    println(greenTransparentCircle.info())
}

package structural_patterns.decorator

import java.util.function.Supplier

interface Shape2 {
    fun info(): String
}

class Circle2(): Shape2 {
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

class Square2(): Shape2 {
    private var side: Double? = null

    constructor(side: Double): this() {
        this.side = side
    }

    override fun info(): String {
        return "A square with side $side"
    }
}

class ColouredShape2<T: Shape2>(): Shape2 {
    private var shape: Shape2? = null
    private var colour: String = ""

    constructor(shapeSupplier: Supplier<out T>, colour: String): this() {
        this.shape = shapeSupplier.get()
        this.colour = colour
    }

    override fun info(): String {
        return "${shape?.info()} has the colour $colour"
    }
}

class TransparentShape2<T: Shape2>(): Shape2 {
    private var shape: Shape2? = null
    private var transparency: Int = 0

    constructor(shapeSupplier: Supplier<out T>, transparency: Int): this() {
        this.shape = shapeSupplier.get()
        this.transparency = transparency
    }

    override fun info(): String {
        return "${shape?.info()} has $transparency % transparency"
    }
}

fun main() {
    val blueSquare = ColouredShape2({ Square2(20.0) }, "blue")
    println(blueSquare.info())

    val transparentGreenCircle = TransparentShape2(
        { ColouredShape2({ Circle2(5.0) }, "green") },
        50
    )
    println(transparentGreenCircle.info())
}

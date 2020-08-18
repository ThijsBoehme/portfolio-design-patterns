package solid_design_principles.liskov_substitution_principle

open class Rectangle() {
    open var width: Int = 0
    open var height: Int = 0

    constructor(width: Int, height: Int): this() {
        this.width = width
        this.height = height
    }

    fun getArea(): Int {
        return width * height
    }

    fun isSquare(): Boolean {
        return width == height
    }

    override fun toString(): String {
        return "Rectangle(width=$width, height=$height)"
    }
}

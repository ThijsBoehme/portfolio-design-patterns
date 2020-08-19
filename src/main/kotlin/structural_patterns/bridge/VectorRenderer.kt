package structural_patterns.bridge

class VectorRenderer: Renderer {
    override fun renderCircle(radius: Double) {
        println("Drawing a circle of radius $radius")
    }
}

package structural_patterns.bridge

class RasterRenderer: Renderer {
    override fun renderCircle(radius: Double) {
        println("Drawing pixels for a circle of radius $radius")
    }
}

package structural_patterns.bridge

class Circle(renderer: Renderer, var radius: Double): Shape(renderer) {
    override fun draw() {
        renderer.renderCircle(radius)
    }

    override fun resize(factor: Double) {
        radius *= factor
    }
}

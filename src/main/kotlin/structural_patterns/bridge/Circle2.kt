package structural_patterns.bridge

import com.google.inject.Inject

class Circle2 @Inject constructor(renderer: Renderer): Shape(renderer) {
    var radius = 0.0

    override fun draw() {
        renderer.renderCircle(radius)
    }

    override fun resize(factor: Double) {
        radius *= factor
    }
}

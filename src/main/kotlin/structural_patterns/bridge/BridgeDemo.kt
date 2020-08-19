package structural_patterns.bridge

import com.google.inject.Guice

fun main() {
    val rasterRenderer = RasterRenderer()
    val vectorRenderer = VectorRenderer()

    val circle = Circle(vectorRenderer, 5.0)
    circle.draw()
    circle.resize(2.0)
    circle.draw()

    val injector = Guice.createInjector(ShapeModule())
    val instance = injector.getInstance(Circle2::class.java)
    instance.radius = 3.0
    instance.draw()
    instance.resize(2.0)
    instance.draw()
}

package structural_patterns.bridge

import com.google.inject.AbstractModule

class ShapeModule: AbstractModule() {
    override fun configure() {
        bind(Renderer::class.java).to(VectorRenderer::class.java)
    }
}

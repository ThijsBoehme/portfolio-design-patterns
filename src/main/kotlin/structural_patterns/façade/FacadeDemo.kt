package structural_patterns.façade

fun main() {
    // Low-level API, not user-friendly
    val buffer = Buffer(30, 20)
    val viewport = Viewport(buffer, 30, 20, 0, 0)
    val console = Console(30, 20)
    console.addViewport(viewport)
    console.render()

    // Façade implementation, simplified API
    val console2 = Console.newConsole(30, 20)
    console2.render()
}

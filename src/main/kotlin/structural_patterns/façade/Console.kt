package structural_patterns.façade

class Console(
    private val width: Int,
    private val height: Int
) {
    private val viewports = ArrayList<Viewport>()

    fun addViewport(viewport: Viewport) {
        viewports.add(viewport)
    }

    fun render() {
        for (y in 0 until height) {
            for (x in 0 until width) {
                for (viewport in viewports) {
                    print(viewport.charAt(x, y))
                }
            }
            println()
        }
    }
    
    companion object {
        fun newConsole(width: Int, height: Int): Console {
            val buffer = Buffer(width, height)
            val viewport = Viewport(buffer, width, height, 0, 0)
            val console = Console(width, height)
            console.addViewport(viewport)
            return console
        }
    }
}

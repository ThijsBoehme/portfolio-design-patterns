package structural_patterns.façade

class Viewport(
    private val buffer: Buffer,
    private val width: Int,
    private val height: Int,
    private val offsetX: Int,
    private val offsetY: Int
) {
    fun charAt(x: Int, y: Int): Char? {
        return buffer.charAt(x + offsetX, y + offsetY)
    }
}

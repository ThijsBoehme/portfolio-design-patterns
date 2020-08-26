package structural_patterns.façade

class Buffer(
    lineHeight: Int,
    private val lineWidth: Int,
) {
    private val characters: Array<Char?> = arrayOfNulls<Char?>(lineWidth * lineHeight)

    fun charAt(x: Int, y: Int): Char? {
        return characters[y*lineWidth + x]
    }
}

package structural_patterns.adapter

class Point(val x: Int, val y: Int) {
    override fun toString(): String {
        return "Point(x=$x, y=$y)"
    }
}

class Line(val start: Point, val end: Point)

open class VectorObject: ArrayList<Line>()

class VectorRectangle(x: Int, y: Int, width: Int, height: Int): VectorObject() {
    init {
        add(Line(Point(x, y), Point(x + width, y)))
        add(Line(Point(x + width, y), Point(x + width, y + height)))
        add(Line(Point(x + width, y + height), Point(x, y + height)))
        add(Line(Point(x, y + height), Point(x, y)))
    }
}

class LineToPointAdapter(line: Line): ArrayList<Point>() {
    init {
        println("${++count}: Generating points for line [${line.start.x}, ${line.start.y}]-[${line.end.x}, ${line.end.y}] (no caching)")

        val left = kotlin.math.min(line.start.x, line.end.x)
        val right = kotlin.math.max(line.start.x, line.end.x)
        val top = kotlin.math.min(line.start.y, line.end.y)
        val bottom = kotlin.math.max(line.start.y, line.end.y)
        val dx = right - left
        val dy = line.end.y - line.start.y

        if (dx == 0) {
            for (y in top + 1 until bottom) {
                add(Point(left, y))
            }
        } else if (dy == 0) {
            for (x in left + 1 until right) {
                add(Point(x, top))
            }
        }
    }

    companion object {
        private var count = 0
    }
}

private val vectorObjects = listOf(
    VectorRectangle(1, 1, 10, 10),
    VectorRectangle(3, 3, 6, 6)
)

private fun drawPoint(point: Point) {
    println(".")
}

private fun draw() {
    for (vectorObject in vectorObjects) {
        for (line in vectorObject) {
            val adapter = LineToPointAdapter(line)
            adapter.forEach { drawPoint(it) }
        }
    }
}

fun main() {
    draw()
}

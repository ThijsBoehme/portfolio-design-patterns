package structural_patterns.adapter

import java.util.*
import java.util.function.Consumer
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class Point2(val x: Int, val y: Int) {
    override fun toString(): String {
        return "Point(x=$x, y=$y)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Point2

        if (x != other.x) return false
        if (y != other.y) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }
}

class Line2(val start: Point2, val end: Point2) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Line2

        if (start != other.start) return false
        if (end != other.end) return false

        return true
    }

    override fun hashCode(): Int {
        var result = start.hashCode()
        result = 31 * result + end.hashCode()
        return result
    }
}

open class VectorObject2: ArrayList<Line2>()

class VectorRectangle2(x: Int, y: Int, width: Int, height: Int): VectorObject2() {
    init {
        add(Line2(Point2(x, y), Point2(x + width, y)))
        add(Line2(Point2(x + width, y), Point2(x + width, y + height)))
        add(Line2(Point2(x + width, y + height), Point2(x, y + height)))
        add(Line2(Point2(x, y + height), Point2(x, y)))
    }
}

class LineToPointAdapter2(line: Line2): Iterable<Point2> {
    private var hash = 0

    init {
        run {
            hash = line.hashCode()
            if (cache[hash] != null) {
                return@run
            }

            println("${++count}: Generating points for line [${line.start.x}, ${line.start.y}]-[${line.end.x}, ${line.end.y}] (no caching)")

            val points = ArrayList<Point2>()

            val left = kotlin.math.min(line.start.x, line.end.x)
            val right = kotlin.math.max(line.start.x, line.end.x)
            val top = kotlin.math.min(line.start.y, line.end.y)
            val bottom = kotlin.math.max(line.start.y, line.end.y)
            val dx = right - left
            val dy = line.end.y - line.start.y

            if (dx == 0) {
                for (y in top + 1 until bottom) {
                    points.add(Point2(left, y))
                }
            } else if (dy == 0) {
                for (x in left + 1 until right) {
                    points.add(Point2(x, top))
                }
            }

            cache[hash] = points
        }
    }

    companion object {
        private var count = 0
        private val cache = HashMap<Int, List<Point2>>()
    }

    override fun iterator(): Iterator<Point2> {
        return cache[hash]!!.iterator()
    }

    override fun forEach(action: Consumer<in Point2>?) {
        cache[hash]!!.forEach(action)
    }

    override fun spliterator(): Spliterator<Point2> {
        return cache[hash]!!.spliterator()
    }
}

private val vectorObjects = listOf(
    VectorRectangle2(1, 1, 10, 10),
    VectorRectangle2(3, 3, 6, 6)
)

private fun drawPoint() {
    println(".")
}

private fun draw() {
    for (vectorObject in vectorObjects) {
        for (line in vectorObject) {
            val adapter = LineToPointAdapter2(line)
            adapter.forEach { _ -> drawPoint() }
        }
    }
}

fun main() {
    draw()
    draw()
}

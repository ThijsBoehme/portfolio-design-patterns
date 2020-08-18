package creational_patterns.factories

import kotlin.math.cos
import kotlin.math.sin

class Point private constructor(private val x: Double, private val y: Double) {
    companion object {
        fun fromCartesian(x: Double, y: Double): Point {
            return Point(x, y)
        }

        fun fromPolar(rho: Double, theta: Double): Point {
            return Point(rho * cos(theta), rho * sin(theta))
        }
    }
}

fun main() {
    val cartesianPoint = Point.fromCartesian(2.0, 3.0)
    val polarPoint = Point.fromPolar(2.0, 3.0)
}

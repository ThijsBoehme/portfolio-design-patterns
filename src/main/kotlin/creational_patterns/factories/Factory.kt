package creational_patterns.factories

import kotlin.math.cos
import kotlin.math.sin

class Point2 private constructor(private val x: Double, private val y: Double) {
    object Factory {
        fun fromCartesian(x: Double, y: Double): Point2 {
            return Point2(x, y)
        }

        fun fromPolar(rho: Double, theta: Double): Point2 {
            return Point2(rho * cos(theta), rho * sin(theta))
        }
    }
}

fun main() {
    val cartesianPoint = Point2.Factory.fromCartesian(2.0, 3.0)
    val polarPoint = Point2.Factory.fromPolar(2.0, 3.0)
}

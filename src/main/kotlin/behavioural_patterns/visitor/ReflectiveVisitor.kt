package behavioural_patterns.visitor

// Need access to private members
// Can skip non-implemented classes because of hard-coded check

abstract class Expression2

class DoubleExpression2(val value: Double): Expression2()

class AdditionExpression2(val left: Expression2, val right: Expression2): Expression2()

class ExpressionPrinter {
    companion object {
        fun print(expression: Expression2, builder: StringBuilder) {
            if (expression.javaClass == DoubleExpression2::class.java) {
                builder.append((expression as DoubleExpression2).value)
            } else if (expression.javaClass == AdditionExpression2::class.java) {
                val additionExpression = expression as AdditionExpression2
                builder.append("(")
                print(additionExpression.left, builder)
                builder.append(" + ")
                print(additionExpression.right, builder)
                builder.append(")")
            }
        }
    }
}

fun main() {
    // 1 + (2 + 3)
    val expression = AdditionExpression2(
        DoubleExpression2(1.0),
        AdditionExpression2(
            DoubleExpression2(2.0),
            DoubleExpression2(3.0)
        )
    )

    val builder = StringBuilder()
    ExpressionPrinter.print(expression, builder)
    println(builder.toString())
}

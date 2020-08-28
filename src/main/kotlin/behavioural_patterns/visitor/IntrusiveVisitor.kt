package behavioural_patterns.visitor

// Intrusive: had to add to every class manually
// - Breaks OCP & SRP

abstract class Expression {
    abstract fun print(builder: StringBuilder)
}

class DoubleExpression(private val value: Double): Expression() {
    override fun print(builder: StringBuilder) {
        builder.append(value)
    }
}

class AdditionExpression(private val left: Expression, private val right: Expression): Expression() {
    override fun print(builder: StringBuilder) {
        builder.append("(")
        left.print(builder)
        builder.append(" + ")
        right.print(builder)
        builder.append(")")
    }
}

fun main() {
    // 1 + (2 + 3)
    val expression = AdditionExpression(
        DoubleExpression(1.0),
        AdditionExpression(
            DoubleExpression(2.0),
            DoubleExpression(3.0)
        )
    )

    val builder = StringBuilder()
    expression.print(builder)
    println(builder.toString())
}

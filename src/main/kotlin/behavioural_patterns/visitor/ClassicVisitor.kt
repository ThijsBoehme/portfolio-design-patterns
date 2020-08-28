package behavioural_patterns.visitor

// Any visitor has to implement this interface
interface ExpressionVisitor {
    fun visit(expression: DoubleExpression3)
    fun visit(expression: AdditionExpression3)
}

abstract class Expression3 {
    abstract fun accept(visitor: ExpressionVisitor)
}

class DoubleExpression3(val value: Double): Expression3() {
    override fun accept(visitor: ExpressionVisitor) {
        visitor.visit(this)
    }
}

class AdditionExpression3(val left: Expression3, val right: Expression3): Expression3() {
    override fun accept(visitor: ExpressionVisitor) {
        visitor.visit(this)
    }
}

class ExpressionPrinter2: ExpressionVisitor {
    private val builder = StringBuilder()

    override fun visit(expression: DoubleExpression3) {
        builder.append(expression.value)
    }

    override fun visit(expression: AdditionExpression3) {
        builder.append("(")
        expression.left.accept(this)
        builder.append(" + ")
        expression.right.accept(this)
        builder.append(")")
    }

    override fun toString(): String {
        return builder.toString()
    }
}

class ExpressionCalculator: ExpressionVisitor {
    var result = 0.0

    override fun visit(expression: DoubleExpression3) {
        result = expression.value
    }

    override fun visit(expression: AdditionExpression3) {
        expression.left.accept(this)
        val a = result
        expression.right.accept(this)
        val b = result
        result = a + b
    }
}

fun main() {
    // 1 + (2 + 3)
    val expression = AdditionExpression3(
        DoubleExpression3(1.0),
        AdditionExpression3(
            DoubleExpression3(2.0),
            DoubleExpression3(3.0)
        )
    )

    val printer = ExpressionPrinter2()
    printer.visit(expression)
    println(printer)

    val calculator = ExpressionCalculator()
    calculator.visit(expression)
    println("$printer = ${calculator.result}")
}

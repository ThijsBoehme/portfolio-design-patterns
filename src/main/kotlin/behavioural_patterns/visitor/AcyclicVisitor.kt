package behavioural_patterns.visitor

// Flexible! ExpressionPrinter can implement the Visitor interfaces it wants.
// Dropping implementation of the DoubleExpressionVisitor does not break the printer

// Marker interface
interface Visitor

interface ExpressionVisitor2: Visitor {
    fun visit(expression: Expression4)
}

interface DoubleExpressionVisitor: Visitor {
    fun visit(expression: DoubleExpression4)
}

interface AdditionExpressionVisitor: Visitor {
    fun visit(expression: AdditionExpression4)
}

abstract class Expression4 {
    open fun accept(visitor: Visitor) {
        if (visitor is ExpressionVisitor2)
            visitor.visit(this)
    }
}

class DoubleExpression4(val value: Double): Expression4() {
    override fun accept(visitor: Visitor) {
        if (visitor is DoubleExpressionVisitor)
            visitor.visit(this)
    }
}

class AdditionExpression4(val left: Expression4, val right: Expression4): Expression4() {
    override fun accept(visitor: Visitor) {
        if (visitor is AdditionExpressionVisitor)
            visitor.visit(this)
    }
}

class ExpressionPrinter3: DoubleExpressionVisitor, AdditionExpressionVisitor {
    private val builder = StringBuilder()

    override fun visit(expression: DoubleExpression4) {
        builder.append(expression.value)
    }

    override fun visit(expression: AdditionExpression4) {
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

fun main() {
    // 1 + (2 + 3)
    val expression = AdditionExpression4(
        DoubleExpression4(1.0),
        AdditionExpression4(
            DoubleExpression4(2.0),
            DoubleExpression4(3.0)
        )
    )

    val printer = ExpressionPrinter3()
    printer.visit(expression)
    println(printer)
}

package behavioural_patterns.visitor;

abstract class ExpressionVisitor3 {
    abstract void visit(Value value);
    abstract void visit(AdditionExpression5 ae);
    abstract void visit(MultiplicationExpression me);
}

abstract class Expression5 {
    abstract void accept(ExpressionVisitor3 ev);
}

class Value extends Expression5 {
    public int value;

    public Value(int value) {
        this.value = value;
    }

    @Override
    void accept(ExpressionVisitor3 ev) {
        ev.visit(this);
    }
}

class AdditionExpression5 extends Expression5 {
    public Expression5 lhs, rhs;

    public AdditionExpression5(Expression5 lhs, Expression5 rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    void accept(ExpressionVisitor3 ev) {
        ev.visit(this);
    }
}

class MultiplicationExpression extends Expression5 {
    public Expression5 lhs, rhs;

    public MultiplicationExpression(Expression5 lhs, Expression5 rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    void accept(ExpressionVisitor3 ev) {
        ev.visit(this);
    }
}

class ExpressionPrinter4 extends ExpressionVisitor3 {
    private final StringBuilder sb = new StringBuilder();

    @Override
    void visit(Value value) {
        sb.append(value.value);
    }

    @Override
    void visit(AdditionExpression5 ae) {
        sb.append("(");
        ae.lhs.accept(this);
        sb.append("+");
        ae.rhs.accept(this);
        sb.append(")");
    }

    @Override
    void visit(MultiplicationExpression me) {
        me.lhs.accept(this);
        sb.append("*");
        me.rhs.accept(this);
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}

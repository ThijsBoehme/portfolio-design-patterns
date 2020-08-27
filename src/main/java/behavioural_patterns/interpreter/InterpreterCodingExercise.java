package behavioural_patterns.interpreter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

interface Element2 {
    int eval();
}

class ExpressionProcessor {
    public Map<Character, java.lang.Integer> variables = new HashMap<>();

    public int calculate(String expression) {
        // todo
        List<Token2> tokens = lex(expression);
        Element2 parsed = parse(tokens);
        return parsed.eval();
    }

    private List<Token2> lex(String input) {
        List<Token2> result = new ArrayList<>();

        int i = 0;
        while (i < input.length()) {
            switch (input.charAt(i)) {
                case '+':
                    result.add(new Token2(Token2.Type.PLUS, "+"));
                    break;
                case '-':
                    result.add(new Token2(Token2.Type.MINUS, "-"));
                    break;
                default:
                    StringBuilder builder = new StringBuilder();
                    Token2.Type type = null;
                    for (int j = i; j < input.length(); j++) {
                        if (Character.isDigit(input.charAt(j)) && type != Token2.Type.VARIABLE) {
                            type = Token2.Type.INTEGER;
                            builder.append(input.charAt(j));
                            ++i;
                        } else if (Character.isAlphabetic(input.charAt(j)) && type != Token2.Type.INTEGER) {
                            type = Token2.Type.VARIABLE;
                            builder.append(input.charAt(j));
                            ++i;
                        } else {
                            result.add(new Token2(type, builder.toString()));
                            i--;
                            break;
                        }
                        if (j == input.length() - 1) {
                            result.add(new Token2(type, builder.toString()));
                            i--;
                            break;
                        }
                    }
            }

            i++;
        }

        return result;
    }

    private Element2 parse(List<Token2> tokens) {

        for (Token2 token: tokens) {
            if (token.type == Token2.Type.VARIABLE && token.text.length() > 1) {
                return new Integer2(0);
            }
        }

        BinaryOperation2 result = new BinaryOperation2();
        boolean hasLeftHandSide = false;

        int i = 0;
        while (i < tokens.size()) {
            Token2 token = tokens.get(i);

            switch (token.type) {
                case INTEGER:
                    Integer2 integer = new Integer2(java.lang.Integer.parseInt(token.text));
                    if (hasLeftHandSide) result.right = integer;
                    else {
                        result.left = integer;
                        hasLeftHandSide = true;
                    }
                    break;
                case VARIABLE:
                    Variable variable = new Variable(0);
                    if (variables.containsKey(token.text.charAt(0))) {
                        variable = new Variable(variables.get(token.text.charAt(0)));
                    }
                    if (hasLeftHandSide) result.right = variable;
                    else {
                        result.left = variable;
                        hasLeftHandSide = true;
                    }
                    break;
                case PLUS:
                    List<Token2> subExpressionPlus = tokens.stream()
                            .skip(i + 1)
                            .collect(Collectors.toList());

                    i += subExpressionPlus.size();

                    result.right = parse(subExpressionPlus);
                    result.type = BinaryOperation2.Type.ADDITION;
                    break;
                case MINUS:
                    List<Token2> subExpressionMinus = tokens.stream()
                            .skip(i + 1)
                            .collect(Collectors.toList());

                    i += subExpressionMinus.size();

                    result.right = parse(subExpressionMinus);
                    result.type = BinaryOperation2.Type.SUBTRACTION;
                    break;
            }

            i++;
        }

        return result;
    }
}

class Token2 {
    Type type;
    String text;

    public Token2(Type type, String text) {
        this.type = type;
        this.text = text;
    }

    enum Type {
        INTEGER, PLUS, MINUS, VARIABLE
    }
}

class Integer2 implements Element2 {
    private final int value;

    public Integer2(int value) {
        this.value = value;
    }

    @Override
    public int eval() {
        return value;
    }
}

class Variable implements Element2 {
    private final int value;

    public Variable(int value) {
        this.value = value;
    }

    @Override
    public int eval() {
        return value;
    }
}

class BinaryOperation2 implements Element2 {
    Type type;
    Element2 left;
    Element2 right;

    @Override
    public int eval() {
        if (type == null) {
            return left.eval();
        }

        switch (type) {
            case ADDITION:
                return left.eval() + right.eval();
            case SUBTRACTION:
                return left.eval() - right.eval();
        }
        return 0;
    }

    enum Type {
        ADDITION, SUBTRACTION
    }
}

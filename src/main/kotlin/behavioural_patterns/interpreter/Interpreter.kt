package behavioural_patterns.interpreter

import java.util.stream.Collectors

interface Element {
    fun eval(): Int
}

class Integer(private val value: Int): Element {
    override fun eval(): Int {
        return value
    }
}

class BinaryOperation: Element {
    lateinit var type: Type
    lateinit var left: Element
    lateinit var right: Element

    override fun eval(): Int {
        return when (type) {
            Type.ADDITION -> left.eval() + right.eval()
            Type.SUBTRACTION -> left.eval() - right.eval()
        }
    }

    enum class Type {
        ADDITION, SUBTRACTION
    }
}

class Token(
    val type: Type,
    val text: String
) {

    override fun toString(): String {
        return "`$text`"
    }

    enum class Type {
        INTEGER, PLUS, MINUS, LEFTPARENTHESIS, RIGHTPARENTHESIS
    }
}

fun lex(input: String): List<Token> {
    val result = ArrayList<Token>()
    var i = 0
    while (i < input.length) {
        when (input[i]) {
            '+' -> result.add(Token(Token.Type.PLUS, "+"))
            '-' -> result.add(Token(Token.Type.MINUS, "-"))
            '(' -> result.add(Token(Token.Type.LEFTPARENTHESIS, "("))
            ')' -> result.add(Token(Token.Type.RIGHTPARENTHESIS, ")"))
            else -> {
                val builder = StringBuilder("${input[i]}")
                for (j in (i + 1) until input.length) {
                    if (input[j].isDigit()) {
                        builder.append(input[j])
                        ++i
                    } else {
                        result.add(Token(Token.Type.INTEGER, builder.toString()))
                        break
                    }
                }
            }
        }

        i++
    }
    return result
}

fun parse(tokens: List<Token>): Element {
    val result = BinaryOperation()
    var hasLeftHandSide = false

    var i = 0
    while (i < tokens.size) {
        val token = tokens[i]
        when (token.type) {
            Token.Type.INTEGER -> {
                val integer = Integer(token.text.toInt())
                if (hasLeftHandSide) result.right = integer
                else {
                    result.left = integer
                    hasLeftHandSide = true
                }
            }
            Token.Type.PLUS -> result.type = BinaryOperation.Type.ADDITION
            Token.Type.MINUS -> result.type = BinaryOperation.Type.SUBTRACTION
            Token.Type.LEFTPARENTHESIS -> {
                var j = i // Location of right parenthesis (assumes no nesting of parentheses)
                while (j < tokens.size) {
                    if (tokens[j].type == Token.Type.RIGHTPARENTHESIS) break
                    j++
                }

                val subExpression = tokens.stream()
                    .skip((i + 1).toLong())
                    .limit((j - i - 1).toLong())
                    .collect(Collectors.toList())

                val element = parse(subExpression)

                if (hasLeftHandSide) result.right = element
                else {
                    result.left = element
                    hasLeftHandSide = true
                }

                i = j
            }
            Token.Type.RIGHTPARENTHESIS -> {
            }
        }

        i++
    }

    return result
}

fun main() {
    val input = "(13+4)-(12+1)"
    val tokens: List<Token> = lex(input)
    println(tokens.stream().map { it.toString() }.collect(Collectors.joining("_")))

    val parsed = parse(tokens)
    println("$input = ${parsed.eval()}")
}

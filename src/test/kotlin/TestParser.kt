import me.katsumag.A2Coursework.components.CircuitComponent
import me.katsumag.A2Coursework.truth_table.lexer.IdentifierToken
import me.katsumag.A2Coursework.truth_table.lexer.OperatorToken
import me.katsumag.A2Coursework.truth_table.lexer.Token
import me.katsumag.A2Coursework.truth_table.parser.ComputedExpression
import me.katsumag.A2Coursework.truth_table.parser.Expression
import me.katsumag.A2Coursework.truth_table.parser.OperatorExpression
import me.katsumag.A2Coursework.truth_table.parser.OperatorExpressionType
import java.util.*

class TestParser(private val tokens: List<Token>) {

    private val bufferedTokens = Stack<Expression>()

    fun parse(): Expression {

        tokens.forEach { token ->

            if (token is IdentifierToken) {
                bufferedTokens.push(ComputedExpression(token.state))
                return@forEach
            }

            // token is OperatorToken

            val operator = token as OperatorToken

            // handle AND and OR gates
            if (operator.operationType != "NOT") {

                bufferedTokens.push(
                    OperatorExpression(
                        OperatorExpressionType.valueOf(operator.operationType),
                        bufferedTokens.pop() ,
                        bufferedTokens.pop()
                    )
                )
            } else {
                bufferedTokens.push(
                    OperatorExpression(OperatorExpressionType.NOT, bufferedTokens.pop(), null)
                )
            }

        }

        println(bufferedTokens)
        return bufferedTokens.pop()
    }

}
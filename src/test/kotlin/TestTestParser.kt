import me.katsumag.A2Coursework.truth_table.lexer.IdentifierToken
import me.katsumag.A2Coursework.truth_table.lexer.OperatorToken
import me.katsumag.A2Coursework.truth_table.parser.ComputedExpression
import me.katsumag.A2Coursework.truth_table.parser.OperatorExpression
import me.katsumag.A2Coursework.truth_table.parser.OperatorExpressionType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestTestParser {

    @Test fun `Test basic AND gate`() {
        val tokens = listOf(
            IdentifierToken(false),
            IdentifierToken(false),
            OperatorToken("AND"),
        )

        TestParser(tokens).parse()
    }

    @Test fun `Test basic OR gate`() {
        val tokens = listOf(
            IdentifierToken(false),
            IdentifierToken(false),
            OperatorToken("OR"),
        )

        TestParser(tokens).parse()
    }

    @Test fun `Test basic NOT gate`() {
        val tokens = listOf(
            IdentifierToken(false),
            OperatorToken("NOT"),
        )

        TestParser(tokens).parse()
    }

    @Test fun `Test real example`() {
        val tokens = listOf(
            IdentifierToken(false),
            IdentifierToken(false),
            OperatorToken("AND"),
            IdentifierToken(false),
            IdentifierToken(false),
            OperatorToken("AND"),
            OperatorToken("OR")
        )

        TestParser(tokens).parse()
    }

    @Test fun `Test extra real example`() {
        val tokens = listOf(
            IdentifierToken(false),
            IdentifierToken(false),
            OperatorToken("AND"),
            IdentifierToken(false),
            IdentifierToken(false),
            OperatorToken("NOT"),
            OperatorToken("AND"),
            OperatorToken("OR")
        )

        TestParser(tokens).parse()
    }

}
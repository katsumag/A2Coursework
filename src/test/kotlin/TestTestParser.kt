import me.katsumag.A2Coursework.karnaugh_map.BinaryToDenary
import me.katsumag.A2Coursework.karnaugh_map.GrayCode
import me.katsumag.A2Coursework.karnaugh_map.KarnaughMap
import me.katsumag.A2Coursework.karnaugh_map.MapSorter
import me.katsumag.A2Coursework.truth_table.Inputs
import me.katsumag.A2Coursework.truth_table.evaluator.TreeEvaluator
import me.katsumag.A2Coursework.truth_table.lexer.IdentifierToken
import me.katsumag.A2Coursework.truth_table.lexer.OperatorToken
import me.katsumag.A2Coursework.truth_table.parser.TreeParser
import org.junit.jupiter.api.Test

class TestTestParser {

    @Test fun `Test basic AND gate`() {
        val tokens = listOf(
            IdentifierToken(false),
            IdentifierToken(false),
            OperatorToken("AND"),
        )

        TreeParser().parse(tokens)
    }

    @Test fun `Test basic OR gate`() {
        val tokens = listOf(
            IdentifierToken(false),
            IdentifierToken(false),
            OperatorToken("OR"),
        )

        TreeParser().parse(tokens)
    }

    @Test fun `Test basic NOT gate`() {
        val tokens = listOf(
            IdentifierToken(false),
            OperatorToken("NOT"),
        )

        TreeParser().parse(tokens)
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

        TreeParser().parse(tokens)
    }

    @Test fun `Test extra real example`() {
        val tokens = listOf(
            IdentifierToken(false),
            IdentifierToken(false),
            OperatorToken("AND"),
            IdentifierToken(false),
            OperatorToken("NOT"),
            IdentifierToken(false),
            OperatorToken("AND"),
            OperatorToken("OR")
        )

        val parsedExpression = TreeParser().parse(tokens)

        println("Parsed expression: $parsedExpression")

        val treeEvaluator = TreeEvaluator()

        // get inputs from 0 to ((numOfSwitches)^2) -1
        // eg for numOfSwitches = 4 inputs will be 0 to 15
        val table: MutableMap<List<Boolean>, Boolean> = HashMap()
        for (inputs in Inputs(16).get()) {
            table[inputs] = treeEvaluator.evaluateWith(parsedExpression, ArrayList(inputs))
        }

        table.toSortedMap(compareBy { BinaryToDenary.convert(it) }).forEach { (inputs: List<Boolean>, output: Boolean) ->
            println(
                "$inputs : $output"
            )
        }

        // TRUTH TABLE IS FINALLY CORRECT USING CHATGPT

        val karnaughMap = KarnaughMap(table)
        karnaughMap.sortByGrayCode(GrayCode().get(2))
        println("Karnaugh Map ----------------------------------------------")
        karnaughMap.internalState.forEach {row -> println(row.map { cell -> cell.state }) }

    }

}
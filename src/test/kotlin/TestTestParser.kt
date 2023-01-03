import me.katsumag.A2Coursework.karnaugh_map.*
import me.katsumag.A2Coursework.truth_table.Inputs
import me.katsumag.A2Coursework.truth_table.evaluator.TreeEvaluator
import me.katsumag.A2Coursework.truth_table.lexer.IdentifierToken
import me.katsumag.A2Coursework.truth_table.lexer.OperatorToken
import me.katsumag.A2Coursework.truth_table.parser.TreeParser
import org.junit.jupiter.api.Test
import kotlin.math.roundToInt

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

        val karnaughMap = KarnaughMap(table)
        karnaughMap.sortByGrayCode(GrayCode().get(2))
        println("Karnaugh Map ----------------------------------------------")
        karnaughMap.internalState.forEach {row -> println(row.map { cell -> cell.state }) }

        val windows = Windows(karnaughMap)
        val startingWindows = windows.defaultPositionWindows

        val mapYSize = karnaughMap.internalState.size
        val mapXSize = karnaughMap.internalState[0].size
        startingWindows.forEach {
            //println("Map: $mapXSize x $mapYSize | Window: ${it.windowX} x ${it.windowY} | Current: ${it.currentX}, ${it.currentY}")
            while (it.currentY < mapYSize) {
                while (it.currentX < mapXSize) {
                    if (it.isValid) {
                        println("Window Size: ${it.windowX} x ${it.windowY} | Window position: (${it.currentX}, ${it.currentY})")
                        println(it.window)
                    }
                    it.shiftRight()
                }
                it.shiftUp()
                it.resetX()
            }
        }

    }

}
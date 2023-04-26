import me.katsumag.A2Coursework.karnaugh_map.*
import me.katsumag.A2Coursework.truth_table.DenaryToBinary
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
            IdentifierToken(false),
            OperatorToken("AND"),
            OperatorToken("OR"),
            OperatorToken("NOT")
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

        val windows = Windows(karnaughMap).validWindows
        println("Number of windows: ${windows.size}")

        val padList: (MutableList<MutableList<Boolean>>.() -> MutableList<MutableList<Boolean>>) = {
            val max = maxByOrNull { it.size }!!.size
            forEach { num ->
                val padAmount = max - num.size
                repeat(padAmount) { num.add(0, false) }
            }
            this
        }

        val finalExpression = mutableListOf<String>()
        windows.forEach { window ->
            window.printContainedCoords()
            val headers: MutableList<Int> = GrayCode().get(2)
            val topWindowRange = padList.invoke((window.currentY until window.currentY + window.windowY).map { DenaryToBinary.convert(headers[it % karnaughMap.mapYSize]) }.toMutableList())
            val sideWindowRange = padList.invoke((window.currentX until window.currentX + window.windowX).map { DenaryToBinary.convert(headers[it % karnaughMap.mapXSize]) }.toMutableList())
            println("Y range: $topWindowRange")
            println("X range: $sideWindowRange")

            val symbolStates = mutableListOf<MutableList<Boolean>>()

            window.window.forEachIndexed { y, cells ->
                cells.forEachIndexed { x, cell ->
                    val yIndex = (window.currentY + y) % karnaughMap.mapYSize
                    val xIndex = (window.currentX + x) % karnaughMap.mapXSize
                    val binYHeader = DenaryToBinary.convert(headers[yIndex]).pad(2)
                    val binXHeader = DenaryToBinary.convert(headers[xIndex]).pad(2)
                    println("Yindex: $yIndex | Xindex: $xIndex | YHeader: $binYHeader | XHeader: $binXHeader")
                    binXHeader.addAll(binYHeader)
                    symbolStates.add(binXHeader)
                }
            }

            symbolStates.forEach { println(it) }
            // symbolStates inner lists in format of [a, b, c, d]
            // TODO: Pick out unique columns
            val symbolStringList = mutableListOf<String>()

            symbolStates.forEachIndexed { index, cellHeaders ->
                val column = symbolStates.map { it[index] }
                val symbol = (65 + index).toChar()
                if (column.all { it == column[0] }) {
                    if (column[0]) { symbolStringList.add("$symbol") } else { symbolStringList.add("NOT $symbol") }
                }
            }

            finalExpression.add("(" + symbolStringList.joinToString(" AND ") + ")")

        }

        println(finalExpression.joinToString(" OR "))

    }

}

fun MutableList<Boolean>.pad(totalLength: Int): MutableList<Boolean> {
    repeat(totalLength - size) { add(0, false) }
    return this
}

fun Window.printContainedCoords() {
    println("currentX: $currentX | currentY: $currentY")
    println((currentY until currentY + windowY).map { y ->
        (currentX until currentX + windowX).map { x ->
            x % 4 to y % 4
        }
    })
}
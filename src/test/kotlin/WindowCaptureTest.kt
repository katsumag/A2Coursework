import me.katsumag.A2Coursework.karnaugh_map.Window
import me.katsumag.A2Coursework.karnaugh_map.Windows
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class WindowCaptureTest {

    private val testMap16 = listOf(
        listOf(false, false, true, false),
        listOf(true, true, true, true),
        listOf(false, false, true, false),
        listOf(false, false, true, false),
    )

    @Test fun `Test initial capture`() {
        val windowSizes = Windows(testMap16).windowSizes
        val window = Window(windowSizes[0].key, windowSizes[0].value, testMap16)
        assertEquals(window.window, testMap16)
    }

    @Test fun `Test half size capture`() {
        val windowSizes = Windows(testMap16).windowSizes
        val window = Window(windowSizes[1].key, windowSizes[1].value, testMap16)
        window.window.forEach { println(it) }
        println("------------")
        window.shiftRight()
        window.window.forEach { println(it) }
    }

    @Test fun `capture all possible windows`() {
        val windows = Windows(testMap16).windowSizes.map { Window(it.key, it.value, testMap16) }
        windows.forEach {
            println("Default position")
            validateAndPrint(it)

            print("Right Shift x1")
            it.shiftUp()
            validateAndPrint(it)

            print("Right Shift x2")
            it.shiftUp()
            validateAndPrint(it)

            println("Right shift x3")
            it.shiftUp()
            validateAndPrint(it)

            println("Right shift x4 - back to original")
            it.shiftUp()
            validateAndPrint(it)
        }
    }
    fun validateAndPrint(window: Window) {
        println("Valid Window? ${window.validate()}\n")
        window.window.forEach { println(it) }
        println("------------------------------------")
    }

}
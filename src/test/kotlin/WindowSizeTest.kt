import me.katsumag.A2Coursework.karnaugh_map.Windows
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import javafx.util.Pair

class WindowSizeTest {

    private val testMap16 = listOf(
        listOf(false, false, true, false),
        listOf(true, true, true, true),
        listOf(false, false, true, false),
        listOf(false, false, true, false)
    )

    private val testMap8 = listOf(
        listOf(false, false, true, false),
        listOf(true, true, true, true)
    )

    private val testMap4 = listOf(
        listOf(false, false),
        listOf(true, true)
    )

    @Test fun `Test window dimension generation with a 16 cell map`() {
        val windows = Windows(testMap16)
        val sizes = windows.getWindowSizes(16)
        assertEquals(sizes, listOf(Pair(4, 4), Pair(2, 4), Pair(2, 2), Pair(1, 4), Pair(1, 2), Pair(1, 1)))
    }

    @Test fun `Test window dimension generation with an 8 cell map`() {
        val windows = Windows(testMap8)
        val sizes = windows.getWindowSizes(8)
        assertEquals(sizes, listOf(Pair(2, 4), Pair(2, 2), Pair(1, 4), Pair(1, 2), Pair(1, 1)))
    }
}
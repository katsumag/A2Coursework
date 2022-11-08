import me.katsumag.A2Coursework.karnaugh_map.Windows
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import javafx.util.Pair

class WindowTest {

    private val testMap16 = listOf(
        listOf(false, false, true, false),
        listOf(true, true, true, true),
        listOf(false, false, true, false),
        listOf(false, false, true, false)
    )

    @Test
    fun `Test window dimension generation with a 16 cell map`() {
        val windows = Windows(testMap16)
        val sizes = windows.getWindowSizes(testMap16.size * testMap16.size)
        sizes.forEach { println(it) }
        assertEquals(sizes, listOf(Pair(4, 4), Pair(2, 4), Pair(2, 2), Pair(1, 4), Pair(1, 2), Pair(1, 1)))
    }

}
package present.compose

import androidx.compose.ui.geometry.Rect
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class PresentStackAppendTest {
    @Test
    fun screensAppendDifferentStylesOntoOneStack() {
        val stack = PresentStack<String>()
        stack.dialog("A")
        stack.bottomSheet("B")
        stack.screen("C")
        stack.popup("D", Rect(0f, 0f, 10f, 10f))
        stack.overlay("E")
        assertEquals(listOf("A", "B", "C", "D", "E"), stack.items.map { it.item })
        assertTrue(stack.items[0] is Presented.Dialog)
        assertTrue(stack.items[1] is Presented.BottomSheet)
        assertTrue(stack.items[2] is Presented.Screen)
        assertTrue(stack.items[3] is Presented.Popup)
        assertTrue(stack.items[4] is Presented.Overlay)
    }
}

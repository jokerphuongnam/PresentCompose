package present.compose

import androidx.compose.ui.geometry.Rect
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class PresentStackBoundsTest {
    @Test
    fun dismissOnAnEmptyStackStaysEmpty() {
        val stack = PresentStack<String>()
        stack.dismiss()
        assertTrue(stack.items.isEmpty())
    }

    @Test
    fun dismissFromIgnoresAnIndexOutsideTheStack() {
        val stack = PresentStack<String>()
        stack.dialog("A")
        stack.dismissFrom(4)
        stack.dismissFrom(-1)
        assertEquals(listOf("A"), stack.items.map { it.item })
    }

    @Test
    fun dialogAndPopupKeepTheBackgroundFlagAndAnchor() {
        val anchor = Rect(4f, 8f, 12f, 16f)
        val stack = PresentStack<String>()
        stack.dialog("A", dismissOnBackground = false)
        stack.popup("B", anchor, dismissOnBackground = false)
        stack.overlay("C", dismissOnBackground = false)

        val dialog = stack.items[0] as Presented.Dialog
        val popup = stack.items[1] as Presented.Popup
        val overlay = stack.items[2] as Presented.Overlay
        assertEquals(false, dialog.dismissOnBackground)
        assertEquals(anchor, popup.anchor)
        assertEquals(false, popup.dismissOnBackground)
        assertEquals(false, overlay.dismissOnBackground)
    }
}

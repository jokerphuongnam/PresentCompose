package present.compose

import org.junit.Assert.assertEquals
import org.junit.Test

class PresentStackDismissTest {
    @Test
    fun dismissRemovesOnlyTheTop() {
        val stack = PresentStack<String>()
        stack.bottomSheet("A")
        stack.dialog("B")
        stack.dismiss()
        assertEquals(listOf("A"), stack.items.map { it.item })
    }

    @Test
    fun dismissFromDropsThatLayerAndEverythingAbove() {
        val stack = PresentStack<String>()
        stack.dialog("A")
        stack.screen("B")
        stack.overlay("C")
        stack.dismissFrom(1)
        assertEquals(listOf("A"), stack.items.map { it.item })
    }
}

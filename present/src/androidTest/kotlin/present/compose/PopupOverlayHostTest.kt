package present.compose

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class PopupOverlayHostTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun popupShowsThePresentedItem() {
        val stack = PresentStack<String>()
        stack.popup("Popup C", Rect(0f, 0f, 10f, 10f), dismissOnBackground = false)
        rule.setContent {
            PresentHost(
                stack = stack,
                content = { Text("Home") },
                presented = { item -> Text(item) },
            )
        }
        rule.onNodeWithText("Popup C").assertIsDisplayed()
        rule.onNodeWithText("Home").assertIsDisplayed()
    }

    @Test
    fun overlayShowsThePresentedItemAndDismissClosesIt() {
        val stack = PresentStack<String>()
        stack.overlay("Overlay C", dismissOnBackground = false)
        rule.setContent {
            PresentHost(
                stack = stack,
                content = { Text("Home") },
                presented = { item ->
                    Text(item)
                    Button(onClick = { stack.dismiss() }) { Text("Close") }
                },
            )
        }
        rule.onNodeWithText("Overlay C").assertIsDisplayed()
        rule.onNodeWithText("Close").performClick()
        rule.onNodeWithText("Overlay C").assertDoesNotExist()
        rule.onNodeWithText("Home").assertIsDisplayed()
    }

    @Test
    fun aPresentedLayerCanOpenAnotherLayer() {
        val stack = PresentStack<String>()
        stack.dialog("First")
        rule.setContent {
            PresentHost(
                stack = stack,
                content = { Text("Home") },
                presented = { item ->
                    Text(item)
                    Button(onClick = { stack.overlay("Second", dismissOnBackground = false) }) {
                        Text("Open next")
                    }
                },
            )
        }
        rule.onNodeWithText("First").assertIsDisplayed()
        rule.onNodeWithText("Open next").performClick()
        rule.onNodeWithText("Second").assertIsDisplayed()
    }
}

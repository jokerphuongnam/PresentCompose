package present.compose

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class BottomSheetHostTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun bottomSheetShowsThePresentedItemAndDismissClosesIt() {
        rule.setContent {
            val stack = remember { PresentStack<String>() }
            PresentHost(
                stack = stack,
                content = {
                    Button(onClick = { stack.bottomSheet("Sheet A") }) { Text("Open sheet") }
                },
                presented = { item ->
                    Text(item)
                    Button(onClick = { stack.dismiss() }) { Text("Close") }
                },
            )
        }

        rule.onNodeWithText("Open sheet").performClick()
        rule.onNodeWithText("Sheet A").assertIsDisplayed()
        rule.onNodeWithText("Close").performClick()
        rule.onNodeWithText("Sheet A").assertDoesNotExist()
    }
}

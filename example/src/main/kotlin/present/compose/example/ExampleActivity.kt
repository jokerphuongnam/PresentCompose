package present.compose.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.remember
import present.compose.PresentHost
import present.compose.PresentStack

class ExampleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val stack = remember { PresentStack<Screen>() }
                PresentHost(
                    stack = stack,
                    content = { Home() },
                    presented = { screen ->
                        when (screen) {
                            Screen.A -> PresentedPage(
                                title = "Screen A",
                                detail = "Opened from Home. This page reads the same stack and can present Screen B.",
                                next = Screen.B,
                            )
                            Screen.B -> PresentedPage(
                                title = "Screen B",
                                detail = "Opened above the previous layer. Dismiss removes only this layer.",
                                next = Screen.C,
                            )
                            Screen.C -> PresentedPage(
                                title = "Screen C",
                                detail = "Opened as a popup anchored to the Home button, or as an overlay with a dim scrim.",
                                next = null,
                            )
                        }
                    },
                )
            }
        }
    }
}

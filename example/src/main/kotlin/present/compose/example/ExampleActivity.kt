package present.compose.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
                            Screen.A -> PresentedPage("Screen A", Screen.B)
                            Screen.B -> PresentedPage("Screen B", Screen.C)
                            Screen.C -> Text("Screen C", modifier = Modifier.padding(24.dp))
                        }
                    },
                )
            }
        }
    }
}

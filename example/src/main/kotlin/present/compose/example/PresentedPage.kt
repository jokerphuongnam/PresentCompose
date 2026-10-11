package present.compose.example

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import present.compose.presentStack

@Composable
internal fun PresentedPage(title: String, next: Screen) {
    val stack = presentStack<Screen>()
    Column(Modifier.padding(24.dp)) {
        Text(title)
        Button(onClick = { stack.dialog(next) }) { Text("Dialog $next") }
        Button(onClick = { stack.screen(next) }) { Text("Screen $next") }
        Button(onClick = { stack.dismiss() }) { Text("Dismiss") }
    }
}

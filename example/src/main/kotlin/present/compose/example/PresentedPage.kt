package present.compose.example

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import present.compose.presentStack

@Composable
internal fun PresentedPage(title: String, detail: String, next: Screen?) {
    val stack = presentStack<Screen>()
    Column(Modifier.padding(24.dp)) {
        Text(title)
        Text(detail, modifier = Modifier.padding(bottom = 12.dp))
        if (next != null) {
            Button(
                onClick = { stack.dialog(next) },
                modifier = Modifier.fillMaxWidth(),
            ) { Text("Open dialog $next") }
            Button(
                onClick = { stack.bottomSheet(next) },
                modifier = Modifier.fillMaxWidth(),
            ) { Text("Open bottom sheet $next") }
            Button(
                onClick = { stack.screen(next) },
                modifier = Modifier.fillMaxWidth(),
            ) { Text("Open screen $next") }
            Button(
                onClick = { stack.overlay(next) },
                modifier = Modifier.fillMaxWidth(),
            ) { Text("Open overlay $next") }
        }
        Button(onClick = { stack.dismiss() }, modifier = Modifier.fillMaxWidth()) {
            Text("Dismiss this layer")
        }
    }
}

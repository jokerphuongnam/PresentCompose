package present.compose.example

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.unit.dp
import present.compose.presentStack

@Composable
internal fun Home() {
    val stack = presentStack<Screen>()
    Column(Modifier.padding(20.dp)) {
        Text("Home")
        Button(onClick = { stack.dialog(Screen.A) }) { Text("Dialog A") }
        Button(onClick = { stack.bottomSheet(Screen.A) }) { Text("Bottom sheet A") }
        Button(onClick = { stack.screen(Screen.B) }) { Text("Screen B") }
        Button(onClick = { stack.overlay(Screen.C) }) { Text("Overlay C") }
        Button(onClick = { stack.popup(Screen.C, Rect(40f, 200f, 80f, 240f)) }) {
            Text("Popup C")
        }
    }
}

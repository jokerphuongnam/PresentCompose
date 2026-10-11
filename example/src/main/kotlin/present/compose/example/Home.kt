package present.compose.example

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import present.compose.presentStack

@Composable
internal fun Home() {
    val stack = presentStack<Screen>()
    var popupAnchor by remember { mutableStateOf(Rect.Zero) }
    Column(Modifier.padding(20.dp)) {
        Text("One stack for this flow")
        Text(
            "Every button appends to the same PresentStack. A presented page can open the next layer.",
            modifier = Modifier.padding(bottom = 12.dp),
        )
        OpenButton("Dialog", "Centered dialog. Tap outside to close.") {
            stack.dialog(Screen.A)
        }
        OpenButton("Bottom sheet", "Material modal bottom sheet.") {
            stack.bottomSheet(Screen.A)
        }
        OpenButton("Screen", "A new screen covering this one.") {
            stack.screen(Screen.B)
        }
        OpenButton("Overlay", "Content over a dim scrim. Tap the scrim to close.") {
            stack.overlay(Screen.C)
        }
        Button(
            onClick = { stack.popup(Screen.C, popupAnchor) },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { popupAnchor = it.boundsInRoot() },
        ) {
            Text("Popup")
        }
        Text(
            "Popup is anchored to this button, like a PopupWindow.",
            modifier = Modifier.padding(bottom = 8.dp),
        )
    }
}

@Composable
private fun OpenButton(title: String, detail: String, onClick: () -> Unit) {
    Button(onClick = onClick, modifier = Modifier.fillMaxWidth()) {
        Text(title)
    }
    Text(detail, modifier = Modifier.padding(bottom = 8.dp))
}

package present.compose

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.zIndex
import kotlin.math.roundToInt

@Composable
internal fun PopupPresentation(
    entry: Presented.Popup<*>,
    onDismiss: () -> Unit,
    content: @Composable () -> Unit,
) {
    Box(Modifier.fillMaxSize()) {
        if (entry.dismissOnBackground) {
            Box(
                Modifier
                    .fillMaxSize()
                    .pointerInput(onDismiss) { detectTapGestures { onDismiss() } },
            )
        }
        Box(
            Modifier
                .offset {
                    IntOffset(
                        entry.anchor.left.roundToInt(),
                        entry.anchor.bottom.roundToInt(),
                    )
                }
                .wrapContentSize()
                .zIndex(1f),
        ) { content() }
    }
}

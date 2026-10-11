package present.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
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
                    .clickable(onClick = onDismiss),
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
                .wrapContentSize(),
        ) { content() }
    }
}

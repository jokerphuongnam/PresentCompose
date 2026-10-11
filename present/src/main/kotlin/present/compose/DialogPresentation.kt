package present.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog

@Composable
internal fun DialogPresentation(
    entry: Presented.Dialog<*>,
    onDismiss: () -> Unit,
    content: @Composable () -> Unit,
) {
    Dialog(
        onDismissRequest = {
            if (entry.dismissOnBackground) onDismiss()
        },
        content = content,
    )
}

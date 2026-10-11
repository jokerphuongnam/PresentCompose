package present.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier

val LocalPresentStack = staticCompositionLocalOf<PresentStack<*>?> { null }

/**
 * Draws [content] and the presentation stack above it.
 * Any child reads [LocalPresentStack] and opens a dialog, bottom sheet, screen, popup, or overlay.
 */
@Composable
fun <T> PresentHost(
    stack: PresentStack<T>,
    content: @Composable () -> Unit,
    presented: @Composable (T) -> Unit,
) {
    CompositionLocalProvider(LocalPresentStack provides stack) {
        Box(Modifier.fillMaxSize()) {
            content()
            if (stack.items.isNotEmpty()) {
                PresentLayer(stack, 0, presented)
            }
        }
    }
}

@Composable
@Suppress("UNCHECKED_CAST")
fun <T> presentStack(): PresentStack<T> {
    return LocalPresentStack.current as? PresentStack<T>
        ?: error("No PresentHost above this composable.")
}

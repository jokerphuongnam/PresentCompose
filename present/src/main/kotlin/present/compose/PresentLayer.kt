package present.compose

import androidx.compose.runtime.Composable

@Composable
internal fun <T> PresentLayer(
    stack: PresentStack<T>,
    index: Int,
    presented: @Composable (T) -> Unit,
) {
    val entry = stack.items.getOrNull(index) ?: return
    val above: @Composable () -> Unit = {
        presented(entry.item)
        if (index + 1 < stack.items.size) {
            PresentLayer(stack, index + 1, presented)
        }
    }
    when (entry) {
        is Presented.Dialog -> DialogPresentation(entry, onDismiss = { stack.dismissFrom(index) }, content = above)
        is Presented.BottomSheet -> BottomSheetPresentation(onDismiss = { stack.dismissFrom(index) }, content = above)
        is Presented.Screen -> ScreenPresentation(onDismiss = { stack.dismissFrom(index) }, content = above)
        is Presented.Popup -> PopupPresentation(entry, onDismiss = { stack.dismissFrom(index) }, content = above)
        is Presented.Overlay -> OverlayPresentation(entry, onDismiss = { stack.dismissFrom(index) }, content = above)
    }
}

package present.compose

import androidx.compose.ui.geometry.Rect

/**
 * One entry on the coordinator stack. The item is the screen. The variant is how Android shows it.
 */
sealed class Presented<out T> {
    abstract val item: T

    /** Centered [android.app.Dialog]. */
    data class Dialog<T>(
        override val item: T,
        val dismissOnBackground: Boolean = true,
    ) : Presented<T>()

    /** Material modal bottom sheet. */
    data class BottomSheet<T>(override val item: T) : Presented<T>()

    /** A new screen covering the current one. */
    data class Screen<T>(override val item: T) : Presented<T>()

    /** A popup anchored to a rect, the same role as PopupWindow. */
    data class Popup<T>(
        override val item: T,
        val anchor: Rect,
        val dismissOnBackground: Boolean = true,
    ) : Presented<T>()

    data class Overlay<T>(
        override val item: T,
        val dismissOnBackground: Boolean = true,
    ) : Presented<T>()
}

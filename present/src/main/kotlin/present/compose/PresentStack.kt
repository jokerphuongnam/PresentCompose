package present.compose

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.geometry.Rect

/**
 * The coordinator. Every screen in the flow appends to this list. [PresentHost] draws it.
 */
class PresentStack<T> {
    val items: SnapshotStateList<Presented<T>> = mutableStateListOf()

    fun dialog(item: T, dismissOnBackground: Boolean = true) {
        items.add(Presented.Dialog(item, dismissOnBackground))
    }

    fun bottomSheet(item: T) {
        items.add(Presented.BottomSheet(item))
    }

    fun screen(item: T) {
        items.add(Presented.Screen(item))
    }

    fun popup(item: T, anchor: Rect, dismissOnBackground: Boolean = true) {
        items.add(Presented.Popup(item, anchor, dismissOnBackground))
    }

    fun overlay(item: T, dismissOnBackground: Boolean = true) {
        items.add(Presented.Overlay(item, dismissOnBackground))
    }

    /** Removes the top presentation. Same word as Dialog.dismiss. */
    fun dismiss() {
        if (items.isNotEmpty()) items.removeAt(items.lastIndex)
    }

    /** Removes this layer and every presentation above it. */
    fun dismissFrom(index: Int) {
        if (index < 0 || index >= items.size) return
        while (items.size > index) {
            items.removeAt(items.lastIndex)
        }
    }
}

# PresentCompose

PresentCompose is the Android coordinator for one flow. Many screens share one `PresentStack`. `PresentHost` is the only place that draws that stack.

Use it when a screen needs to open a dialog, a bottom sheet, another screen, a popup, or an overlay without each caller owning its own `Dialog` or `ModalBottomSheet`. It does not replace Navigation Compose, and it is not ComposeAtoms.

Current release: `1.0.0`.

## Install

```kotlin
dependencies {
    implementation("present.compose:present:1.0.0")
}
```

The artifact is `present.compose:present:1.0.0`. `minSdk` is 26. Compose runtime, foundation, UI, and Material 3 come in through the library.

This repository publishes to Maven Local, not Maven Central:

```bash
./gradlew :present:publishToMavenLocal
```

Add `mavenLocal()` to the app repositories, then use the dependency above. The example module is `:example`.

## One stack

Create the stack once, next to `PresentHost`.

```kotlin
val stack = remember { PresentStack<Screen>() }

PresentHost(
    stack = stack,
    content = { Home() },
    presented = { screen ->
        when (screen) {
            Screen.A -> ScreenA()
            Screen.B -> ScreenB()
        }
    },
)
```

`content` is the screen under the stack. `presented` builds the UI for whatever item was pushed. A child anywhere under `PresentHost` reads the same stack:

```kotlin
val stack = presentStack<Screen>()
```

If that call runs outside `PresentHost`, it throws `No PresentHost above this composable.`

Every open call appends. The next entry draws inside the previous one, so a dialog can open a screen, and that screen can open a bottom sheet. Two flows need two stacks and two hosts.

## What each call opens

`dialog` opens a centered Android dialog. The content keeps the dialog's own width. Set `dismissOnBackground` to `false` when a tap outside must leave it open. The default is `true`.

```kotlin
stack.dialog(Screen.A)
```

`bottomSheet` opens a Material modal bottom sheet. Dragging it down, or tapping the scrim, removes that layer.

```kotlin
stack.bottomSheet(Screen.A)
```

`screen` opens a new screen that covers the current one. The back action on that window removes that layer.

```kotlin
stack.screen(Screen.B)
```

`popup` opens content anchored to a `Rect`, in the same role as `PopupWindow`. `anchor.left` and `anchor.bottom` are pixel offsets. A tap outside removes it unless `dismissOnBackground` is `false`.

```kotlin
stack.popup(Screen.C, Rect(40f, 200f, 80f, 240f))
```

`overlay` draws over the current content with a dim scrim. The content is not forced into a dialog window. A tap on the scrim removes it unless `dismissOnBackground` is `false`.

```kotlin
stack.overlay(Screen.C)
```

## Dismiss

`dismiss()` removes only the top entry. `dismissFrom(index)` removes that entry and every entry above it. Index `0` is the first presentation on the stack.

```kotlin
stack.dismiss()
stack.dismissFrom(0)
```

The stack does not hold the composable. When the last entry is gone, only `content` remains.

## Library files

| File | Role |
|---|---|
| `Presented.kt` | The entry type: dialog, bottom sheet, screen, popup, overlay |
| `PresentStack.kt` | Append and dismiss |
| `PresentHost.kt` | Host and `presentStack()` |
| `PresentLayer.kt` | Walks the stack and picks a presentation |
| `DialogPresentation.kt` | Centered dialog |
| `BottomSheetPresentation.kt` | Modal bottom sheet |
| `ScreenPresentation.kt` | Full-screen cover |
| `PopupPresentation.kt` | Anchored popup |
| `OverlayPresentation.kt` | Dimmed overlay |

## Versions

| Version | What it is |
|---|---|
| 1.0.0 | First release. One stack for dialog, bottom sheet, screen, popup, and overlay |

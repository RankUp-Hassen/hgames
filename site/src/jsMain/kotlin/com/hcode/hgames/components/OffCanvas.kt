package com.hcode.hgames.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.attrsModifier
import com.varabyte.kobweb.compose.ui.modifiers.classNames
import com.varabyte.kobweb.compose.ui.modifiers.id
import com.varabyte.kobweb.compose.ui.thenIf
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.attributes.disabled
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H5

enum class OffcanvasPlacement(val value: String) {
    TOP(value = "offcanvas-top"),
    BOTTOM(value = "offcanvas-bottom"),
    START(value = "offcanvas-start"),
    END(value = "offcanvas-end")
}
@Composable
fun BSOffcanvas(
    modifier: Modifier = Modifier,
    id: String,
    title: String,
    body: @Composable () -> Unit,
    dark: Boolean = false,
    allowScrolling: Boolean = false,
    disableBackdrop: Boolean = false,
    closableOutside: Boolean = true,
    placement: OffcanvasPlacement = OffcanvasPlacement.END
) {
    Div(
        attrs = modifier
            .id(id)
            .classNames("offcanvas",placement.value)
            .toAttrs {
                attr("tabindex", "-1")
                attr("aria-labelledby", "offcanvasLabel")
                attr("aria-controls", "#$id")
                if (allowScrolling) attr("data-bs-scroll", "true")
                if (disableBackdrop) attr("data-bs-backdrop", "false")
                if (!closableOutside) attr("data-bs-backdrop", "static")
                if (dark) attr("data-bs-theme", "dark")
            }
    ) {
        Div(
            attrs = Modifier
                .classNames("offcanvas-header")
                .toAttrs()
        ) {
            H5(
                attrs = Modifier
                    .id("offcanvasLabel")
                    .classNames("offcanvas-title")
                    .toAttrs()
            ) {
                SpanText(text = title)
            }
            BSCloseButton(modifier = Modifier.hideOffcanvasOnClick())
        }
        Div(
            attrs = Modifier
                .classNames("offcanvas-body")
                .toAttrs()
        ) {
            body()
        }
    }
}


fun Modifier.showOffcanvasOnClick(id: String): Modifier = attrsModifier {
    attr("data-bs-toggle", "offcanvas")
    attr("data-bs-target", "#$id")
}


fun Modifier.hideOffcanvasOnClick(): Modifier = attrsModifier {
    attr("data-bs-dismiss", "offcanvas")
}

@Composable
fun BSCloseButton(
    modifier: Modifier = Modifier,
    disabled: Boolean = false,
    dark: Boolean = true
) {
    Button(attrs = modifier
        .classNames("btn-close")
        .thenIf(
            condition = !dark,
            other = Modifier.classNames("btn-close-white")
        )
        .toAttrs {
            attr("type", "button")
            attr("aria-label", "Close")
            if (disabled) disabled()
        }
    )
}



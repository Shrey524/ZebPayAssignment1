package com.example.zebpayassignment.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp


class CrazyListBuilder(
    private val content: MutableList<CrazyListItem> = mutableListOf()
) {
    // code to add these items to the content list declared above
    fun space(height: Dp) = apply { content.add(CrazyListItem.Space(height)) }
    fun header(text: String) = apply { content.add(CrazyListItem.Header(text)) }
    fun text(text: String) = apply { content.add(CrazyListItem.TextItem(text)) }
    fun image(painter: Painter) = apply { content.add(CrazyListItem.ImageItem(painter)) }
    fun button(text: String, onClick: () -> Unit) = apply { content.add(CrazyListItem.ButtonItem(text, onClick)) }
    fun textField(id: String, hint: String, onValChanged: (String) -> Unit) = apply { content.add(CrazyListItem.TextFieldItem(id, hint, onValChanged)) }

    // composable function to render Crazy List
    @Composable
    fun Render(padding: Dp) {
        CrazyListRenderer.Render(content = content, padding = padding)
    }
}
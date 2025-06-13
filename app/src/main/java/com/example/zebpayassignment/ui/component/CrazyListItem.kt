package com.example.zebpayassignment.ui.component

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp

sealed class CrazyListItem {
    data class Space(val height: Dp) : CrazyListItem()
    data class Header(val text: String) : CrazyListItem()
    data class TextItem(val text: String) : CrazyListItem()
    data class ImageItem(val painter: Painter) : CrazyListItem()
    data class ButtonItem(val text: String, val onClick: () -> Unit) : CrazyListItem()
    data class TextFieldItem(val id: String, val hint: String, val onValChanged: (String) -> Unit) : CrazyListItem()}
package com.example.zebpayassignment.ui.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items


object CrazyListRenderer {

    @Composable
    fun Render(content: List<CrazyListItem>, padding: Dp = 16.dp) {
        val textFieldValues = remember { mutableStateMapOf<String, String>() }
        val focusManager = LocalFocusManager.current

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(content) { item ->
                RenderItem(item, textFieldValues, focusManager)
            }
        }
    }
}

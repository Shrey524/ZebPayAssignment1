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

// Singleton class because we don't need multiple instances of the renderer all it does is display items
object CrazyListRenderer {

    // Composable function to create a LazyColum from the list provided
    @Composable
    fun Render(content: List<CrazyListItem>, padding: Dp = 16.dp) {
        // textFieldValues here is being used to store the input value in key value pairs
        val textFieldValues = remember { mutableStateMapOf<String, String>() }
        // focus manager to close the keyboard when user is done with the input
        val focusManager = LocalFocusManager.current

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            // rendering each item in the list using the RenderItem function
            items(content) { item ->
                RenderItem(item, textFieldValues, focusManager)
            }
        }
    }
}

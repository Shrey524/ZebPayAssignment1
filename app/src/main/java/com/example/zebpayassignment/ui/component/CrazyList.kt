package com.example.zebpayassignment.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val CrazyListChain get() = CrazyList()


class CrazyList(
    private val content : List<CrazyListItem> = emptyList()
) {
    // SpacerView declared
    fun space(height: Dp): CrazyList =
        CrazyList(content + CrazyListItem.Space(height))

    // HeaderView declared
    fun header(text: String): CrazyList =
        CrazyList(content + CrazyListItem.Header(text))

    // textView declared
    fun text(text: String): CrazyList =
        CrazyList(content + CrazyListItem.TextItem(text))

    // ImageView declared
    fun image(painter: Painter): CrazyList =
        CrazyList(content + CrazyListItem.ImageItem(painter))

    // ButtonView declared
    fun button(text: String, onClick: () -> Unit): CrazyList =
        CrazyList(content + CrazyListItem.ButtonItem(text, onClick))

    // textFieldView declared
    fun textField(hint: String, onValueChange: (String) -> Unit): CrazyList =
        CrazyList(content + CrazyListItem.TextFieldItem(hint = hint, onValChanged = onValueChange))

    // composable function to build the list after all the elements are added
    @Composable
    fun Render(itemPadding : Dp) {

        // creating a textFieldValue val with key value DS to hold data for multiple Text input views
        val textFieldValues = remember { mutableStateMapOf<String, String>() }

        // putting everything inside a column
        Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {

            // providing views for each item
            content.forEach { item ->
                when (item) {

                    // Spacer implementation simple only need to take in Height
                    is CrazyListItem.Space -> Spacer(modifier = Modifier.height(item.height))

                    // Header implementation currently only taking in Text but can take in fontsize, weight etc can create a modifier for our Header item)
                    is CrazyListItem.Header -> Text(
                        item.text,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = itemPadding)
                    )

                    // TextView implementation currently only taking in Text but can take in fontsize, weight etc can create a modifier for our Text item)
                    is CrazyListItem.TextItem -> Text(
                        text = item.text,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(vertical = itemPadding)
                    )

                    // ImageItem implementation currently only taking in the image resource but here again we can take in height, width etc can create a modifier for our IMage item)
                    is CrazyListItem.ImageItem -> Image(
                        painter = item.painter,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .padding(vertical = itemPadding),
                        contentScale = ContentScale.Crop
                    )

                    // ButtonItem implementation
                    is CrazyListItem.ButtonItem -> Button(
                        onClick = item.onClick,
                        modifier = Modifier.padding(vertical = itemPadding)
                    ) {
                        Text(item.text)
                    }

                    // TextFieldItem implementation was facing an issue where the keyboard wasn't closing on clicking enter so had to clear focus and activate done action)
                    is CrazyListItem.TextFieldItem -> {
                        val value = textFieldValues[item.id] ?: ""
                        val focusManager = LocalFocusManager.current

                        OutlinedTextField(
                            value = value,
                            onValueChange = {
                                textFieldValues[item.id] = it
                                item.onValChanged(it)
                            },
                            label = { Text(item.hint) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = itemPadding),
                            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    focusManager.clearFocus()
                                }
                            )
                        )
                    }
                }
            }
        }
    }
}

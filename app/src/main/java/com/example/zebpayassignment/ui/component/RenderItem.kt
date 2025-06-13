package com.example.zebpayassignment.ui.component
import androidx.compose.foundation.Image
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.widget.Toast

@Composable
fun RenderItem(
    item: CrazyListItem,
    textFieldValues: MutableMap<String, String>,
    focusManager: FocusManager
) {
    val context = LocalContext.current

    when (item) {
        is CrazyListItem.Space -> Spacer(modifier = Modifier.height(item.height))

        is CrazyListItem.Header -> Text(
            text = item.text,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        is CrazyListItem.TextItem -> Text(
            text = item.text,
            fontSize = 16.sp,
            modifier = Modifier.padding(4.dp)
        )

        is CrazyListItem.ImageItem -> Image(
            painter = item.painter,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .padding(vertical = 8.dp),
            contentScale = ContentScale.Crop
        )

        is CrazyListItem.ButtonItem -> Button(
            onClick = {
                item.onClick()
                Toast.makeText(context, "Button clicked", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text(item.text)
        }

        is CrazyListItem.TextFieldItem -> {
            val value = textFieldValues[item.hint] ?: ""
            OutlinedTextField(
                value = value,
                onValueChange = {
                    textFieldValues[item.hint] = it
                    item.onValChanged(it)
                    Toast.makeText(context, "Typed: $it", Toast.LENGTH_SHORT).show()
                },
                label = { Text(item.hint) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    focusManager.clearFocus()
                })
            )
        }
    }
}
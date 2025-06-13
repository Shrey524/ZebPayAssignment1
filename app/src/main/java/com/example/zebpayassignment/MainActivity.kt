package com.example.zebpayassignment

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.zebpayassignment.ui.component.CrazyListBuilder
import com.example.zebpayassignment.ui.theme.ZebPayAssignmentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ZebPayAssignmentTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Calling Crazy List
                    MyCrazyListDemo(innerPadding)
                }
            }
        }
    }
}

// Composable to render the crazy list
@Composable
fun MyCrazyListDemo(innerPadding: PaddingValues) {
    val painter = painterResource(id = R.drawable.ic_launcher_background)
    val context = LocalContext.current

    Column(modifier = Modifier.padding(innerPadding)) {
        CrazyListBuilder()
            .space(16.dp)
            .header("CrazyList Demo")
            .text("CrazyList TextView.")
            .image(painter)
            .button("Click Here") {
                Toast.makeText(context, "Button clicked", Toast.LENGTH_SHORT).show()
            }
            .image(painter)
            .textField(id = "username", hint = "Enter Username"){
                Toast.makeText(context, "User Typed: $it", Toast.LENGTH_SHORT).show()
            }
            .image(painter)
            .Render(16.dp)
    }
}
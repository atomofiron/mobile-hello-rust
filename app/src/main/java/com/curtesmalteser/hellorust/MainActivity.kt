package com.curtesmalteser.hellorust

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.curtesmalteser.hellorust.ui.theme.HelloRustTheme

class MainActivity : ComponentActivity() {

    private external fun add(left: Long, right: Long): Long
    private external fun subtract(left: Long, right: Long): Long

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sumFromRust = add(left = 2, right = 3)
        val subtractFromRust = subtract(left = 2, right = 3)
        enableEdgeToEdge()
        setContent {
            HelloRustTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column {
                        Greeting(
                            name = "Android sum from Rust: $sumFromRust",
                            modifier = Modifier.padding(innerPadding)
                        )
                        Greeting(
                            name = "Android subtract from Rust: $subtractFromRust",
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HelloRustTheme {
        Greeting("Android")
    }
}
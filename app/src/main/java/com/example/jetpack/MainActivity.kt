package com.example.jetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpack.ui.theme.JetpackTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      JetpackTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
          PetProfileScreen(
            modifier = Modifier.padding(innerPadding)
          )
        }
      }
    }
  }
}

@Composable
fun PetProfileScreen(modifier: Modifier = Modifier) {
  PetProfileContent(modifier = modifier)
}

@Preview(showBackground = true)
@Composable
fun PetProfilePreview() {
  JetpackTheme {
    PetProfileScreen()
  }
}
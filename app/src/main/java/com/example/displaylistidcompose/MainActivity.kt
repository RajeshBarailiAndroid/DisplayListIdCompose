package com.example.displaylistidcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.displaylistidcompose.navigation.NavigationScreen
import com.example.displaylistidcompose.ui.theme.DisplayListIdComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DisplayListIdComposeTheme {
          NavigationScreen()
            }
        }
    }
}




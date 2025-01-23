package com.example.displaylistidcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.displaylistidcompose.ui.compose.MainPageView
import kotlinx.serialization.Serializable


@Composable
fun NavigationScreen() {
    // navigation
    val navController = rememberNavController()
    NavHost(
            navController = navController,
            startDestination = Home
        ) {
            composable<Home> {
                MainPageView(navController)
            }

        }
    }
@Serializable
object Home

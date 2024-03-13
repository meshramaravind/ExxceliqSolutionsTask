package com.arvind.exxceliqsolutiionstask.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.arvind.exxceliqsolutiionstask.presentation.home.HomeScreen
import com.arvind.exxceliqsolutiionstask.utils.WindowSize

@Composable
fun Navigation(windowSize: WindowSize) {
    val navigationControl = rememberNavController()

    NavHost(
        navController = navigationControl,
        startDestination = Screens.HomeScreen.route
    ) {
        composable(Screens.HomeScreen.route) {
            HomeScreen(windowSize = windowSize)
        }
    }
}
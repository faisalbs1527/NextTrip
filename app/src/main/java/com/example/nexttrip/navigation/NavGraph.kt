package com.example.nexttrip.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.nexttrip.presentation.booking.BookingScreen
import com.example.nexttrip.presentation.booking.SearchScreen
import com.example.nexttrip.presentation.destination.PopularDestinationScreen
import com.example.nexttrip.presentation.home.HomeScreen

@Composable
fun SetUpNavGraph(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController)
        }
        composable(route = Screen.PopularDestination.route) {
            PopularDestinationScreen(navController)
        }
        composable(route = Screen.BookingScreen.route ){
            BookingScreen(navController)
        }
        composable(
            route = Screen.SearchScreen.route,
            arguments = listOf(
                navArgument("title") { defaultValue = "" },
                navArgument("focusTarget") { defaultValue = 1 }
            )
        ) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: ""
            val focusTarget = backStackEntry.arguments?.getInt("focusTarget") ?: 1
            SearchScreen(navController= navController,title = title, focusTarget = focusTarget)
        }
    }
}
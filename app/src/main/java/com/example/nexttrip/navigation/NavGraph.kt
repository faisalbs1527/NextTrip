package com.example.nexttrip.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.nexttrip.presentation.booking.BookingScreen
import com.example.nexttrip.presentation.booking.ResultsScreen
import com.example.nexttrip.presentation.booking.SearchScreen
import com.example.nexttrip.presentation.destination.PopularDestinationScreen
import com.example.nexttrip.presentation.home.HomeScreen
import com.example.nexttrip.presentation.model.AirportsData
import com.google.gson.Gson

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
        composable(route = Screen.BookingScreen.route) {
            BookingScreen(navController)
        }
        composable(
            route = Screen.SearchScreen.route,
            arguments = listOf(
                navArgument("title") { defaultValue = "" },
                navArgument("focusTarget") { defaultValue = 1 },
                navArgument(name = "from") { defaultValue = "" },
                navArgument(name = "to") { defaultValue = "" }
            )
        ) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: ""
            val focusTarget = backStackEntry.arguments?.getInt("focusTarget") ?: 1
            val fromJson = backStackEntry.arguments?.getString("from")
            val toJson = backStackEntry.arguments?.getString("to")

            val from = Gson().fromJson(fromJson, AirportsData::class.java)
            val to = Gson().fromJson(toJson, AirportsData::class.java)

            SearchScreen(
                navController = navController,
                title = title,
                focusTarget = focusTarget,
                from = from,
                to = to
            )
        }
        composable(route = Screen.ResultsScreen.route) {
            ResultsScreen(navController = navController)
        }
    }
}
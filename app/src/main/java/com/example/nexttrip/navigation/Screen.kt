package com.example.nexttrip.navigation

import com.example.nexttrip.presentation.model.AirportsData
import com.google.gson.Gson

sealed class Screen(val route: String) {
    data object MainScreen : Screen(route = "MainScreen")
    data object HomeScreen : Screen(route = "HomeScreen")
    data object PopularDestination : Screen(route = "PopularDestination")
    data object BookingScreen : Screen(route = "BookingScreen")
    data object SearchScreen : Screen(route = "SearchScreen/{title}/{focusTarget}/{from}/{to}") {
        fun createRoute(text: String, selected: Int, from: AirportsData, to: AirportsData): String {
            val fromJson = Gson().toJson(from)
            val toJson = Gson().toJson(to)
            return "SearchScreen/$text/$selected/$fromJson/$toJson"
        }
    }
    data object ResultsScreen : Screen(route = "ResultsScreen")
}
package com.example.nexttrip.navigation

sealed class Screen(val route: String) {
    data object MainScreen : Screen(route = "MainScreen")
    data object HomeScreen : Screen(route = "HomeScreen")
    data object PopularDestination : Screen(route = "PopularDestination")
    data object BookingScreen : Screen(route = "BookingScreen")
    data object SearchScreen : Screen(route = "SearchScreen/{title}/{focusTarget}") {
        fun createRoute(text: String, selected: Int) = "SearchScreen/$text/$selected"
    }
}
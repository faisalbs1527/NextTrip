package com.example.nexttrip.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.nexttrip.presentation.MainScreen
import com.example.nexttrip.presentation.destination.PopularDestinationScreen
import com.example.nexttrip.presentation.flightBooking.BookingScreen
import com.example.nexttrip.presentation.flightBooking.ResultsScreen
import com.example.nexttrip.presentation.flightBooking.SearchScreen
import com.example.nexttrip.presentation.flightBooking.SharedViewModel
import com.example.nexttrip.presentation.flightBooking.addingInfo.AddingInfoScreen
import com.example.nexttrip.presentation.flightBooking.confirmation.ConfirmationScreen
import com.example.nexttrip.presentation.home.HomeScreen
import com.example.nexttrip.presentation.hotelBooking.ReservationScreen
import com.example.nexttrip.presentation.model.AirportsData
import com.example.nexttrip.presentation.model.FlightBookingData
import com.example.nexttrip.presentation.model.FlightsData
import com.google.gson.Gson

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetUpNavGraph(
    navController: NavHostController,
    startDestination: String,
    sharedViewModel: SharedViewModel = hiltViewModel()
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = navController, selectedItem = 1)
        }
        composable(route = Screen.HomeScreen.route) {
            MainScreen(navController = navController, selectedItem = 1)
        }
        composable(route = Screen.PopularDestination.route) {
            PopularDestinationScreen(navController)
        }
        composable(route = Screen.BookingScreen.route) {
            MainScreen(navController = navController, selectedItem = 3)
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
        composable(
            route = Screen.ResultsScreen.route,
            arguments = listOf(
                navArgument("bookingData") { defaultValue = "" }
            )
        ) { backStackEntry ->
            val data = backStackEntry.arguments?.getString("bookingData") ?: ""
            val bookingInfo = Gson().fromJson(data, FlightBookingData::class.java)

            ResultsScreen(
                navController = navController,
                bookingData = bookingInfo,
                sharedViewModel = sharedViewModel
            )
        }

        composable(
            route = Screen.AddInfoScreen.route
        ) {
            AddingInfoScreen(navController = navController, sharedViewModel = sharedViewModel)
        }
        composable(
            route = Screen.ConfirmationScreen.route
        ) {
            ConfirmationScreen(navController = navController, sharedViewModel = sharedViewModel)
        }
        composable(route = Screen.ReservationScreen.route) {
            ReservationScreen(navController = navController)
        }

        composable(route = Screen.MyBookingScreen.route) {
            MainScreen(navController = navController, selectedItem = 2)
        }
    }

}
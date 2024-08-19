package com.example.nexttrip.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.nexttrip.presentation.destination.PopularDestinationScreen
import com.example.nexttrip.presentation.flightBooking.BookingScreen
import com.example.nexttrip.presentation.flightBooking.ResultsScreen
import com.example.nexttrip.presentation.flightBooking.SearchScreen
import com.example.nexttrip.presentation.flightBooking.addingInfo.AddingInfoScreen
import com.example.nexttrip.presentation.flightBooking.confirmation.ConfirmationScreen
import com.example.nexttrip.presentation.home.HomeScreen
import com.example.nexttrip.presentation.model.AirportsData
import com.example.nexttrip.presentation.model.FlightBookingData
import com.example.nexttrip.presentation.model.FlightsData
import com.google.gson.Gson

@RequiresApi(Build.VERSION_CODES.O)
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
        composable(
            route = Screen.ResultsScreen.route,
            arguments = listOf(
                navArgument("bookingData") { defaultValue = "" }
            )
        ) { backStackEntry ->
            val data = backStackEntry.arguments?.getString("bookingData") ?: ""
            val bookingInfo = Gson().fromJson(data, FlightBookingData::class.java)

            ResultsScreen(navController = navController, bookingData = bookingInfo)
        }

        composable(
            route = Screen.AddInfoScreen.route,
            arguments = listOf(
                navArgument("bookingData") { defaultValue = "" },
                navArgument("departureFlight") { defaultValue = "" },
                navArgument("returnFlight") { defaultValue = "" }
            )
        ) { backStackEntry ->
            val data = backStackEntry.arguments?.getString("bookingData") ?: ""
            val outgoingJson = backStackEntry.arguments?.getString("departureFlight") ?: ""
            val incomingJson = backStackEntry.arguments?.getString("returnFlight") ?: ""

            val bookingInfo = Gson().fromJson(data, FlightBookingData::class.java)
            val outgoingInfo = Gson().fromJson(outgoingJson, FlightsData::class.java)
            val incomingInfo = Gson().fromJson(incomingJson, FlightsData::class.java)

            AddingInfoScreen(
                navController = navController,
                bookingData = bookingInfo,
                departureFlight = outgoingInfo,
                returnFlight = incomingInfo
            )
        }
        composable(
            route = Screen.ConfirmationScreen.route
        ) {
            ConfirmationScreen(navController = navController)
        }
    }
}
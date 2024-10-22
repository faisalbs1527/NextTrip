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
import com.example.nexttrip.presentation.busTicketBooking.AvailableBusScreen
import com.example.nexttrip.presentation.busTicketBooking.BookingDetailsScreen
import com.example.nexttrip.presentation.busTicketBooking.BusReservationScreen
import com.example.nexttrip.presentation.busTicketBooking.BusReservationViewModel
import com.example.nexttrip.presentation.busTicketBooking.PaymentScreen
import com.example.nexttrip.presentation.busTicketBooking.SeatSelectionScreen
import com.example.nexttrip.presentation.carBooking.CarDetailsScreen
import com.example.nexttrip.presentation.carBooking.CarBookingScreen
import com.example.nexttrip.presentation.carBooking.CarBookingViewModel
import com.example.nexttrip.presentation.carBooking.RideConfirmationScreen
import com.example.nexttrip.presentation.carBooking.SelectLocationScreen
import com.example.nexttrip.presentation.destination.PopularDestinationScreen
import com.example.nexttrip.presentation.flightBooking.ResultsScreen
import com.example.nexttrip.presentation.flightBooking.SearchScreen
import com.example.nexttrip.presentation.flightBooking.SharedViewModel
import com.example.nexttrip.presentation.flightBooking.addingInfo.AddingInfoScreen
import com.example.nexttrip.presentation.flightBooking.confirmation.ConfirmationScreen
import com.example.nexttrip.presentation.hotelBooking.AvailableHotelScreen
import com.example.nexttrip.presentation.hotelBooking.AvailableRoomScreen
import com.example.nexttrip.presentation.hotelBooking.BookingConfirmationScreen
import com.example.nexttrip.presentation.hotelBooking.BookingSummaryScreen
import com.example.nexttrip.presentation.hotelBooking.HotelDetailsScreen
import com.example.nexttrip.presentation.hotelBooking.ReservationScreen
import com.example.nexttrip.presentation.hotelBooking.ReservationViewModel
import com.example.nexttrip.presentation.model.AirportsData
import com.example.nexttrip.presentation.model.FlightBookingData
import com.example.nexttrip.presentation.myBooking.MyBookingViewModel
import com.example.nexttrip.presentation.myBooking.ViewPdfScreen
import com.google.gson.Gson

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetUpNavGraph(
    navController: NavHostController,
    startDestination: String,
    sharedViewModel: SharedViewModel = hiltViewModel(),
    myBookingViewModel: MyBookingViewModel = hiltViewModel(),
    reservationViewModel: ReservationViewModel = hiltViewModel(),
    busReservationViewModel: BusReservationViewModel = hiltViewModel(),
    carBookingViewModel: CarBookingViewModel = hiltViewModel()
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(
                navController = navController,
                selectedItem = 1,
                myBookingViewModel = myBookingViewModel
            )
        }
        composable(route = Screen.HomeScreen.route) {
            MainScreen(
                navController = navController,
                selectedItem = 1,
                myBookingViewModel = myBookingViewModel
            )
        }
        composable(route = Screen.PopularDestination.route) {
            PopularDestinationScreen(navController)
        }
        composable(route = Screen.BookingScreen.route) {
            MainScreen(
                navController = navController,
                selectedItem = 3,
                myBookingViewModel = myBookingViewModel
            )
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
            ReservationScreen(navController = navController, viewModel = reservationViewModel)
        }

        composable(route = Screen.MyBookingScreen.route) {
            MainScreen(
                navController = navController,
                selectedItem = 2,
                myBookingViewModel = myBookingViewModel
            )
        }

        composable(route = Screen.PdfViewScreen.route) {
            ViewPdfScreen(navController = navController, viewModel = myBookingViewModel)
        }

        composable(route = Screen.AvailableHotelScreen.route) {
            AvailableHotelScreen(navController = navController, viewModel = reservationViewModel)
        }
        composable(route = Screen.HotelDetailsScreen.route) {
            HotelDetailsScreen(navController = navController, viewModel = reservationViewModel)
        }
        composable(route = Screen.AvailableRoomScreen.route) {
            AvailableRoomScreen(navController = navController, viewModel = reservationViewModel)
        }
        composable(route = Screen.BookingSummaryScreen.route) {
            BookingSummaryScreen(navController = navController, viewModel = reservationViewModel)
        }
        composable(route = Screen.HotelConfirmation.route) {
            BookingConfirmationScreen(
                navController = navController,
                viewModel = reservationViewModel
            )
        }
        composable(route = Screen.BusReservationScreen.route) {
            BusReservationScreen(navController = navController, viewModel = busReservationViewModel)
        }
        composable(route = Screen.AvailableBusScreen.route) {
            AvailableBusScreen(navController = navController, viewModel = busReservationViewModel)
        }
        composable(route = Screen.SeatSelectionScreen.route) {
            SeatSelectionScreen(navController = navController, viewModel = busReservationViewModel)
        }
        composable(route = Screen.BusBookingDetails.route) {
            BookingDetailsScreen(navController = navController, viewModel = busReservationViewModel)
        }
        composable(route = Screen.BusPaymentScreen.route) {
            PaymentScreen(navController = navController, viewModel = busReservationViewModel)
        }
        composable(route = Screen.CarBookingScreen.route) {
            CarBookingScreen(navController = navController, viewModel = carBookingViewModel)
        }
        composable(route = Screen.SelectLocationScreen.route) {
            SelectLocationScreen(navController = navController, viewModel = carBookingViewModel)
        }
        composable(route = Screen.CarDetailsScreen.route) {
            CarDetailsScreen(navController = navController, viewModel = carBookingViewModel)
        }
        composable(route = Screen.RideConfirmationScreen.route) {
            RideConfirmationScreen(navController = navController, viewModel = carBookingViewModel)
        }
    }

}
package com.example.nexttrip.navigation

import com.example.nexttrip.presentation.model.AirportsData
import com.example.nexttrip.presentation.model.FlightBookingData
import com.example.nexttrip.presentation.model.FlightsData
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

    data object ResultsScreen : Screen(route = "ResultsScreen/{bookingData}") {
        fun createRoute(data: FlightBookingData): String {
            val infoBooking = Gson().toJson(data)
            return "ResultsScreen/$infoBooking"
        }
    }

    data object AddInfoScreen : Screen(route = "AddInfoScreen")

    data object ConfirmationScreen : Screen(route = "ConfirmationScreen")

    data object ReservationScreen : Screen(route = "ReservationScreen")

    data object MyBookingScreen : Screen(route = "MyBookingScreen")

    data object PdfViewScreen : Screen(route = "PdfView")

    data object AvailableHotelScreen : Screen(route = "availableHotels")

    data object HotelDetailsScreen : Screen(route = "hotelDetails")

    data object AvailableRoomScreen : Screen(route = "availableRooms")
    data object BookingSummaryScreen : Screen(route = "bookingSummary")
    data object HotelConfirmation : Screen(route = "hotelConfirmation")
    data object BusReservationScreen: Screen(route = "busReservation")
    data object AvailableBusScreen: Screen(route = "availableBuses")
    data object SeatSelectionScreen: Screen(route = "seatSelectionBus")
    data object BusBookingDetails: Screen(route = "busBookingDetails")
    data object BusPaymentScreen: Screen(route = "busPayment")
    data object CarBookingScreen: Screen(route = "carBooking")
    data object SelectLocationScreen: Screen(route = "selectLocation")
    data object CarDetailsScreen: Screen(route = "CarDetails")
}
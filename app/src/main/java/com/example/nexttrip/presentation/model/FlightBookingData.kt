package com.example.nexttrip.presentation.model

data class FlightBookingData(
    var departureCity: String = "",
    var departureCode: String = "",
    var departureAirport: String = "",
    var departureDate: String = "",
    var arrivalCity: String = "",
    var arrivalCode: String = "",
    var arrivalAirport: String = "",
    var arrivalDate: String = "",
    var totalTravelers: String = "",
    var adults: String = "",
    var childs: String = "",
    var infants: String = "",
    var type: String = "",
    var roundway: Boolean = false
)

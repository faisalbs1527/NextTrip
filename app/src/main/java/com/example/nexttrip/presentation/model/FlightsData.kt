package com.example.nexttrip.presentation.model

data class FlightsData(
    var airline: String = "",
    var arrivalAirport: String = "",
    var arrivalTime: String = "",
    var classType: String = "",
    var currency: String = "",
    var departureAirport: String = "",
    var departureTime: String = "",
    var flightNumber: String = "",
    var price: Int = 0,
    var duration: String = "",
    var stop: String = "Non-Stops"
)

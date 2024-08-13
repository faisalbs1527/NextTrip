package com.example.nexttrip.presentation.model

data class FlightsData(
    val airline: String,
    val arrivalAirport: String,
    val arrivalTime: String,
    val classType: String,
    val currency: String,
    val departureAirport: String,
    val departureTime: String,
    val flightNumber: String,
    val price: Int,
    var duration: String = "",
    val stop: String = "Non-Stops"
)

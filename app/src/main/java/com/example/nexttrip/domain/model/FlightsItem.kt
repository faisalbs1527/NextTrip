package com.example.nexttrip.domain.model

data class FlightsItem(
    val airline: String,
    val arrivalAirport: String,
    val arrivalTime: String,
    val classType: String,
    val currency: String,
    val departureAirport: String,
    val departureTime: String,
    val flightNumber: String,
    val price: Int
)
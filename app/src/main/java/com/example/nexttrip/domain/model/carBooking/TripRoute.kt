package com.example.nexttrip.domain.model.carBooking

data class TripRoute(
    val fromLocation: LocationDetails,
    val toLocation: LocationDetails,
    val price: Int
)

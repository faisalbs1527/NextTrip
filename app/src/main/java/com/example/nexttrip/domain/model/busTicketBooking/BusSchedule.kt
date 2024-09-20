package com.example.nexttrip.domain.model.busTicketBooking

data class BusSchedule(
    val arrivalTime: String,
    val departureTime: String,
    val pickupPoints: List<String>
)
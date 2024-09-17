package com.example.nexttrip.presentation.model

import com.example.nexttrip.domain.model.carBooking.TripRoute

data class AvailableCarData(
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val rotation: Float = 0f,
    val carName: String,
    val model: String,
    val riderName: String,
    val fuelType: String,
    val gearType: String,
    val color: String,
    val successfulRides: Int,
    val rating: Double,
    val availableRoutes: List<TripRoute>
)

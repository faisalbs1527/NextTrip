package com.example.nexttrip.domain.model.carBooking

import com.example.nexttrip.presentation.model.AvailableCarData

data class TripCars(
    val latitude: Double,
    val longitude: Double,
    val rotation: Double,
    val carName: String,
    val model: String,
    val riderName: String,
    val fuelType: String,
    val gearType: String,
    val successfulRides: Int,
    val rating: Double,
    val availableRoutes: List<TripRoute>
)

fun TripCars.toAvailableCarData() = AvailableCarData(
    latitude = latitude,
    longitude = longitude,
    rotation = rotation.toFloat(),
    carName = carName,
    model = model,
    riderName = riderName,
    fuelType = fuelType,
    gearType = gearType,
    successfulRides = successfulRides,
    rating = rating,
    availableRoutes = availableRoutes
)
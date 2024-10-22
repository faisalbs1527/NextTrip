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
    val color: String,
    val image: String,
    val successfulRides: Int,
    val reviews: Int,
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
    color = color,
    image = image,
    successfulRides = successfulRides,
    reviews = reviews,
    rating = rating,
    availableRoutes = availableRoutes
)
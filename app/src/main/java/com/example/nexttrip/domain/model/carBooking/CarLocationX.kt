package com.example.nexttrip.domain.model.carBooking

import com.example.nexttrip.presentation.model.GeoLocation

data class CarLocationX(
    val latitude: Double,
    val longitude: Double,
    val rotation: Double
)

fun CarLocationX.toGeoLocation()= GeoLocation(
    latitude = latitude,
    longitude = longitude,
    rotation = rotation.toFloat()
)
package com.example.nexttrip.presentation.model

import com.example.nexttrip.domain.model.hotelbooking.Room

data class AvailableHotelData(
    val id: Int = 0,
    val name: String = "",
    val image: String = "",
    val rating: Double = 0.1,
    val location: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val discount: Int = 0,
    val startPriceDiscount: Int = 0,
    val startPriceActual: Int = 0,
    val description: String = "",
    val cancellationPolicy: String = "",
    val pets: String = "",
    val smoking: String = "",
    val complimentaryService: List<String> = emptyList(),
    val checkIn: String = "",
    val checkOut: String = "",
    val rooms: List<Room> = emptyList()
)

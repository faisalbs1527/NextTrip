package com.example.nexttrip.presentation.model

data class AvailableHotelData(
    val id: Int,
    val name: String,
    val image: String,
    val rating: Double,
    val location: String,
    val discount: Int,
    val startPriceDiscount: Int,
    val startPriceActual: Int,
)

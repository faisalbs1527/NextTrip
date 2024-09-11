package com.example.nexttrip.domain.model.hotelbooking

import com.example.nexttrip.presentation.model.AvailableHotelData
import kotlin.math.ceil

data class Hotel(
    val check_in_time: String,
    val check_out_time: String,
    val start_price_discount: Int,
    val start_price_actual: Int,
    val city: String,
    val complimentary_services: List<String>,
    val description: String,
    val hotel_id: Int,
    val image_url: String,
    val location: String,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val policies: Policies,
    val rating: Double,
    val rooms: List<Room>
)

fun Hotel.toAvailableHotel() = AvailableHotelData(
    id = hotel_id,
    name = name,
    image = image_url,
    location = location,
    latitude = latitude,
    longitude = longitude,
    rating = rating,
    discount = ceil(((start_price_actual - start_price_discount) * 100).toDouble() / start_price_actual).toInt(),
    startPriceActual = start_price_actual,
    startPriceDiscount = start_price_discount,
    description = description,
    cancellationPolicy = policies.cancellation_policy,
    pets = policies.pets,
    smoking = policies.smoking,
    complimentaryService = complimentary_services,
    checkIn = check_in_time,
    checkOut = check_out_time,
    rooms = rooms
)
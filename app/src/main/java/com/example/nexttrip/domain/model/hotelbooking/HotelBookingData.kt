package com.example.nexttrip.domain.model.hotelbooking

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hotelBooking")
data class HotelBookingData(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val checkIn: String,
    val checkOut: String,
    val bookingDate: String,
    val hotelName: String,
    val rooms: Int
)

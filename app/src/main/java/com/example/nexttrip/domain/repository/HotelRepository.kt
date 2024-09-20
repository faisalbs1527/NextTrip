package com.example.nexttrip.domain.repository

import com.example.nexttrip.domain.model.hotelbooking.HotelBookingData
import com.example.nexttrip.domain.model.hotelbooking.HotelData

interface HotelRepository {
    suspend fun getHotels(): HotelData

    suspend fun saveHotelBookingInfo(hotelBookingData: HotelBookingData)

    suspend fun getHotelBookingInfo(): List<HotelBookingData>
}
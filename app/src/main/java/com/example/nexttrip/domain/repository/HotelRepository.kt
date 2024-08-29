package com.example.nexttrip.domain.repository

import com.example.nexttrip.domain.model.hotelbooking.HotelData

interface HotelRepository {
    suspend fun getHotels(): HotelData
}
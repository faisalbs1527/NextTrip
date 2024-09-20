package com.example.nexttrip.data.network.api

import com.example.nexttrip.domain.model.hotelbooking.Hotel
import com.example.nexttrip.domain.model.hotelbooking.HotelData
import retrofit2.http.GET

interface HotelAPI {
    @GET("NextTrip/hotels.json")
    suspend fun getHotels(): HotelData
}
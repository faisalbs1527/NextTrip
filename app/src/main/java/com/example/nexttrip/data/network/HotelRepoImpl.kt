package com.example.nexttrip.data.network

import com.example.nexttrip.data.network.api.HotelAPI
import com.example.nexttrip.domain.model.hotelbooking.HotelData
import com.example.nexttrip.domain.repository.HotelRepository
import javax.inject.Inject

class HotelRepoImpl @Inject constructor(
    private val apiService: HotelAPI
) : HotelRepository {
    override suspend fun getHotels(): HotelData {
        return apiService.getHotels()
    }
}
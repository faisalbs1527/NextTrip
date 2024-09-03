package com.example.nexttrip.data

import com.example.nexttrip.data.local.AppDatabase
import com.example.nexttrip.data.network.api.HotelAPI
import com.example.nexttrip.domain.model.hotelbooking.HotelBookingData
import com.example.nexttrip.domain.model.hotelbooking.HotelData
import com.example.nexttrip.domain.repository.HotelRepository
import javax.inject.Inject

class HotelRepoImpl @Inject constructor(
    private val apiService: HotelAPI,
    private val dbService: AppDatabase
) : HotelRepository {
    override suspend fun getHotels(): HotelData {
        return apiService.getHotels()
    }

    override suspend fun saveHotelBookingInfo(hotelBookingData: HotelBookingData) {
        dbService.hotelBookingDao().saveHotelBookingInfo(hotelBookingData)
    }

    suspend fun getHotelBookingInfo(): List<HotelBookingData> {
        return dbService.hotelBookingDao().getHotelBookingInfo()
    }
}
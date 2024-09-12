package com.example.nexttrip.data

import com.example.nexttrip.data.network.api.CarBookingAPI
import com.example.nexttrip.domain.model.carBooking.CarLocation
import com.example.nexttrip.domain.model.carBooking.LocationDhaka
import com.example.nexttrip.domain.repository.CarBookingRepository
import javax.inject.Inject

class CarBookingRepoImpl @Inject constructor(
    private val apiService: CarBookingAPI
) : CarBookingRepository {
    override suspend fun getLocationsDhaka(): LocationDhaka {
        return apiService.getLocationsDhaka()
    }

    override suspend fun getCurrentCarLocations(): CarLocation {
        return apiService.getCarLocations()
    }
}
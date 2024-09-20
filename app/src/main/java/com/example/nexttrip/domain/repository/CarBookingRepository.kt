package com.example.nexttrip.domain.repository

import com.example.nexttrip.domain.model.carBooking.CarLocation
import com.example.nexttrip.domain.model.carBooking.LocationDhaka

interface CarBookingRepository {
    suspend fun getLocationsDhaka(): LocationDhaka

    suspend fun getCurrentCarLocations(): CarLocation
}
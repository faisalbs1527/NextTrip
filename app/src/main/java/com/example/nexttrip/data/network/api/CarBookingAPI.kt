package com.example.nexttrip.data.network.api

import com.example.nexttrip.domain.model.busTicketBooking.CityListBD
import com.example.nexttrip.domain.model.carBooking.CarLocation
import com.example.nexttrip.domain.model.carBooking.LocationDhaka
import retrofit2.http.GET

interface CarBookingAPI {
    @GET("NextTrip/locationDhaka.json")
    suspend fun getLocationsDhaka(): LocationDhaka

    @GET("NextTrip/carlocations.json")
    suspend fun getCarLocations(): CarLocation
}
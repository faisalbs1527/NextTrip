package com.example.nexttrip.data.network.api

import com.example.nexttrip.domain.model.busTicketBooking.CityListBD
import retrofit2.http.GET

interface BusTicketAPI {
    @GET("NextTrip/bd-districts.json")
    suspend fun getCities(): CityListBD
}
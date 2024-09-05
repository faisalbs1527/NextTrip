package com.example.nexttrip.data.network.api

import com.example.nexttrip.domain.model.busTicketBooking.CityListBD
import com.example.nexttrip.domain.model.busTicketBooking.Route
import com.example.nexttrip.domain.model.busTicketBooking.Routes
import retrofit2.http.GET

interface BusTicketAPI {
    @GET("NextTrip/bd-districts.json")
    suspend fun getCities(): CityListBD

    @GET("NextTrip/busschedule.json")
    suspend fun getRoutes(): Routes
}
package com.example.nexttrip.data.network.api

import com.example.nexttrip.domain.model.FlightsItem
import retrofit2.http.GET

interface FlightAPI {
    @GET("NextTrip/flights.json")
    suspend fun getFlights(): List<FlightsItem>
}
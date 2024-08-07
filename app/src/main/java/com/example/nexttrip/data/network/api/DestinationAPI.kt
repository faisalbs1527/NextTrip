package com.example.nexttrip.data.network.api

import com.example.nexttrip.domain.model.AirportsItem
import com.example.nexttrip.domain.model.DataDestination
import retrofit2.http.GET

interface DestinationAPI {

    @GET(value = "NextTrip/destinationData.json")
    suspend fun getDestinationData(): List<DataDestination>

    @GET(value = "NextTrip/airports.json")
    suspend fun getAirportsLocation(): List<AirportsItem>
}
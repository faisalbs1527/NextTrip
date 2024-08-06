package com.example.nexttrip.data.network.api

import com.example.nexttrip.domain.model.DataDestination
import okhttp3.Response
import retrofit2.http.GET

interface DestinationAPI {

    @GET(value = "NextTrip/destinationData.json")
    suspend fun getDestinationData(): List<DataDestination>
}
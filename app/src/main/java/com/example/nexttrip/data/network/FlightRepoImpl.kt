package com.example.nexttrip.data.network

import com.example.nexttrip.data.network.api.FlightAPI
import com.example.nexttrip.domain.model.FlightsItem
import com.example.nexttrip.domain.model.SeatPlan
import com.example.nexttrip.domain.repository.FlightRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FlightRepoImpl @Inject constructor(
    private val apiService: FlightAPI
) : FlightRepository {
    override suspend fun getFlights(): List<FlightsItem> = withContext(Dispatchers.IO) {
        return@withContext apiService.getFlights()
    }

    override suspend fun getSeatPlans(flightNo: String): SeatPlan =
        withContext(Dispatchers.IO) {
            val response = apiService.getSeatPlans()
            val seatList = response.find { it.flightNumber == flightNo }
            return@withContext seatList!!
        }
}
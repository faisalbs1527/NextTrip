package com.example.nexttrip.domain.repository

import com.example.nexttrip.domain.model.FlightsItem
import com.example.nexttrip.domain.model.SeatPlan

interface FlightRepository {
    suspend fun getFlights(): List<FlightsItem>

    suspend fun getSeatPlans(flightNo: String): SeatPlan
}
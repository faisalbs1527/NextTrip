package com.example.nexttrip.domain.repository

import com.example.nexttrip.domain.model.FlightsItem

interface FlightRepository {
    suspend fun getFlights(): List<FlightsItem>
}
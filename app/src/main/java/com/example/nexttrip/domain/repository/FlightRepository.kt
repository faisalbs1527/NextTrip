package com.example.nexttrip.domain.repository

import com.example.nexttrip.domain.model.FlightsItem
import com.example.nexttrip.domain.model.SeatPlan
import com.example.nexttrip.domain.model.TicketEntity

interface FlightRepository {
    suspend fun getFlights(): List<FlightsItem>

    suspend fun getSeatPlans(flightNo: String): SeatPlan

    suspend fun saveFlightTicketInfo(ticketInfo: TicketEntity)

    suspend fun getFlightBookingList(): List<TicketEntity>
}
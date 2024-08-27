package com.example.nexttrip.data

import com.example.nexttrip.data.local.AppDatabase
import com.example.nexttrip.data.network.api.FlightAPI
import com.example.nexttrip.domain.model.FlightsItem
import com.example.nexttrip.domain.model.SeatPlan
import com.example.nexttrip.domain.model.TicketEntity
import com.example.nexttrip.domain.repository.FlightRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FlightRepoImpl @Inject constructor(
    private val apiService: FlightAPI,
    private val dbService: AppDatabase
) : FlightRepository {
    override suspend fun getFlights(): List<FlightsItem> = withContext(Dispatchers.IO) {
        return@withContext apiService.getFlights()
    }

    override suspend fun getSeatPlans(flightNo: String): SeatPlan =
        withContext(Dispatchers.IO) {
            val response = apiService.getSeatPlans()
            val seatList = response.find { it.flightNumber == flightNo }
            println(seatList)
            return@withContext seatList!!
        }

    override suspend fun saveFlightTicketInfo(ticketInfo: TicketEntity) {
        dbService.ticketDao().saveFlightBookingInfo(ticketInfo)
    }

    override suspend fun getFlightBookingList(): List<TicketEntity> = withContext(Dispatchers.IO) {
        return@withContext dbService.ticketDao().getFlightBookingInfo()
    }
}
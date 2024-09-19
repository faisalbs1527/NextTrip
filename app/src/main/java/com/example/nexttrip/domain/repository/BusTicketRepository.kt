package com.example.nexttrip.domain.repository

import com.example.nexttrip.domain.model.TicketEntity
import com.example.nexttrip.domain.model.busTicketBooking.BusCompany
import com.example.nexttrip.domain.model.busTicketBooking.BusTicketEntity
import com.example.nexttrip.domain.model.busTicketBooking.CityListBD

interface BusTicketRepository {
    suspend fun getCityList(): CityListBD

    suspend fun getAvailableBuses(route: String): List<BusCompany>?

    suspend fun saveBusTicketInfo(ticketInfo: BusTicketEntity)

    suspend fun getBusBookingList(): List<BusTicketEntity>
}
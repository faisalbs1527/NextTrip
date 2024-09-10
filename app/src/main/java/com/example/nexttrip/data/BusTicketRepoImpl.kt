package com.example.nexttrip.data

import com.example.nexttrip.data.network.api.BusTicketAPI
import com.example.nexttrip.domain.model.busTicketBooking.BusCompany
import com.example.nexttrip.domain.model.busTicketBooking.CityListBD
import com.example.nexttrip.domain.repository.BusTicketRepository
import javax.inject.Inject

class BusTicketRepoImpl @Inject constructor(
    private val apiService: BusTicketAPI
) : BusTicketRepository {
    override suspend fun getCityList(): CityListBD {
        return apiService.getCities()
    }

    override suspend fun getAvailableBuses(route: String): List<BusCompany>? {
        val response = apiService.getRoutes()
        val availableBuses = response.routes.find { it.routeName == route }?.busCompanies
        return availableBuses
    }
}
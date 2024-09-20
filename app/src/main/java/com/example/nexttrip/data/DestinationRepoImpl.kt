package com.example.nexttrip.data

import com.example.nexttrip.data.network.api.DestinationAPI
import com.example.nexttrip.domain.model.AirportsItem
import com.example.nexttrip.domain.model.DataDestination
import com.example.nexttrip.domain.repository.DestinationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DestinationRepoImpl @Inject constructor(
    private val apiService: DestinationAPI
) : DestinationRepository {
    override suspend fun getDestinations(): List<DataDestination> = withContext(Dispatchers.IO) {
        return@withContext apiService.getDestinationData()
    }

    override suspend fun getAirports(): List<AirportsItem> = withContext(Dispatchers.IO) {
        return@withContext apiService.getAirportsLocation()
    }
}
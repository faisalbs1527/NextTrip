package com.example.nexttrip.data

import com.example.nexttrip.domain.model.DataDestination
import com.example.nexttrip.domain.repository.DestinationRepository

class DestinationRepoImpl(): DestinationRepository {
    override suspend fun getDestinations(): List<DataDestination> {

    }
}
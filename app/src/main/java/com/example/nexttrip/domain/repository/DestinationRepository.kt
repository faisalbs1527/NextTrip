package com.example.nexttrip.domain.repository

import com.example.nexttrip.domain.model.DataDestination

interface DestinationRepository {
    suspend fun getDestinations(): List<DataDestination>
}
package com.example.nexttrip.domain.model.busTicketBooking

import com.example.nexttrip.presentation.model.AvailableBusData

data class BusCompany(
    val buses: List<Bus>,
    val companyName: String
)
package com.example.nexttrip.presentation.model

import com.example.nexttrip.domain.model.busTicketBooking.BusSchedule
import com.example.nexttrip.domain.model.busTicketBooking.SeatData

data class AvailableBusData(
    val companyName: String,
    val busNo: String,
    val busSchedule: BusSchedule,
    val coachType: String,
    val price: String,
    val from: String,
    val to: String,
    val availableSeats: Int,
    val seats: List<SeatData>
)

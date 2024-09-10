package com.example.nexttrip.domain.model.busTicketBooking

import com.example.nexttrip.presentation.model.AvailableBusData

data class Bus(
    val busNo: String,
    val busSchedule: BusSchedule,
    val coachType: String,
    val price: String,
    val seats: List<SeatData>
)


fun Bus.toAvailableBusData(name: String, from: String, to: String, availableSeats: Int) =
    AvailableBusData(
        companyName = name,
        busNo = busNo,
        busSchedule = busSchedule,
        coachType = coachType,
        price = price,
        from = from,
        to = to,
        availableSeats = availableSeats,
        seats = seats
    )
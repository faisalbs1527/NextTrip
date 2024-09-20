package com.example.nexttrip.domain.model

data class SeatPlanData(
    val availableBusinessSeats: Int,
    val availableEconomySeats: Int,
    val seats: List<Seat>,
    val totalBusinessSeats: Int,
    val totalEconomySeats: Int,
    val totalSeats: Int
)
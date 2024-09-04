package com.example.nexttrip.domain.model.busTicketBooking

import com.example.nexttrip.presentation.model.CityData

data class District(
    val bn_name: String,
    val division_id: String,
    val id: String,
    val lat: String,
    val long: String,
    val name: String
)

fun District.toCityData() = CityData(
    name = name
)
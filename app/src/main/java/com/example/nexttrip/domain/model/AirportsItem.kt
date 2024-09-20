package com.example.nexttrip.domain.model

import com.example.nexttrip.presentation.model.AirportsData

data class AirportsItem(
    val carriers: String,
    val city: String,
    val code: String,
    val country: String,
    val direct_flights: String,
    val elev: String,
    val email: String,
    val icao: String,
    val lat: String,
    val lon: String,
    val name: String,
    val phone: String,
    val runway_length: String,
    val state: String,
    val type: String,
    val tz: String,
    val url: String,
    val woeid: String
)

fun AirportsItem.toAirportsData() = AirportsData(
    name = name,
    city = city,
    country = country,
    code = code
)
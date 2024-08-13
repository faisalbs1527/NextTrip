package com.example.nexttrip.domain.model

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.nexttrip.presentation.model.FlightsData
import com.example.nexttrip.utils.getDuration

data class FlightsItem(
    val airline: String,
    val arrivalAirport: String,
    val arrivalTime: String,
    val classType: String,
    val currency: String,
    val departureAirport: String,
    val departureTime: String,
    val flightNumber: String,
    val price: Int
)

@RequiresApi(Build.VERSION_CODES.O)
fun FlightsItem.toFlightsData() = FlightsData(
    airline = airline,
    arrivalAirport = arrivalAirport,
    arrivalTime = arrivalTime,
    classType = classType,
    currency = currency,
    departureAirport = departureAirport,
    departureTime = departureTime,
    flightNumber = flightNumber,
    price = price,
    duration = getDuration(departureTime, arrivalTime)
)
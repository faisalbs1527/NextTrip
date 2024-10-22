package com.example.nexttrip.utils

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.Color
import com.example.nexttrip.R
import com.example.nexttrip.presentation.model.AirportsData
import com.example.nexttrip.presentation.model.FlightBookingData
import com.example.nexttrip.presentation.model.FlightsData
import com.example.nexttrip.presentation.model.PassengerData
import com.example.nexttrip.presentation.model.ServiceItemData


val status = listOf(
    Pair("Done", Color(0xFF28A745)),
    Pair("In Progress", Color(0xFF007BFF)),
    Pair("Upcoming", Color(0xFFFFC107)),
    Pair("Cancel", Color(0xFFDC3545)),
)

val itemsList = listOf(
    ServiceItemData(1, "Flight", R.drawable.plane),
    ServiceItemData(2, "Hotel", R.drawable.hotel),
    ServiceItemData(3, "Travel", R.drawable.travel),
    ServiceItemData(4, "Bus", R.drawable.bus),
    ServiceItemData(5, "Car", R.drawable.car)
)

val from = AirportsData(
    name = "Hazzarat Shahjalal Int. Airport",
    city = "Dhaka",
    country = "Bangladesh",
    code = "DAC"
)
val to = AirportsData(
    name = "Cox's Bazar Airport",
    city = "Cox's Bazar",
    country = "Bangladesh",
    code = "CXB"
)

val departureData = FlightsData(
    airline = "Biman Bangladesh Airlines",
    arrivalAirport = "CXB",
    departureAirport = "DAC",
    classType = "Business",
    price = 150,
    flightNumber = "BG-201",
    currency = "",
    arrivalTime = "2024-08-15T10:00:00Z",
    departureTime = "2024-08-15T09:00:00Z",
    duration = "1h"
)
val returnData = FlightsData(
    airline = "Biman Bangladesh Airlines",
    arrivalAirport = "DAC",
    departureAirport = "CXB",
    classType = "Business",
    price = 150,
    flightNumber = "BG-201",
    currency = "",
    arrivalTime = "2024-08-15T10:00:00Z",
    departureTime = "2024-08-15T09:00:00Z",
    duration = "1h"
)
val bookingInfoData = FlightBookingData(
    departureCity = "Dhaka",
    departureCode = "DAC",
    departureDate = "24 Aug, 2024",
    departureAirport = "",
    arrivalCity = "Cox's Bazar",
    arrivalCode = "CXB",
    arrivalDate = "25 Aug, 2024",
    arrivalAirport = "",
    totalTravelers = "5",
    adults = "2",
    childs = "2",
    infants = "1",
    type = "Business",
    roundway = true
)

@RequiresApi(Build.VERSION_CODES.O)
val dummyPassengerList = listOf(
    PassengerData(
        title = "MR.",
        firstName = "Faisal",
        lastName = "Ahammed",
        birthDate = createDate("8", "5", "2000"),
        status = "Adult",
        passengerNo = "1"
    ),
    PassengerData(
        title = "MR.",
        firstName = "G M",
        lastName = "Taskin",
        birthDate = createDate("8", "5", "2000"),
        status = "Adult",
        passengerNo = "2"
    )
)

val cityList = listOf(
    "New York",
    "Los Angeles",
    "Chicago",
    "Houston",
    "Phoenix",
    "Philadelphia",
    "San Antonio",
    "San Diego",
    "Dallas",
    "San Jose"
)
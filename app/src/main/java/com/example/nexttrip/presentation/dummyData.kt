package com.example.nexttrip.presentation

import com.example.nexttrip.R
import com.example.nexttrip.presentation.model.AirportsData
import com.example.nexttrip.presentation.model.FlightBookingData
import com.example.nexttrip.presentation.model.FlightsData
import com.example.nexttrip.presentation.model.ServiceItemData


//val destinationList = listOf(
//    DestinationData("Dubai City",R.drawable.dubai,"16 Aug,2024","Business Class","$3100.00"),
//    DestinationData("Phi Phi Island",R.drawable.phiphi,"16 Aug,2024","Business/First Class","$1100.00"),
//    DestinationData("Kashmir",R.drawable.kashmir,"16 Sep,2024","Business/First Class","$1400.00"),
//    DestinationData("Manali City",R.drawable.manali,"18 Aug,2024","Business/First Class","$2260.00"),
//    DestinationData("Gangtok",R.drawable.gantok,"16 Aug,2024","Business Class","$1540.00")
//)

val itemsList = listOf(
    ServiceItemData("Flight", R.drawable.plane),
    ServiceItemData("Hotel", R.drawable.hotel),
    ServiceItemData("Travel", R.drawable.travel),
    ServiceItemData("Bus", R.drawable.bus),
    ServiceItemData("Car", R.drawable.car)
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
    flightNumber = "",
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
    flightNumber = "",
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
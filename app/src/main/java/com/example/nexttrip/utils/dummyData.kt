package com.example.nexttrip.utils

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.ImageBitmap
import com.example.nexttrip.R
import com.example.nexttrip.presentation.model.AirportsData
import com.example.nexttrip.presentation.model.FlightBookingData
import com.example.nexttrip.presentation.model.FlightsData
import com.example.nexttrip.presentation.model.GeoLocation
import com.example.nexttrip.presentation.model.PassengerData
import com.example.nexttrip.presentation.model.ServiceItemData


//val destinationList = listOf(
//    DestinationData("Dubai City",R.drawable.dubai,"16 Aug,2024","Business Class","$3100.00"),
//    DestinationData("Phi Phi Island",R.drawable.phiphi,"16 Aug,2024","Business/First Class","$1100.00"),
//    DestinationData("Kashmir",R.drawable.kashmir,"16 Sep,2024","Business/First Class","$1400.00"),
//    DestinationData("Manali City",R.drawable.manali,"18 Aug,2024","Business/First Class","$2260.00"),
//    DestinationData("Gangtok",R.drawable.gantok,"16 Aug,2024","Business Class","$1540.00")
//)

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

val carLocation = listOf(
    GeoLocation(23.77695924969011,90.39700328337875,0f),
    GeoLocation(23.77613113123303,90.39247866297734,90f),
    GeoLocation(23.766429922374797,90.401269354043,180f),
    GeoLocation(23.774356573926013,90.41122351892608,90f),
    GeoLocation(23.77908867293702,90.40592324931299,-90f),
    GeoLocation(23.788788937544666,90.3887296917876,0f),
    GeoLocation(23.804639031342276,90.39713255824739,0f),
    GeoLocation(23.824250724150644,90.40782222028639,180f),
    GeoLocation(23.819524907748303,90.43661950497864,90f),
    GeoLocation(23.81138001182734,90.39056583213107,-90f),
    GeoLocation(23.795591536412374,90.37078143806764,-90f),
    GeoLocation(23.78211450465139,90.37704649618775,180f),
    GeoLocation(23.739360947758215,90.37297970407468,0f),
    GeoLocation(23.74751035584785,90.3750680567814,0f),
    GeoLocation(23.76260050567616,90.36671464595463,180f),
    GeoLocation(23.744592725127305,90.41024031289413,180f),
    GeoLocation(23.751836377635172,90.40782222028639,90f),
    GeoLocation(23.752540600137394,90.39342357794021,-90f),
    GeoLocation(23.737147440235418,90.39551193064688,-90f)
)
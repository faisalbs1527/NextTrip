package com.example.nexttrip.presentation

import com.example.nexttrip.R
import com.example.nexttrip.presentation.model.DestinationData
import com.example.nexttrip.presentation.model.ServiceItemData


val destinationList = listOf(
    DestinationData("Dubai City",R.drawable.dubai,"16 Aug,2024","Business Class","$3100.00"),
    DestinationData("Phi Phi Island",R.drawable.phiphi,"16 Aug,2024","Business/First Class","$1100.00"),
    DestinationData("Kashmir",R.drawable.kashmir,"16 Sep,2024","Business/First Class","$1400.00"),
    DestinationData("Manali City",R.drawable.manali,"18 Aug,2024","Business/First Class","$2260.00"),
    DestinationData("Gangtok",R.drawable.gantok,"16 Aug,2024","Business Class","$1540.00")
)

val itemsList = listOf(
    ServiceItemData("Flight", R.drawable.plane),
    ServiceItemData("Hotel", R.drawable.hotel),
    ServiceItemData("Travel", R.drawable.travel),
    ServiceItemData("Bus", R.drawable.bus),
    ServiceItemData("Car", R.drawable.car)
)
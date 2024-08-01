package com.example.nexttrip.presentation

import com.example.nexttrip.R

data class ServiceItemData(val title: String, val image: Int)

val itemsList = listOf(
    ServiceItemData("Flight",  R.drawable.plane),
    ServiceItemData("Hotel", R.drawable.hotel),
    ServiceItemData("Travel",  R.drawable.travel),
    ServiceItemData("Bus", R.drawable.bus),
    ServiceItemData("Car",  R.drawable.car)
)
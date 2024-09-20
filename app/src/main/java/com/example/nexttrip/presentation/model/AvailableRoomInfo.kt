package com.example.nexttrip.presentation.model

data class AvailableRoomInfo(
    val acStatus: String = "",
    val actualPrice: Int = 0,
    val discountPrice: Int = 0,
    val bedType: String = "",
    val capacity: Int = 0,
    val image: String = "",
    val noOfBeds: Int = 0,
    val roomId: Int = 0,
    val roomType: String = "",
    val discount: Int = 0
)
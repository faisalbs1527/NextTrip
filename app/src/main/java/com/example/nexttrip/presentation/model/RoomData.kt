package com.example.nexttrip.presentation.model

data class RoomData(
    var roomNo: Int = 1,
    var adult: Int = 2,
    var children: Int = 0,
    val totalGuest: Int = adult + children
)

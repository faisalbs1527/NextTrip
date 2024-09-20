package com.example.nexttrip.domain.model.hotelbooking

import com.example.nexttrip.presentation.model.AvailableRoomInfo
import kotlin.math.ceil

data class Room(
    val ac_status: String,
    val actual_price: Int,
    val availability: Boolean,
    val bed_type: String,
    val capacity: Int,
    val discount_price: Int,
    val image_url: String,
    val number_of_beds: Int,
    val room_id: Int,
    val room_type: String
)

fun Room.toAvailableRoomInfo() = AvailableRoomInfo(
    roomId = room_id,
    roomType = room_type,
    bedType = bed_type,
    acStatus = ac_status,
    actualPrice = actual_price,
    discountPrice = discount_price,
    capacity = capacity,
    image = image_url,
    noOfBeds = number_of_beds,
    discount = ceil(((actual_price - discount_price) * 100).toDouble() / discount_price).toInt()
)
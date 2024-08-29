package com.example.nexttrip.domain.model.hotelbooking

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
package com.example.nexttrip.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "flightBooking")
data class TicketEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val flightNo: String,
    val departureCity: String,
    val arrivalCity: String,
    val departureTime: String,
    val arrivalTime: String,
    val ticket: ByteArray,
    val ticketName: String
)

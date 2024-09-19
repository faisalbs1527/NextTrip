package com.example.nexttrip.domain.model.busTicketBooking

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "busBooking")
data class BusTicketEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val busName: String,
    val departureCity: String,
    val arrivalCity: String,
    val travelDate: String,
    val ticket: ByteArray,
    val ticketName: String
)

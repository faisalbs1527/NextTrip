package com.example.nexttrip.presentation.model

import java.time.LocalDate
import java.util.Date

data class PassengerData(
    var title: String,
    var firstName: String,
    var lastName: String,
    var birthDate: LocalDate?,
    var status: String,
    var passengerNo: String
)

package com.example.nexttrip.domain.model

import com.example.nexttrip.presentation.model.DestinationData

data class DataDestination(
    val destination: String,
    val date: String,
    val type: String,
    val image: String,
    val amountFrom: String
)

//fun DataDestination.toDestinationData() = DestinationData(
//
//)
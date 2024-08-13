package com.example.nexttrip.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun getDuration(startDateTime: String, endDateTime: String): String {
    val formatter = DateTimeFormatter.ISO_DATE_TIME

    val departureTime = LocalDateTime.parse(startDateTime, formatter)
    val arrivalTime = LocalDateTime.parse(endDateTime, formatter)

    return Duration.between(departureTime, arrivalTime).toHours().toString()
}

@RequiresApi(Build.VERSION_CODES.O)
fun getTime(dateTime: String): String {
    val localDateTime = LocalDateTime.parse(dateTime.removeSuffix("Z"))
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    val timePart = localDateTime.format(timeFormatter)
    return timePart.toString()
}

@RequiresApi(Build.VERSION_CODES.O)
fun getDateWithDay(date: String):String{
    val inputFormatter = DateTimeFormatter.ofPattern("dd MMM, yyyy")
    val localDate = LocalDate.parse(date, inputFormatter)
    val outputFormatter = DateTimeFormatter.ofPattern("E, dd MMM")
    val formattedDate = localDate.format(outputFormatter)
    return formattedDate.toString()
}
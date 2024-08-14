package com.example.nexttrip.utils

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.nexttrip.components.formatDate
import com.example.nexttrip.components.getNextDate
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun getDuration(startDateTime: String, endDateTime: String): String {
    val formatter = DateTimeFormatter.ISO_DATE_TIME

    val departureTime = LocalDateTime.parse(startDateTime, formatter)
    val arrivalTime = LocalDateTime.parse(endDateTime, formatter)
    val duration = Duration.between(departureTime, arrivalTime)
    val hours = duration.toHours()
    val minutes = duration.toMinutes() % 60

    return buildString {
        if (hours > 0) append("${hours}h ")
        if (minutes > 0) append("${minutes}m")
    }.trim()
}

@RequiresApi(Build.VERSION_CODES.O)
fun getTime(dateTime: String): String {
    val localDateTime = LocalDateTime.parse(dateTime.removeSuffix("Z"))
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    val timePart = localDateTime.format(timeFormatter)
    return timePart.toString()
}

@RequiresApi(Build.VERSION_CODES.O)
fun getDateWithDay(date: String): String {
    val inputFormatter = DateTimeFormatter.ofPattern("dd MMM, yyyy")
    val localDate = LocalDate.parse(date, inputFormatter)
    val outputFormatter = DateTimeFormatter.ofPattern("E,dd MMM")
    val formattedDate = localDate.format(outputFormatter)
    return formattedDate.toString()
}

val currentDateMillis = System.currentTimeMillis()
val currentDate = formatDate(currentDateMillis)
val nextDate = getNextDate(currentDateMillis)
package com.example.nexttrip.utils

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.nexttrip.components.formatDate
import com.example.nexttrip.components.getNextDate
import java.text.SimpleDateFormat
import java.time.DateTimeException
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

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

@RequiresApi(Build.VERSION_CODES.O)
fun getDateWithMonth(date: LocalDate): String {
    val outputFormatter = DateTimeFormatter.ofPattern("dd MMM, yyyy")
    val formattedDate = date.format(outputFormatter)
    return formattedDate.toString()
}

@RequiresApi(Build.VERSION_CODES.O)
fun createDate(day: String, month: String, year: String): LocalDate? {
    return try {
        val dd = day.toInt()
        val mm = month.toInt()
        val yy = year.toInt()
        LocalDate.of(yy, mm, dd)
    } catch (e: NumberFormatException) {
        null
    } catch (e: DateTimeException) {
        null
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun getDay(date: LocalDate?): String {
    return date?.dayOfMonth?.toString() ?: ""
}

@RequiresApi(Build.VERSION_CODES.O)
fun getMonth(date: LocalDate?): String {
    return date?.monthValue?.toString() ?: ""
}

@RequiresApi(Build.VERSION_CODES.O)
fun getYear(date: LocalDate?): String {
    return date?.year?.toString() ?: ""
}

@RequiresApi(Build.VERSION_CODES.O)
fun convertToISO8601(dateString: String, timeString: String): String {

    // Combine date and time strings
    val combinedDateTimeString = "$dateString $timeString"
    val combinedFormatter = DateTimeFormatter.ofPattern("dd MMM, yyyy HH:mm")

    // Parse the combined date and time string into a LocalDateTime object
    val localDateTime = LocalDateTime.parse(combinedDateTimeString, combinedFormatter)

    // Convert LocalDateTime to ISO 8601 format with Zulu time (UTC)
    val iso8601Formatter =
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").withZone(ZoneOffset.UTC)
    val iso8601String = localDateTime.atZone(ZoneOffset.UTC).format(iso8601Formatter)

    return iso8601String
}

@RequiresApi(Build.VERSION_CODES.O)
fun hasTimeCrossed(givenTime: String): Boolean {
    // Define the date-time format
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")

    // Parse the given time string to ZonedDateTime
    val parsedTime = ZonedDateTime.parse(givenTime, formatter.withZone(ZoneOffset.UTC))

    // Get the current time in UTC
    val currentTime = ZonedDateTime.now(ZoneOffset.UTC)

    // Compare the parsed time with the current time
    return currentTime.isAfter(parsedTime)
}

@RequiresApi(Build.VERSION_CODES.O)
fun hasCrossedOnlyDate(givenDate: String): Boolean {
    val inputFormatter = DateTimeFormatter.ofPattern("dd MMM, yyyy")
    val localDate = LocalDate.parse(givenDate, inputFormatter)

    val currentDateLocal = LocalDate.parse(currentDate, inputFormatter)

    return currentDateLocal.isAfter(localDate)
}

fun ticketDate(): String {
    val dateFormat = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
    val currentDateTime = dateFormat.format(Date())
    return currentDateTime
}

@RequiresApi(Build.VERSION_CODES.O)
fun getBusDuration(startTime: String, endTime: String): String {
    val formatter = DateTimeFormatter.ofPattern("HH:mm")

    val time1 = LocalTime.parse(startTime, formatter)
    val time2 = LocalTime.parse(endTime, formatter)

    val duration = Duration.between(time1, time2)

    val hours = duration.toHours()
    val minutes = duration.toMinutes() % 60

    return "${hours}h ${minutes}m"
}

fun formatTime(seconds: Int): String {
    val minutes = seconds / 60
    val secondsDisplay = seconds % 60
    val formattedTime = String.format("%02d:%02d", minutes, secondsDisplay)
    return formattedTime
}

val currentDateMillis = System.currentTimeMillis()
val currentDate = formatDate(currentDateMillis)
val nextDate = getNextDate(currentDateMillis)
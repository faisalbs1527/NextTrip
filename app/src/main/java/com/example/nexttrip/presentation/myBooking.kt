package com.example.nexttrip.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightBookingListScreen(bookings: List<FlightBooking>) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Bookings") }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            items(bookings) { booking ->
                FlightBookingItem(booking)
                Divider()
            }
        }
    }
}

@Composable
fun FlightBookingItem(booking: FlightBooking) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Flight ${booking.flightNumber}",
                style = MaterialTheme.typography.titleMedium,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "From: ${booking.departureCity}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "To: ${booking.arrivalCity}",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Departure Date: ${booking.departureDate}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Arrival Date: ${booking.arrivalDate}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview
@Composable
private fun Show() {
    val bookings = listOf(
        FlightBooking("AB123", "New York", "Los Angeles", "2024-08-30", "2024-08-30"),
        FlightBooking("CD456", "San Francisco", "Seattle", "2024-09-05", "2024-09-05")
        // Add more bookings as needed
    )
    FlightBookingListScreen(bookings = bookings)
}

data class FlightBooking(
    val flightNumber: String,
    val departureCity: String,
    val arrivalCity: String,
    val departureDate: String,
    val arrivalDate: String
)

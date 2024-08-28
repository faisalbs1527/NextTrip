package com.example.nexttrip.presentation.myBooking

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nexttrip.domain.model.TicketEntity
import com.example.nexttrip.navigation.Screen
import com.example.nexttrip.ui.theme.Font_SFPro
import com.example.nexttrip.ui.theme.red40

@Composable
fun MyBookingsScreen(navController: NavController, innerPadding: PaddingValues) {

    val viewModel: MyBookingViewModel = hiltViewModel()

    val bookings by viewModel.ticketInfo.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getTicketInfo()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        Column(
            modifier = Modifier
                .background(color = Color.Gray.copy(0.2f))
                .fillMaxSize()
                .padding(vertical = 30.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
                    contentDescription = "",
                    modifier = Modifier
                        .weight(.1f)
                        .size(36.dp)
                        .clickable {

                        }
                )
                Row(
                    modifier = Modifier
                        .weight(.8f),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "My Bookings",
                        fontFamily = Font_SFPro,
                        fontSize = 20.sp,
                        color = red40,
                        fontWeight = FontWeight(600)
                    )
                }
                Spacer(modifier = Modifier.weight(.1f))
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                items(bookings) { booking ->
                    BookingItem(booking)
                }
            }
        }
    }
}

@Composable
fun BookingItem(booking: TicketEntity) {
    Card(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 8.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Flight ${booking.flightNo}",
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
                text = "Departure Date: ${booking.departureTime}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Arrival Date: ${booking.arrivalTime}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Show() {
    MyBookingsScreen(rememberNavController(), PaddingValues(0.dp))
}

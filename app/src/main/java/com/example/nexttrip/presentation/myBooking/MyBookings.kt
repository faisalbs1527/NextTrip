package com.example.nexttrip.presentation.myBooking

import android.os.Build
import androidx.annotation.RequiresApi
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
import com.example.nexttrip.components.appBar.SelectionBar
import com.example.nexttrip.components.bookingCard.BookingItem
import com.example.nexttrip.components.bookingCard.BusBookingItem
import com.example.nexttrip.components.bookingCard.HotelBookingItem
import com.example.nexttrip.navigation.Screen
import com.example.nexttrip.ui.theme.Font_SFPro
import com.example.nexttrip.ui.theme.red40

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyBookingsScreen(
    navController: NavController, innerPadding: PaddingValues,
    viewModel: MyBookingViewModel = hiltViewModel()
) {

    val bookings by viewModel.ticketInfo.collectAsState()
    val busBookings by viewModel.busBookingList.collectAsState()
    val hotelBookings by viewModel.hotelBookingList.collectAsState()
    val selectedService by viewModel.selectedService.collectAsState()


    LaunchedEffect(key1 = Unit) {
        viewModel.getTicketInfo()
        viewModel.getBusBookingList()
        viewModel.getHotelBookingList()
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
                .padding(top = 30.dp)
                .padding(horizontal = 20.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
                    contentDescription = "",
                    modifier = Modifier
                        .weight(.1f)
                        .size(28.dp)
                        .clickable {
                            navController.popBackStack()
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
            SelectionBar(
                selectedId = selectedService
            ) {
                viewModel.updateSelectedService(it)
            }

            when (selectedService) {
                1 -> LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    items(bookings) { booking ->
                        BookingItem(booking) {
                            viewModel.getTicket(booking.id)
                            navController.navigate(Screen.PdfViewScreen.route)
                        }
                    }
                }

                2 -> LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    items(hotelBookings) { booking ->
                        HotelBookingItem(booking) {

                        }
                    }
                }

                4 -> LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    items(busBookings) { booking ->
                        BusBookingItem(booking) {
                            viewModel.getBusTicket(booking.id)
                            navController.navigate(Screen.PdfViewScreen.route)
                        }
                    }
                }
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
private fun Show() {
    MyBookingsScreen(rememberNavController(), PaddingValues(0.dp))
}

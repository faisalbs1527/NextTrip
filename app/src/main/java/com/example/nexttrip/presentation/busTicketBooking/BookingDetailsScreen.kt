package com.example.nexttrip.presentation.busTicketBooking

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddLocationAlt
import androidx.compose.material.icons.filled.AirlineSeatReclineNormal
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Paid
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nexttrip.components.ButtonCustom
import com.example.nexttrip.components.ForwardArrow
import com.example.nexttrip.components.IconInfoRow
import com.example.nexttrip.ui.theme.Font_SFPro
import com.example.nexttrip.ui.theme.black40
import com.example.nexttrip.ui.theme.gray
import com.example.nexttrip.ui.theme.red40
import com.example.nexttrip.ui.theme.red80

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BookingDetailsScreen(
    navController: NavController = rememberNavController(),
    viewModel: BusReservationViewModel = hiltViewModel()
) {

    val from by viewModel.fromLoc.collectAsState()
    val to by viewModel.toLoc.collectAsState()
    val travelDate by viewModel.travelDate.collectAsState()
    val selectedBus = viewModel.getSelectedBus()
    val selectedSeats = viewModel.getSeats()
    val totalPrice by viewModel.totalPrice.collectAsState()

    Scaffold(
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            ButtonCustom(
                text = "Next",
                modifier = Modifier.padding(
                    start = 20.dp,
                    end = 20.dp
                ),
                buttonColor = red40,
                textColor = Color.White
            ) {

            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(color = Color.Gray.copy(alpha = 0.2f))
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .background(color = red80)
                    .padding(vertical = 30.dp, horizontal = 20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Column {
                        Text(
                            text = "NextTrip",
                            fontSize = 28.sp,
                            fontFamily = Font_SFPro,
                            fontWeight = FontWeight(500),
                            color = Color.White
                        )
                        Text(
                            text = "Booking Information!!",
                            fontSize = 18.sp,
                            color = Color.White.copy(.7f),
                            fontFamily = Font_SFPro,
                            fontWeight = FontWeight(400)
                        )
                    }
                    Row(
                        modifier = Modifier
                            .background(color = Color.White, shape = RoundedCornerShape(4.dp))
                            .padding(horizontal = 12.dp, vertical = 4.dp)
                            .clickable {
                                navController.popBackStack()
                            }
                    ) {
                        Text(
                            text = "Back",
                            color = red80,
                            fontFamily = Font_SFPro,
                            fontWeight = FontWeight(600),
                        )
                    }

                }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 120.dp, start = 20.dp, end = 20.dp)
                    .verticalScroll(
                        state = rememberScrollState()
                    )
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.White, shape = RoundedCornerShape(4.dp))
                            .padding(horizontal = 12.dp, vertical = 20.dp)
                    ) {
                        Text(
                            text = selectedBus.companyName,
                            fontSize = 24.sp,
                            fontFamily = Font_SFPro,
                            fontWeight = FontWeight(400),
                            modifier = Modifier.padding(bottom = 12.dp)
                        )
                        InfoSection(
                            departure = from,
                            arrival = to
                        )
                        IconInfoRow(
                            modifier = Modifier.padding(top = 16.dp),
                            title = "Seat",
                            text = selectedSeats,
                            icon = Icons.Default.AirlineSeatReclineNormal
                        )
                        IconInfoRow(
                            modifier = Modifier.padding(top = 16.dp),
                            title = "Travel Date",
                            text = travelDate,
                            icon = Icons.Default.CalendarMonth
                        )
                        IconInfoRow(
                            modifier = Modifier.padding(top = 16.dp),
                            title = "Departure",
                            text = selectedBus.busSchedule.departureTime,
                            icon = Icons.Default.Timer
                        )
                        IconInfoRow(
                            modifier = Modifier.padding(top = 16.dp),
                            title = "Arrival",
                            text = selectedBus.busSchedule.arrivalTime,
                            icon = Icons.Default.Timer
                        )
                        IconInfoRow(
                            modifier = Modifier.padding(top = 16.dp),
                            title = "Total Price",
                            text = "BDT $totalPrice",
                            icon = Icons.Default.Paid
                        )
                        IconInfoRow(
                            modifier = Modifier.padding(top = 16.dp),
                            title = "PickUp Points",
                            text = "",
                            icon = Icons.Default.AddLocationAlt
                        )
                        LazyRow(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 12.dp),
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            items(selectedBus.busSchedule.pickupPoints) {
                                PickUpBox(text = it)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Show() {
    BookingDetailsScreen()
}

@Composable
fun InfoSection(
    departure: String,
    arrival: String
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .border(width = 1.dp, color = gray.copy(.1f), shape = RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(.3f),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = departure,
                    fontSize = 18.sp,
                    fontFamily = Font_SFPro,
                    fontWeight = FontWeight(500)
                )
            }

            Column(
                modifier = Modifier.weight(.4f),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .weight(.4f)
                            .padding(vertical = 8.dp)
                            .height(1.dp)
                            .background(color = Color.Black.copy(alpha = .2f))
                    )
                    Icon(
                        imageVector = Icons.Default.DirectionsBus,
                        contentDescription = "",
                        tint = red40,
                        modifier = Modifier
                            .weight(.2f)
                            .graphicsLayer { rotationZ = 0f }
                    )
                    ForwardArrow(modifier = Modifier.weight(.4f))
                }
            }

            Column(
                modifier = Modifier.weight(.3f),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = arrival,
                    fontSize = 18.sp,
                    fontFamily = Font_SFPro,
                    fontWeight = FontWeight(500)
                )
            }

        }
    }
}

@Composable
fun PickUpBox(text: String) {
    Row(
        modifier = Modifier
            .background(
                color = black40.copy(.1f),
                shape = RoundedCornerShape(4.dp)
            )
            .padding(6.dp)
            .clickable {
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Icon(
            imageVector = Icons.Default.LocationOn,
            modifier = Modifier.size(14.dp),
            contentDescription = "",
            tint = Color.Black.copy(.6f)
        )
        Text(
            text = text,
            fontSize = 12.sp,
            color = Color.Black,
            fontFamily = Font_SFPro,
            fontWeight = FontWeight(500),
        )
    }
}
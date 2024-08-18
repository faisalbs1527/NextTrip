package com.example.nexttrip.presentation.flightBooking.addingInfo

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.nexttrip.components.ButtonCustom
import com.example.nexttrip.components.HorizontalLine
import com.example.nexttrip.components.PassengerInput
import com.example.nexttrip.components.SeatPlan
import com.example.nexttrip.components.TicketText
import com.example.nexttrip.presentation.bookingInfoData
import com.example.nexttrip.presentation.departureData
import com.example.nexttrip.presentation.model.FlightBookingData
import com.example.nexttrip.presentation.model.FlightsData
import com.example.nexttrip.presentation.returnData
import com.example.nexttrip.ui.theme.Font_SFPro
import com.example.nexttrip.ui.theme.NextTripTheme
import com.example.nexttrip.ui.theme.red40
import com.example.nexttrip.utils.getDateWithDay

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddingInfoScreen(
    navController: NavController,
    bookingData: FlightBookingData,
    departureFlight: FlightsData,
    returnFlight: FlightsData
) {

    val viewModel: AddingInfoViewModel = hiltViewModel()
    viewModel.addPassenger(bookingData.adults, bookingData.childs, bookingData.infants)

    val passengers = bookingData.adults.toInt() + bookingData.childs.toInt()

    val passengerList by viewModel.passengerList.collectAsState()

    val selectedSeats by viewModel.selectedSeat.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getSeatPlans(departureFlight.flightNumber)
    }

    var pageStatus by remember {
        mutableStateOf(1)
    }
    var titleText by remember {
        mutableStateOf("Passenger Details")
    }

    Scaffold(
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            ButtonCustom(
                text = "Next",
                modifier = Modifier.padding(
                    start = 20.dp,
                    end = 20.dp
                )
            ) {
                if (pageStatus == 1) {
                    pageStatus = 2
                    titleText = "Select Seats"
                }
            }
        }
    ) { innerPadding ->
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
                            .size(36.dp)
                            .clickable {
                                if (pageStatus == 1) {
                                    navController.popBackStack()
                                } else if (pageStatus == 2) {
                                    pageStatus = 1
                                    titleText = "Passenger Details"
                                }
                            }
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "${bookingData.departureCity} to ${bookingData.arrivalCity}",
                            fontFamily = Font_SFPro,
                            fontSize = 20.sp,
                            color = red40,
                            fontWeight = FontWeight(600)
                        )
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                TicketText(
                                    text = getDateWithDay(bookingData.departureDate),
                                    size = 12
                                )
                                if (bookingData.roundway) {
                                    TicketText(text = "to", size = 12)
                                    TicketText(
                                        text = getDateWithDay(bookingData.arrivalDate),
                                        size = 12
                                    )
                                }
                            }
                            Box(
                                modifier = Modifier
                                    .height(8.dp)
                                    .width(1.dp)
                                    .background(color = Color.Black.copy(alpha = .4f))
                            )
                            TicketText(text = bookingData.type, size = 12)
                        }
                    }
                }
                HorizontalLine()
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, top = 12.dp)
//                    .background(color = Color.White)
                ) {
                    Text(
                        text = titleText,
                        fontSize = 22.sp,
                        fontFamily = Font_SFPro,
                        modifier = Modifier.padding(top = 8.dp),
                        fontWeight = FontWeight(600)
                    )
                    if (pageStatus == 1) {
                        Row(
                            modifier = Modifier.padding(bottom = 4.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            TicketText(text = "${bookingData.adults} Adults", size = 14)
                            Box(
                                modifier = Modifier
                                    .height(10.dp)
                                    .width(1.dp)
                                    .background(color = Color.Black.copy(alpha = .4f))
                            )
                            TicketText(text = "${bookingData.childs} Childrens", size = 14)
                            Box(
                                modifier = Modifier
                                    .height(10.dp)
                                    .width(1.dp)
                                    .background(color = Color.Black.copy(alpha = .4f))
                            )
                            TicketText(text = "${bookingData.infants} Infants", size = 14)
                        }
                    } else if (pageStatus == 2) {
                        TicketText(
                            text = "(${selectedSeats.size} of $passengers selected)",
                            size = 14
                        )
                    }
                }
                if (pageStatus == 1) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp, bottom = 36.dp, top = 4.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(passengerList) { passenger ->
                            PassengerInput(
                                passenger,
                                onUpdateTitle = {
                                    viewModel.updateTitle(
                                        passenger.status,
                                        passenger.passengerNo,
                                        it
                                    )
                                },
                                onUpdateFirstName = {
                                    viewModel.updateFirstName(
                                        passenger.status,
                                        passenger.passengerNo,
                                        it
                                    )
                                },
                                onUpdateLastName = {
                                    viewModel.updateLastName(
                                        passenger.status,
                                        passenger.passengerNo,
                                        it
                                    )
                                },
                                onUpdateDate = { day, month, year ->
                                    viewModel.updateBirthDate(
                                        passenger.status,
                                        passenger.passengerNo,
                                        day,
                                        month,
                                        year
                                    )
                                }
                            )
                        }
                    }
                } else if (pageStatus == 2) {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp, end = 20.dp, bottom = 36.dp, top = 4.dp)
                                .verticalScroll(
                                    state = rememberScrollState()
                                )
                        ) {
                            SeatPlan(viewModel, passengers, bookingData.type)
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
    NextTripTheme {
        AddingInfoScreen(
            navController = rememberNavController(),
            bookingData = bookingInfoData,
            departureFlight = departureData,
            returnFlight = returnData
        )
    }
}
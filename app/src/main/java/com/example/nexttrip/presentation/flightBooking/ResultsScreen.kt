package com.example.nexttrip.presentation.flightBooking

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nexttrip.R
import com.example.nexttrip.components.HorizontalLine
import com.example.nexttrip.components.TicketInfo
import com.example.nexttrip.components.TicketText
import com.example.nexttrip.presentation.model.FlightBookingData
import com.example.nexttrip.presentation.model.FlightsData
import com.example.nexttrip.ui.theme.Font_SFPro
import com.example.nexttrip.ui.theme.NextTripTheme
import com.example.nexttrip.ui.theme.gray
import com.example.nexttrip.ui.theme.red40
import com.example.nexttrip.utils.getDateWithDay

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ResultsScreen(navController: NavController, bookingData: FlightBookingData) {

    val viewModel: BookingViewModel = hiltViewModel()
    val flightList by viewModel.flightList.collectAsState()
    val returnFlightList by viewModel.returnList.collectAsState()
    val bothWayFlights = generateRoundWayFlights(flightList, returnFlightList, bookingData.roundway)

    LaunchedEffect(key1 = Unit) {
        viewModel.getFlights(
            arrivalAirport = bookingData.arrivalCode,
            departureAirport = bookingData.departureCode,
            classType = bookingData.type
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .background(color = Color.Gray.copy(0.2f))
                .fillMaxSize()
                .padding(vertical = 30.dp, horizontal = 20.dp)

        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBackIos, contentDescription = "",
                    modifier = Modifier
                        .size(26.dp)
                        .clickable {
                            navController.popBackStack()
                        }
                )

                Column(
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
                            TicketText(text = getDateWithDay(bookingData.departureDate), size = 12)
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
                Icon(
                    painter = painterResource(id = R.drawable.filter), contentDescription = "",
                    modifier = Modifier.size(30.dp),
                    tint = Color.Black
                )
            }
            HorizontalLine()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = "Available Tickets",
                    fontFamily = Font_SFPro,
                    fontSize = 18.sp,
                    fontWeight = FontWeight(600)
                )
                Text(
                    text = "(" + bothWayFlights.size.toString() + " Results)",
                    fontFamily = Font_SFPro,
                    fontSize = 18.sp,
                    color = gray,
                    fontWeight = FontWeight(600)
                )
            }
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(bothWayFlights) { (outgoing, incoming) ->
                    TicketInfo(outgoing, incoming, bookingData.roundway)
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
        ResultsScreen(navController = rememberNavController(), bookingData = FlightBookingData())
    }
}

private fun generateRoundWayFlights(
    outgoingFlights: List<FlightsData>,
    incomingFlights: List<FlightsData>,
    roundWay: Boolean
): List<Pair<FlightsData, FlightsData>> {
    val combinations = mutableListOf<Pair<FlightsData, FlightsData>>()
    for (outgoing in outgoingFlights) {
        if (roundWay) {
            for (incoming in incomingFlights) {
                combinations.add(Pair(outgoing, incoming))
            }
        } else {
            combinations.add(Pair(outgoing, FlightsData()))
        }
    }
    return combinations
}
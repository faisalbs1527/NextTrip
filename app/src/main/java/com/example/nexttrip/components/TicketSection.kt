package com.example.nexttrip.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.AirlineSeatReclineNormal
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nexttrip.R
import com.example.nexttrip.presentation.bookingInfoData
import com.example.nexttrip.presentation.departureData
import com.example.nexttrip.presentation.dummyPassengerList
import com.example.nexttrip.presentation.model.FlightBookingData
import com.example.nexttrip.presentation.model.FlightsData
import com.example.nexttrip.presentation.model.PassengerData
import com.example.nexttrip.presentation.returnData
import com.example.nexttrip.ui.theme.Font_SFPro
import com.example.nexttrip.ui.theme.gray
import com.example.nexttrip.ui.theme.red80
import com.example.nexttrip.utils.getTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TicketSection(
    departureFlight: FlightsData,
    returnFlight: FlightsData,
    bookingData: FlightBookingData,
    passengerList: List<PassengerData>,
    seatsDeparture: String,
    seatsReturn: String,
    width: Int
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
                .background(color = Color.White)
                .padding(start = 20.dp, end = 20.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            item {
                Text(
                    text = departureFlight.airline,
                    fontSize = 24.sp,
                    fontFamily = Font_SFPro,
                    fontWeight = FontWeight(400),
                    modifier = Modifier.padding(top = 16.dp, bottom = 2.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.airplane),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp)
                        .height(120.dp)
                        .background(color = gray.copy(.1f)),
                    contentScale = ContentScale.FillBounds
                )
            }
            item {
                TopSection(
                    departureCode = bookingData.departureCode,
                    departureCity = bookingData.departureCity,
                    arrivalCode = bookingData.arrivalCode,
                    arrivalCity = bookingData.arrivalCity,
                    travelDate = bookingData.departureDate,
                    flight = departureFlight
                )
            }
            if (bookingData.roundway) {
                item {
                    HorizontalLine()
                    TopSection(
                        departureCode = bookingData.arrivalCode,
                        departureCity = bookingData.arrivalCity,
                        arrivalCode = bookingData.departureCode,
                        arrivalCity = bookingData.departureCity,
                        travelDate = bookingData.arrivalDate,
                        flight = returnFlight
                    )
                }
            }
            item {
                Text(
                    text = "Passenger Details",
                    fontSize = 18.sp,
                    fontFamily = Font_SFPro,
                    fontWeight = FontWeight(600)
                )
            }
            items(passengerList) {
                Passenger(passengerData = it)
            }
            item {
                Spacer(modifier = Modifier.size(6.dp))
                if(bookingData.roundway){
                    Text(
                        text = "Departure",
                        fontSize = 18.sp,
                        fontFamily = Font_SFPro,
                        fontWeight = FontWeight(600),
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
                BottomSection(
                    flight = departureFlight,
                    seats = seatsDeparture,
                    seatType = bookingData.type
                )
            }
            if(bookingData.roundway){
                item {
                    Text(
                        text = "Return",
                        fontSize = 18.sp,
                        fontFamily = Font_SFPro,
                        fontWeight = FontWeight(600),
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    BottomSection(
                        flight = returnFlight,
                        seats = seatsReturn,
                        seatType = bookingData.type
                    )
                }
            }
            item {
                HorizontalLine()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 20.dp)
                ) {
                    val bitmap = generateBarcode("1234567890", width, (width * 0.22).toInt())
                    bitmap?.let {
                        Image(
                            bitmap = it.asImageBitmap(),
                            contentDescription = "Barcode",
                            modifier = Modifier.fillMaxWidth()
                        )
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
    TicketSection(
        departureFlight = departureData,
        returnFlight = returnData,
        bookingData = bookingInfoData,
        passengerList = dummyPassengerList,
        seatsDeparture = "3A-4B",
        seatsReturn = "2A-3B",
        width = 600
    )
}

@Composable
fun DateTimeBox(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    title: String,
    content: String
) {
    Box(
        modifier = modifier
            .border(width = 1.dp, color = gray.copy(.1f), shape = RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "",
                tint = red80
            )
            Text(
                text = title,
                fontSize = 14.sp,
                fontFamily = Font_SFPro,
                fontWeight = FontWeight(500),
                color = gray
            )
            Text(
                text = content,
                fontSize = 16.sp,
                fontFamily = Font_SFPro,
                fontWeight = FontWeight(500),
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TopSection(
    departureCode: String,
    departureCity: String,
    arrivalCode: String,
    arrivalCity: String,
    travelDate: String,
    flight: FlightsData
) {
    TravelInfo(
        departureCode = departureCode,
        departureCity = departureCity,
        arrivalCode = arrivalCode,
        arrivalCity = arrivalCity
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Spacer(modifier = Modifier.weight(.05f))
        DateTimeBox(
            modifier = Modifier.weight(.42f),
            icon = Icons.Default.CalendarMonth,
            title = "Date",
            content = travelDate
        )
        Spacer(modifier = Modifier.weight(.06f))
        DateTimeBox(
            modifier = Modifier.weight(.42f),
            icon = Icons.Default.AccessTime,
            title = "Time",
            content = getTime(flight.departureTime) + "-" + getTime(
                flight.arrivalTime
            )
        )
        Spacer(modifier = Modifier.weight(.05f))
    }
}

@Composable
fun BottomSection(
    flight: FlightsData,
    seats: String,
    seatType: String
) {
    HorizontalLine()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        InfoColumn(title = "Terminal", text = "3")
        InfoColumn(title = "Gate", text = "B7")
        InfoColumn(title = "Flight No", text = flight.flightNumber)
        InfoColumn(title = "Class", text = seatType)
    }
    HorizontalLine()
    IconInfoRow(
        modifier = Modifier.padding(vertical = 8.dp),
        title = "Seats",
        text = seats,
        icon = Icons.Default.AirlineSeatReclineNormal
    )
}
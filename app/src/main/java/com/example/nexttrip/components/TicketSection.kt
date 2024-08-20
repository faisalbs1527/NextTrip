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
    seats: String
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
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
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
            TravelInfo(bookingData = bookingData)
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
                    content = bookingData.departureDate
                )
                Spacer(modifier = Modifier.weight(.06f))
                DateTimeBox(
                    modifier = Modifier.weight(.42f),
                    icon = Icons.Default.AccessTime,
                    title = "Time",
                    content = getTime(departureFlight.departureTime) + "-" + getTime(departureFlight.arrivalTime)
                )
                Spacer(modifier = Modifier.weight(.05f))
            }
            Text(
                text = "Passenger Details",
                fontSize = 18.sp,
                fontFamily = Font_SFPro,
                fontWeight = FontWeight(600),
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        items(passengerList) {
            Passenger(passengerData = it)
        }
        item {
            Spacer(modifier = Modifier.size(14.dp))
            HorizontalLine()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                InfoColumn(title = "Terminal", text = "3")
                InfoColumn(title = "Gate", text = "B7")
                InfoColumn(title = "Flight No", text = departureFlight.flightNumber)
                InfoColumn(title = "Class", text = bookingData.type)
            }
            HorizontalLine()
            IconInfoRow(
                modifier = Modifier.padding(vertical = 8.dp),
                title = "Seats",
                text = seats,
                icon = Icons.Default.AirlineSeatReclineNormal
            )
            HorizontalLine()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 20.dp)
            ) {
                BarcodeView(text = "1234567890", modifier = Modifier.weight(1f))
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
        seats = "3A-4B"
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
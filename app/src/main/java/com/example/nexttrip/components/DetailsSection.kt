package com.example.nexttrip.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AirlineSeatReclineNormal
import androidx.compose.material.icons.filled.Luggage
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nexttrip.presentation.departureData
import com.example.nexttrip.presentation.dummyPassengerList
import com.example.nexttrip.presentation.model.FlightsData
import com.example.nexttrip.presentation.model.PassengerData
import com.example.nexttrip.ui.theme.Font_SFPro
import com.example.nexttrip.ui.theme.gray
import com.example.nexttrip.ui.theme.red40
import com.example.nexttrip.ui.theme.red80
import com.example.nexttrip.utils.getDateWithMonth

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailsSection(
    passengerList: List<PassengerData>,
    selectedSeats: String,
    flightData: FlightsData
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 36.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Text(
                text = "Personal Details",
                fontSize = 18.sp,
                fontFamily = Font_SFPro,
                fontWeight = FontWeight(600),
                modifier = Modifier.padding(top = 12.dp)
            )
        }

        items(passengerList) { passenger ->
            PersonalDetails(passenger)
        }
        item {
            Text(
                text = "Flight Details",
                fontSize = 18.sp,
                fontFamily = Font_SFPro,
                fontWeight = FontWeight(600),
                modifier = Modifier.padding(top = 8.dp)
            )
        }
        item {
            FlightDetails(
                flightData = flightData,
                seats = selectedSeats
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PersonalDetails(
    passenger: PassengerData
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = "${passenger.status} - ${passenger.passengerNo}",
            fontFamily = Font_SFPro,
            fontSize = 18.sp,
            color = red40,
            fontWeight = FontWeight(600),
            modifier = Modifier.padding(bottom = 4.dp)
        )
        InfoRow(title = "Full Name", text = "${passenger.firstName} ${passenger.lastName}")
        InfoRow(title = "Passport ID", text = "26531786371")
        InfoRow(title = "Date of Birth", text = getDateWithMonth(passenger.birthDate!!))
    }
}

@Composable
fun FlightDetails(
    modifier: Modifier = Modifier,
    flightData: FlightsData,
    seats: String
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(horizontal = 8.dp, vertical = 12.dp)
    ) {
        Text(
            text = flightData.airline,
            fontSize = 24.sp,
            fontFamily = Font_SFPro,
            fontWeight = FontWeight(400),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            InfoColumn(title = "Terminal", text = "3")
            InfoColumn(title = "Gate", text = "B7")
            InfoColumn(title = "Flight No", text = flightData.flightNumber)
        }
        HorizontalLine()
        IconInfoRow(
            modifier = Modifier.padding(top = 8.dp),
            title = "Seat",
            text = seats,
            icon = Icons.Default.AirlineSeatReclineNormal
        )
        IconInfoRow(
            modifier = Modifier.padding(top = 8.dp),
            title = "Baggage",
            text = "30kgs",
            icon = Icons.Default.Luggage
        )
        IconInfoRow(
            modifier = Modifier.padding(top = 8.dp),
            title = "Check in",
            text = "Home Check-in",
            icon = Icons.Default.Timer
        )
    }
}

@Composable
fun InfoColumn(
    modifier: Modifier = Modifier,
    title: String,
    text: String
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontSize = 12.sp,
            fontFamily = Font_SFPro,
            fontWeight = FontWeight(500),
            color = gray
        )
        Text(
            text = text,
            fontSize = 20.sp,
            fontFamily = Font_SFPro,
            fontWeight = FontWeight(500),
        )
    }
}

@Composable
fun IconInfoRow(
    modifier: Modifier = Modifier,
    title: String,
    text: String,
    icon: ImageVector
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "",
            tint = red80,
            modifier = Modifier.padding(end = 4.dp)
        )
        InfoRow(title = title, text = text)
    }
}

@Composable
fun InfoRow(
    modifier: Modifier = Modifier,
    title: String,
    text: String
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            fontFamily = Font_SFPro,
            fontWeight = FontWeight(400),
            color = gray
        )
        Text(
            text = text,
            fontSize = 16.sp,
            fontFamily = Font_SFPro,
            fontWeight = FontWeight(400),
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
private fun Show() {
    DetailsSection(
        passengerList = dummyPassengerList,
        selectedSeats = "3B-2C",
        flightData = departureData
    )
}
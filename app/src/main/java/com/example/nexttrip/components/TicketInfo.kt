package com.example.nexttrip.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nexttrip.presentation.model.FlightsData
import com.example.nexttrip.ui.theme.Font_SFPro
import com.example.nexttrip.ui.theme.red40
import com.example.nexttrip.utils.getTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TicketInfo(
    flightdata: FlightsData
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White, shape = RoundedCornerShape(4.dp))
    ) {
        ScheduleContent(
            startTime = getTime(flightdata.departureTime),
            endTime = getTime(flightdata.arrivalTime),
            startLoc = flightdata.departureAirport,
            endLoc = flightdata.arrivalAirport,
            duration = flightdata.duration,
            stops = flightdata.stop
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TicketText(text = flightdata.airline, size = 15)
            Text(
                text = "$${flightdata.price}",
                fontSize = 18.sp,
                color = red40,
                fontFamily = Font_SFPro,
                fontWeight = FontWeight(600)
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
private fun Show() {
    TicketInfo(
        flightdata = FlightsData(
            airline = "Biman Bangladesh Airlines",
            arrivalAirport = "CXB",
            departureAirport = "DAC",
            classType = "Business",
            price = 150,
            flightNumber = "",
            currency = "",
            arrivalTime = "2024-08-15T10:00:00Z",
            departureTime = "2024-08-15T09:00:00Z",
            duration = "1h"
        )
    )
}
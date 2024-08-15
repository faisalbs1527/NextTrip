package com.example.nexttrip.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
    flightData: FlightsData,
    flightDataReturn: FlightsData,
    roundWay: Boolean,
    onClick: (FlightsData, FlightsData) -> Unit
) {

    var price = flightData.price
    if (roundWay) price += flightDataReturn.price
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White, shape = RoundedCornerShape(4.dp))
            .clickable {
                onClick(flightData, flightDataReturn)
            }
    ) {
        ScheduleContent(
            startTime = getTime(flightData.departureTime),
            endTime = getTime(flightData.arrivalTime),
            startLoc = flightData.departureAirport,
            endLoc = flightData.arrivalAirport,
            duration = flightData.duration,
            stops = flightData.stop
        )
        if (roundWay) {
            HorizontalLine()
            ScheduleContent(
                startTime = getTime(flightDataReturn.departureTime),
                endTime = getTime(flightDataReturn.arrivalTime),
                startLoc = flightDataReturn.departureAirport,
                endLoc = flightDataReturn.arrivalAirport,
                duration = flightDataReturn.duration,
                stops = flightDataReturn.stop
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TicketText(text = flightData.airline, size = 15)
            Text(
                text = "$$price",
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
        flightData = FlightsData(
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
        ),
        flightDataReturn = FlightsData(
            airline = "Biman Bangladesh Airlines",
            arrivalAirport = "DAC",
            departureAirport = "CXB",
            classType = "Business",
            price = 150,
            flightNumber = "",
            currency = "",
            arrivalTime = "2024-08-15T10:00:00Z",
            departureTime = "2024-08-15T09:00:00Z",
            duration = "1h"
        ),
        roundWay = true,
        onClick = { one,two ->

        }
    )
}
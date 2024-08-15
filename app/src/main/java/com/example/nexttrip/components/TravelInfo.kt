package com.example.nexttrip.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nexttrip.R
import com.example.nexttrip.presentation.model.FlightBookingData
import com.example.nexttrip.ui.theme.Font_SFPro
import com.example.nexttrip.ui.theme.red40
import com.example.nexttrip.utils.getDateWithDay

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TravelInfo(
    bookingData: FlightBookingData
) {
    Column(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(.25f),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = bookingData.departureCode,
                    fontSize = 24.sp,
                    fontFamily = Font_SFPro,
                    fontWeight = FontWeight(500)
                )
                TicketText(text = bookingData.departureCity, size = 12)
            }

            Column(
                modifier = Modifier.weight(.5f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TicketText(text = getDateWithDay(bookingData.departureDate), size = 12)
                    if (bookingData.roundway) {
                        TicketText(text = "to", size = 12)
                        TicketText(text = getDateWithDay(bookingData.arrivalDate), size = 12)
                    }
                }
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
                        painter = painterResource(id = R.drawable.flight),
                        contentDescription = "",
                        tint = red40,
                        modifier = Modifier
                            .weight(.2f)
                            .graphicsLayer { rotationZ = 90f }
                    )
                    ForwardArrow(modifier = Modifier.weight(.4f))
                }

                TicketText(text = "${bookingData.totalTravelers} travellers", size = 12)
            }

            Column(
                modifier = Modifier.weight(.25f),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = bookingData.arrivalCode,
                    fontSize = 24.sp,
                    fontFamily = Font_SFPro,
                    fontWeight = FontWeight(500)
                )
                TicketText(text = bookingData.arrivalCity, size = 12)
            }

        }
        Row(
            modifier = Modifier.padding(top = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TicketText(text = "${bookingData.adults} Adults", size = 12)
            Box(
                modifier = Modifier
                    .height(10.dp)
                    .width(1.dp)
                    .background(color = Color.Black.copy(alpha = .4f))
            )
            TicketText(text = "${bookingData.childs} Childrens", size = 12)
            Box(
                modifier = Modifier
                    .height(10.dp)
                    .width(1.dp)
                    .background(color = Color.Black.copy(alpha = .4f))
            )
            TicketText(text = "${bookingData.infants} Infants", size = 12)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
private fun Show() {
    TravelInfo(
        bookingData = FlightBookingData(
            departureCity = "Dhaka",
            departureCode = "DAC",
            departureDate = "24 Aug, 2024",
            departureAirport = "",
            arrivalCity = "Cox's Bazar",
            arrivalCode = "CXB",
            arrivalDate = "25 Aug, 2024",
            arrivalAirport = "",
            totalTravelers = "5",
            adults = "2",
            childs = "2",
            infants = "1",
            type = "Business",
            roundway = true
        )
    )
}
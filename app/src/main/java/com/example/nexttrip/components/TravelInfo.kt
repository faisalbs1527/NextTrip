package com.example.nexttrip.components

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
import com.example.nexttrip.ui.theme.Font_LatoBold
import com.example.nexttrip.ui.theme.red40

@Composable
fun TravelInfo(
    startCode: String,
    endCode: String,
    startLoc: String,
    endLoc: String,
    startDate: String,
    endDate: String,
    totalTravelers: String,
    adults: String,
    childs: String,
    infants: String,
    type: String,
    roundway: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(.2f),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = startCode,
                fontSize = 20.sp,
                fontFamily = Font_LatoBold,
                fontWeight = FontWeight(400)
            )
            TicketText(text = startLoc, size = 12)
        }

        Column(
            modifier = Modifier.weight(.6f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TicketText(text = startDate, size = 12)
                if (roundway) {
                    TicketText(text = "to", size = 12)
                    TicketText(text = endDate, size = 12)
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

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TicketText(text = "$totalTravelers travellers", size = 14)
                Box(
                    modifier = Modifier
                        .height(10.dp)
                        .width(1.dp)
                        .background(color = Color.Black.copy(alpha = .4f))
                )
                TicketText(text = type, size = 14)
            }

        }

        Column(
            modifier = Modifier.weight(.2f),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = endCode,
                fontSize = 20.sp,
                fontFamily = Font_LatoBold,
                fontWeight = FontWeight(400)
            )
            TicketText(text = endLoc, size = 12)
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun Show() {
    TravelInfo(
        startCode = "DHA",
        endCode = "CXB",
        startLoc = "Dhaka",
        endLoc = "Cox's Bazar",
        startDate = "Sun,24 Aug",
        endDate = "Mon,25 Aug",
        totalTravelers = "4",
        adults = "2",
        childs = "1",
        infants = "1",
        type = "Business",
        roundway = true
    )
}
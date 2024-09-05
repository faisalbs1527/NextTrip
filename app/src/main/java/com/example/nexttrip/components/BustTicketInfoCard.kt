package com.example.nexttrip.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsBusFilled
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nexttrip.presentation.model.AvailableBusData
import com.example.nexttrip.ui.theme.Font_SFPro
import com.example.nexttrip.ui.theme.red40
import com.example.nexttrip.ui.theme.red80
import com.example.nexttrip.utils.getBusDuration

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BustTicketInfoCard(
    bus: AvailableBusData,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White, shape = RoundedCornerShape(4.dp))
            .clickable {

            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, bottom = 8.dp, start = 8.dp, end = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = bus.companyName,
                color = Color.Black,
                fontSize = 20.sp,
                fontFamily = Font_SFPro,
                fontWeight = FontWeight(500),
            )
            Row(
                modifier = Modifier
                    .background(color = red40, shape = RoundedCornerShape(4.dp))
                    .padding(horizontal = 28.dp, vertical = 4.dp)
            ) {
                Text(
                    text = bus.coachType,
                    color = Color.White,
                    fontFamily = Font_SFPro,
                    fontWeight = FontWeight(500),
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(.25f),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = bus.busSchedule.departureTime,
                    fontSize = 24.sp,
                    fontFamily = Font_SFPro,
                    fontWeight = FontWeight(400)
                )
                TicketText(text = bus.from, size = 15)
            }

            Column(
                modifier = Modifier.weight(.5f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
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
                        imageVector = Icons.Default.DirectionsBusFilled,
                        contentDescription = "",
                        tint = red40,
                        modifier = Modifier
                            .weight(.2f)
                    )
                    ForwardArrow(modifier = Modifier.weight(.4f))
                }
                TicketText(
                    text = getBusDuration(
                        bus.busSchedule.departureTime,
                        bus.busSchedule.arrivalTime
                    ), size = 12
                )

            }

            Column(
                modifier = Modifier.weight(.25f),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = bus.busSchedule.arrivalTime,
                    fontSize = 24.sp,
                    fontFamily = Font_SFPro,
                    fontWeight = FontWeight(400)
                )
                TicketText(text = bus.to, size = 15)
            }

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "",
                    modifier = Modifier.size(15.dp),
                    tint = red80
                )
                TicketText(text = bus.availableSeats.toString(), size = 12)
            }
            Text(
                text = "BDT ${bus.price}",
                fontSize = 18.sp,
                color = red40,
                fontFamily = Font_SFPro,
                fontWeight = FontWeight(600)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Show() {
//    BustTicketInfoCard(
//        onClick = {}
//    )
}
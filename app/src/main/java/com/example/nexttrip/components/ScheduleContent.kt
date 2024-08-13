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
import androidx.compose.material3.SnackbarDuration
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
import com.example.nexttrip.ui.theme.Font_SFPro
import com.example.nexttrip.ui.theme.red40


@Composable
fun ScheduleContent(
    startTime: String,
    endTime: String,
    startLoc: String,
    endLoc: String,
    duration: String,
    stops:String
) {
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
                text = startTime,
                fontSize = 24.sp,
                fontFamily = Font_SFPro,
                fontWeight = FontWeight(400)
            )
            TicketText(text = startLoc, size = 15)
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
                TicketText(text = duration, size = 12)
                Box(
                    modifier = Modifier
                        .height(8.dp)
                        .width(1.dp)
                        .background(color = Color.Black.copy(alpha = .4f))
                )
                TicketText(text = stops, size = 12)
            }

        }

        Column(
            modifier = Modifier.weight(.25f),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = endTime,
                fontSize = 24.sp,
                fontFamily = Font_SFPro,
                fontWeight = FontWeight(400)
            )
            TicketText(text = endLoc, size = 15)
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun Show() {
    ScheduleContent(
        startTime = "12:25",
        endTime = "13:10",
        startLoc = "DHA",
        endLoc = "CXB",
        duration = "1h",
        stops = "Non-Stops"
    )
}
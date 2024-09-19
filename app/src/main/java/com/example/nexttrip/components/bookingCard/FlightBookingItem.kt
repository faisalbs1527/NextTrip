package com.example.nexttrip.components.bookingCard

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nexttrip.domain.model.TicketEntity
import com.example.nexttrip.ui.theme.Font_SFPro
import com.example.nexttrip.utils.hasTimeCrossed
import com.example.nexttrip.utils.status

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BookingItem(
    booking: TicketEntity,
    onClick: () -> Unit
) {
    val statusNo =
        if (hasTimeCrossed(booking.arrivalTime)) 0 else if (hasTimeCrossed(booking.departureTime)) 1 else 2

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(color = Color.White, shape = RoundedCornerShape(4.dp))
            .padding(vertical = 16.dp, horizontal = 8.dp)
            .clickable {
                onClick()
            }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Text(
                text = "Flight ${booking.flightNo}",
                fontFamily = Font_SFPro,
                style = MaterialTheme.typography.titleMedium,
                fontSize = 18.sp
            )
            Text(
                text = status[statusNo].first,
                fontSize = 16.sp,
                color = status[statusNo].second,
                fontFamily = Font_SFPro,
                fontWeight = FontWeight(600)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "From: ${booking.departureCity}",
            fontFamily = Font_SFPro,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "To: ${booking.arrivalCity}",
            fontFamily = Font_SFPro,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Departure Date: ${booking.departureTime}",
            fontFamily = Font_SFPro,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "Arrival Date: ${booking.arrivalTime}",
            fontFamily = Font_SFPro,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
package com.example.nexttrip.components

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
import com.example.nexttrip.ui.theme.Font_LatoBold
import com.example.nexttrip.ui.theme.red40

@Composable
fun TicketInfo() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White, shape = RoundedCornerShape(4.dp))
    ) {
        ScheduleContent(
            startTime = "12:25",
            endTime = "13:10",
            startLoc = "DHA",
            endLoc = "CXB"
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical =  8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TicketText(text = "Airbus A350-1000", size = 14)
            Text(
                text = "$2260.00",
                fontSize = 20.sp,
                color = red40,
                fontFamily = Font_LatoBold,
                fontWeight = FontWeight(400)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Show() {
    TicketInfo()
}
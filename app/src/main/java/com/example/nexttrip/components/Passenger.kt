package com.example.nexttrip.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nexttrip.presentation.model.PassengerData
import com.example.nexttrip.ui.theme.Font_SFPro
import com.example.nexttrip.ui.theme.gray
import com.example.nexttrip.ui.theme.red80

@Composable
fun Passenger(passengerData: PassengerData) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 1.dp, color = gray.copy(.1f), shape = RoundedCornerShape(4.dp)),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "${passengerData.firstName} ${passengerData.lastName}",
                    fontSize = 16.sp,
                    fontFamily = Font_SFPro,
                    fontWeight = FontWeight(500),
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(2.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Date of Birth :",
                        fontSize = 14.sp,
                        fontFamily = Font_SFPro,
                        fontWeight = FontWeight(400),
                    )
                    Text(
                        text = "3 Dec,2024",
                        fontSize = 14.sp,
                        fontFamily = Font_SFPro,
                        fontWeight = FontWeight(400),
                        color = gray
                    )
                }
            }
            Text(
                text = passengerData.status,
                fontSize = 16.sp,
                fontFamily = Font_SFPro,
                fontWeight = FontWeight(500),
                color = red80,
                fontStyle = FontStyle.Italic
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Show() {
    Passenger(
        passengerData = PassengerData(
            title = "MR.",
            firstName = "Faisal",
            lastName = "Ahammed",
            birthDate = null,
            status = "Adult",
            passengerNo = "1"
        )
    )
}
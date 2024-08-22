package com.example.nexttrip.presentation.hotelBooking

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nexttrip.R
import com.example.nexttrip.ui.theme.Font_Lato
import com.example.nexttrip.ui.theme.Font_LatoBold
import com.example.nexttrip.ui.theme.Font_SFPro
import com.example.nexttrip.ui.theme.gray

@Composable
fun BookingScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .background(color = Color.Gray.copy(alpha = 0.2f))
                .fillMaxSize()
                .padding(vertical = 30.dp, horizontal = 20.dp)
                .verticalScroll(
                    state = rememberScrollState()
                )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBackIos, contentDescription = "",
                    modifier = Modifier
                        .weight(.1f)
                        .size(30.dp)
                )
                Text(
                    text = "Hotel",
                    fontSize = 28.sp,
                    fontFamily = Font_SFPro,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF8A1C40),
                    modifier = Modifier.weight(.8f),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.weight(.1f))
            }
            Text(
                text = "Reserve Your Rooms",
                fontSize = 26.sp,
                fontFamily = Font_SFPro,
                fontWeight = FontWeight(600),
                modifier = Modifier.padding(top = 30.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(4.dp))
            ) {
                Text(
                    text = "City/Area/Hotel Name",
                    fontSize = 16.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight(400),
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
                    color = gray
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Departure",
                        tint = Color.Black.copy(alpha = .6f),
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .size(24.dp)
                    )

                    Text(
                        text = "Cox's Bazar",
                        fontSize = 22.sp,
                        fontFamily = Font_SFPro,
                        fontWeight = FontWeight(500)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ShowReservationScreen() {
    BookingScreen()
}
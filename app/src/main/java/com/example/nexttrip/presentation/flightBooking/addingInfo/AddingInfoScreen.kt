package com.example.nexttrip.presentation.flightBooking.addingInfo

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nexttrip.components.ButtonCustom
import com.example.nexttrip.components.HorizontalLine
import com.example.nexttrip.components.PassengerInput
import com.example.nexttrip.components.TicketText
import com.example.nexttrip.ui.theme.Font_SFPro
import com.example.nexttrip.ui.theme.NextTripTheme
import com.example.nexttrip.ui.theme.red40

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddingInfoScreen(navController: NavController) {

    var titleText by remember {
        mutableStateOf("Passenger Details")
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .background(color = Color.Gray.copy(0.2f))
                .fillMaxSize()
                .padding(vertical = 30.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "",
                    modifier = Modifier
                        .size(36.dp)
                        .clickable {
                            navController.popBackStack()
                        }
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Dhaka to Cox's Bazar",
                        fontSize = 20.sp,
                        fontFamily = Font_SFPro,
                        color = red40,
                        modifier = Modifier.padding(end = 12.dp),
                        fontWeight = FontWeight(600)
                    )
                }
            }
            HorizontalLine()
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 12.dp)
//                    .background(color = Color.White)
            ) {
                Text(
                    text = titleText,
                    fontSize = 22.sp,
                    fontFamily = Font_SFPro,
                    modifier = Modifier.padding(top = 8.dp),
                    fontWeight = FontWeight(600)
                )
                Row(
                    modifier = Modifier.padding(bottom = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TicketText(text = "1 Adults", size = 14)
                    Box(
                        modifier = Modifier
                            .height(10.dp)
                            .width(1.dp)
                            .background(color = Color.Black.copy(alpha = .4f))
                    )
                    TicketText(text = "1 Childrens", size = 14)
                    Box(
                        modifier = Modifier
                            .height(10.dp)
                            .width(1.dp)
                            .background(color = Color.Black.copy(alpha = .4f))
                    )
                    TicketText(text = "0 Infants", size = 14)
                }
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 16.dp, top = 4.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(1) {
                    PassengerInput()
                }
            }
            ButtonCustom(
                text = "Next",
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 8.dp, bottom = 16.dp)
            ) {

            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
private fun Show() {
    NextTripTheme {
        AddingInfoScreen(navController = rememberNavController())
    }
}
package com.example.nexttrip.presentation.booking

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nexttrip.R
import com.example.nexttrip.components.TicketInfo
import com.example.nexttrip.components.TravelInfo
import com.example.nexttrip.ui.theme.Font_LatoBold
import com.example.nexttrip.ui.theme.NextTripTheme
import com.example.nexttrip.ui.theme.red40

@Composable
fun ResultsScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .background(color = Color.Gray.copy(0.2f))
                .fillMaxSize()
                .padding(vertical = 30.dp, horizontal = 20.dp)

        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBackIos, contentDescription = "",
                    modifier = Modifier
                        .size(26.dp)
                        .clickable {
                            navController.popBackStack()
                        }
                )
                Text(
                    text = "Search Results",
                    fontFamily = Font_LatoBold,
                    fontSize = 22.sp,
                    color = red40
                )
                Icon(
                    painter = painterResource(id = R.drawable.filter), contentDescription = "",
                    modifier = Modifier.size(30.dp),
                    tint = Color.Black
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(8.dp))
            ) {
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
                    roundway = false
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp, bottom = 16.dp)
            ) {
                Text(
                    text = "Available Tickets",
                    fontFamily = Font_LatoBold,
                    fontSize = 20.sp
                )
                Text(
                    text = "(10 Results)",
                    fontFamily = Font_LatoBold,
                    fontSize = 20.sp,
                    color = Color.Black.copy(0.4f)
                )
            }
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(10){
                    TicketInfo()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Show() {
    NextTripTheme {
        ResultsScreen(navController = rememberNavController())
    }
}
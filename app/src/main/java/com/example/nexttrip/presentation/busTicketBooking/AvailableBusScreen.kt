package com.example.nexttrip.presentation.busTicketBooking

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
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nexttrip.R
import com.example.nexttrip.components.BustTicketInfoCard
import com.example.nexttrip.components.HorizontalLine
import com.example.nexttrip.components.HotelInfoCard
import com.example.nexttrip.components.TicketText
import com.example.nexttrip.navigation.Screen
import com.example.nexttrip.ui.theme.Font_SFPro
import com.example.nexttrip.ui.theme.gray
import com.example.nexttrip.ui.theme.red40
import com.example.nexttrip.utils.getDateWithDay

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AvailableBusScreen(
    navController: NavController = rememberNavController(),
    viewModel: BusReservationViewModel = hiltViewModel()
) {
    val buses by viewModel.availableBuses.collectAsState()
    val route by viewModel.route.collectAsState()
    val travelDate by viewModel.travelDate.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getBuses()
    }

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
                            viewModel.clearBusList()
                            navController.popBackStack()
                        }
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = route,
                        fontFamily = Font_SFPro,
                        fontSize = 20.sp,
                        color = red40,
                        fontWeight = FontWeight(600)
                    )
                    TicketText(text = travelDate, size = 12)

                }
                Icon(
                    painter = painterResource(id = R.drawable.filter), contentDescription = "",
                    modifier = Modifier.size(30.dp),
                    tint = Color.Black
                )
            }
            HorizontalLine()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = "Available Buses",
                    fontFamily = Font_SFPro,
                    fontSize = 18.sp,
                    fontWeight = FontWeight(600)
                )
                Text(
                    text = "(" + buses.size.toString() + " Results)",
                    fontFamily = Font_SFPro,
                    fontSize = 18.sp,
                    color = gray,
                    fontWeight = FontWeight(600)
                )
            }
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(buses) {
                    BustTicketInfoCard(it) {
                        viewModel.updateBusSelection(it.busNo)
                        viewModel.getSeatList()
                        navController.navigate(Screen.SeatSelectionScreen.route)
                    }
                }
            }
        }
    }
}
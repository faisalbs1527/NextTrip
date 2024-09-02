package com.example.nexttrip.presentation.hotelBooking

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
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.nexttrip.components.ButtonCustom
import com.example.nexttrip.components.HorizontalLine
import com.example.nexttrip.components.RoomInfoCard
import com.example.nexttrip.components.TicketText
import com.example.nexttrip.navigation.Screen
import com.example.nexttrip.ui.theme.Font_SFPro
import com.example.nexttrip.ui.theme.red40
import com.example.nexttrip.utils.getDateWithDay

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AvailableRoomScreen(
    navController: NavController = rememberNavController(),
    viewModel: ReservationViewModel = hiltViewModel()
) {
    val checkInDate by viewModel.checkIn.collectAsState()
    val checkOutDate by viewModel.checkOut.collectAsState()
    val availableRooms by viewModel.availableRooms.collectAsState()
    val rooms by viewModel.roomList.collectAsState()

    var currRoomId by remember {
        mutableIntStateOf(0)
    }

    LaunchedEffect(currRoomId) {
        viewModel.getAvailableRooms(rooms[currRoomId])
    }
    Scaffold(
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            ButtonCustom(
                text = "Next",
                modifier = Modifier.padding(
                    start = 20.dp,
                    end = 20.dp
                )
            ) {
                navController.navigate(Screen.BookingSummaryScreen.route)
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .background(color = Color.Gray.copy(0.2f))
                    .fillMaxSize()
                    .padding(top = 30.dp, bottom = 56.dp, start = 20.dp, end = 20.dp)

            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
                        contentDescription = "",
                        modifier = Modifier
                            .size(26.dp)
                            .clickable {
                                navController.popBackStack()
                            }
                    )

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Select Rooms",
                            fontFamily = Font_SFPro,
                            fontSize = 20.sp,
                            color = red40,
                            fontWeight = FontWeight(600)
                        )
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                TicketText(text = getDateWithDay(checkInDate), size = 12)

                                TicketText(text = "to", size = 12)
                                TicketText(
                                    text = getDateWithDay(checkOutDate),
                                    size = 12
                                )

                            }
                            Box(
                                modifier = Modifier
                                    .height(8.dp)
                                    .width(1.dp)
                                    .background(color = Color.Black.copy(alpha = .4f))
                            )
                            TicketText(text = rooms.size.toString() + " rooms", size = 12)
                        }
                    }
                    Icon(
                        painter = painterResource(id = R.drawable.filter), contentDescription = "",
                        modifier = Modifier.size(30.dp),
                        tint = Color.Black
                    )
                }
                HorizontalLine()

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(availableRooms) {
                        RoomInfoCard(room = it) {

                        }
                    }
                }
            }
        }
    }
}
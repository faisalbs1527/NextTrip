package com.example.nexttrip.presentation.busTicketBooking

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nexttrip.R
import com.example.nexttrip.components.ButtonCustom
import com.example.nexttrip.components.HorizontalLine
import com.example.nexttrip.components.IdentificationBox
import com.example.nexttrip.components.SeatBox
import com.example.nexttrip.components.TicketText
import com.example.nexttrip.domain.model.Seat
import com.example.nexttrip.domain.model.busTicketBooking.SeatData
import com.example.nexttrip.navigation.Screen
import com.example.nexttrip.presentation.flightBooking.addingInfo.AddingInfoViewModel
import com.example.nexttrip.ui.theme.Font_SFPro
import com.example.nexttrip.ui.theme.black40
import com.example.nexttrip.ui.theme.green40
import com.example.nexttrip.ui.theme.red10
import com.example.nexttrip.ui.theme.red40
import com.example.nexttrip.ui.theme.red80

@Composable
fun SeatSelectionScreen(
    navController: NavController = rememberNavController(),
    viewModel: BusReservationViewModel = hiltViewModel()
) {
    val seatList by viewModel.seatList.collectAsState()
    val route by viewModel.route.collectAsState()
    val travelDate by viewModel.travelDate.collectAsState()
    val selectedSeats by viewModel.selectedSeats.collectAsState()


    Scaffold(
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            ButtonCustom(
                text = "Next",
                modifier = Modifier.padding(
                    start = 20.dp,
                    end = 20.dp
                ),
                buttonColor = if (selectedSeats.isNotEmpty()) red40 else black40.copy(.2f),
                textColor = if (selectedSeats.isNotEmpty()) Color.White else Color.Black
            ) {
                if (selectedSeats.isNotEmpty()) {

                }
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
                    .padding(vertical = 30.dp, horizontal = 20.dp)

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
                                viewModel.clearSeats()
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
                Text(
                    text = "Select Your Seats",
                    fontFamily = Font_SFPro,
                    fontSize = 18.sp,
                    fontWeight = FontWeight(600),
                    modifier = Modifier.padding(top = 8.dp)
                )
                TicketText(text = "(Maximum 4 seats can be selected.)", size = 13)
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .background(color = Color.White)
                        .padding(horizontal = 4.dp, vertical = 24.dp)
                        .verticalScroll(
                            state = rememberScrollState()
                        )
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        IdentificationBox(color = red10, text = "Available")
                        IdentificationBox(color = green40, text = "Selected")
                        IdentificationBox(color = red80, text = "Booked")
                    }
                    HorizontalLine()
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 4.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        SeatRow(row = "1", viewModel = viewModel, seatList = seatList)
                        SeatRow(row = "2", viewModel = viewModel, seatList = seatList)
                        SeatRow(row = "3", viewModel = viewModel, seatList = seatList)
                        SeatRow(row = "4", viewModel = viewModel, seatList = seatList)
                        SeatRow(row = "5", viewModel = viewModel, seatList = seatList)
                        SeatRow(row = "6", viewModel = viewModel, seatList = seatList)
                        SeatRow(row = "7", viewModel = viewModel, seatList = seatList)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Show() {
    SeatSelectionScreen()
}

@Composable
fun SeatRow(
    row: String,
    viewModel: BusReservationViewModel,
    seatList: List<SeatData>?
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp)
    ) {
        Row(
            modifier = Modifier.weight(.42f)
        ) {
            SeatBox(
                seatNo = row + "A",
                modifier = Modifier
                    .weight(.45f)
                    .height(44.dp),
                status = seatList?.find { it.seatNumber == row + "A" }?.status
            ) {
                viewModel.selectSeat(row + "A")
            }
            Spacer(modifier = Modifier.weight(.1f))
            SeatBox(
                seatNo = row + "B",
                modifier = Modifier
                    .weight(.45f)
                    .height(44.dp),
                status = seatList?.find { it.seatNumber == row + "B" }?.status
            ) {
                viewModel.selectSeat(row + "B")
            }
        }
        Spacer(modifier = Modifier.weight(.16f))
        Row(
            modifier = Modifier.weight(.42f)
        ) {
            SeatBox(
                seatNo = row + "C",
                modifier = Modifier
                    .weight(.45f)
                    .height(44.dp),
                status = seatList?.find { it.seatNumber == row + "C" }?.status
            ) {
                viewModel.selectSeat(row + "C")
            }
            Spacer(modifier = Modifier.weight(.1f))
            SeatBox(
                seatNo = row + "D",
                modifier = Modifier
                    .weight(.45f)
                    .height(44.dp),
                status = seatList?.find { it.seatNumber == row + "D" }?.status
            ) {
                viewModel.selectSeat(row + "D")
            }
        }
    }
}

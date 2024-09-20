package com.example.nexttrip.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.nexttrip.domain.model.Seat
import com.example.nexttrip.presentation.flightBooking.addingInfo.AddingInfoViewModel
import com.example.nexttrip.ui.theme.Font_SFPro
import com.example.nexttrip.ui.theme.green40
import com.example.nexttrip.ui.theme.red10
import com.example.nexttrip.ui.theme.red80

@Composable
fun SeatPlan(
    viewModel: AddingInfoViewModel,
    totalPassenger: Int,
    classType: String
) {
    val seatList by viewModel.seatList.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(horizontal = 4.dp, vertical = 24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            IdentificationBox(color = red10, text = "Available")
            IdentificationBox(color = green40, text = "Selected")
            IdentificationBox(color = red80, text = "Booked")
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            HorizontalLine()
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Business Class",
                    fontSize = 16.sp,
                    fontFamily = Font_SFPro,
                    fontWeight = FontWeight(600)
                )
            }
            HorizontalLine()

            BusinessRow(
                row = "1",
                viewModel = viewModel,
                passenger = totalPassenger,
                seatList = seatList,
                classType = classType
            )
            BusinessRow(
                row = "2",
                viewModel = viewModel,
                passenger = totalPassenger,
                seatList = seatList,
                classType = classType
            )
            BusinessRow(
                row = "3",
                viewModel = viewModel,
                passenger = totalPassenger,
                seatList = seatList,
                classType = classType
            )
            BusinessRow(
                row = "4",
                viewModel = viewModel,
                passenger = totalPassenger,
                seatList = seatList,
                classType = classType
            )

            Spacer(modifier = Modifier.height(8.dp))
            HorizontalLine()
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Economy Class",
                    fontSize = 16.sp,
                    fontFamily = Font_SFPro,
                    fontWeight = FontWeight(600)
                )
            }
            HorizontalLine()

            EconomyRow(
                row = "5",
                viewModel = viewModel,
                passenger = totalPassenger,
                seatList = seatList,
                classType = classType
            )
            EconomyRow(
                row = "6",
                viewModel = viewModel,
                passenger = totalPassenger,
                seatList = seatList,
                classType = classType
            )
            EconomyRow(
                row = "7",
                viewModel = viewModel,
                passenger = totalPassenger,
                seatList = seatList,
                classType = classType
            )
            EconomyRow(
                row = "8",
                viewModel = viewModel,
                passenger = totalPassenger,
                seatList = seatList,
                classType = classType
            )
            EconomyRow(
                row = "9",
                viewModel = viewModel,
                passenger = totalPassenger,
                seatList = seatList,
                classType = classType
            )
            EconomyRow(
                row = "10",
                viewModel = viewModel,
                passenger = totalPassenger,
                seatList = seatList,
                classType = classType
            )
            EconomyRow(
                row = "11",
                viewModel = viewModel,
                passenger = totalPassenger,
                seatList = seatList,
                classType = classType
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

    }
}


@Composable
fun BusinessRow(
    row: String,
    viewModel: AddingInfoViewModel,
    passenger: Int,
    seatList: List<Seat>,
    classType: String
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
                modifier = Modifier.weight(.45f),
                status = seatList.find { it.seatNumber == row + "A" }?.status
            ) {
                viewModel.selectSeat(
                    seatNo = row + "A",
                    totalPassenger = passenger,
                    classType = classType
                )
            }
            Spacer(modifier = Modifier.weight(.1f))
            SeatBox(
                seatNo = row + "B",
                modifier = Modifier.weight(.45f),
                status = seatList.find { it.seatNumber == row + "B" }?.status
            ) {
                viewModel.selectSeat(
                    seatNo = row + "B",
                    totalPassenger = passenger,
                    classType = classType
                )
            }
        }
        Spacer(modifier = Modifier.weight(.16f))
        Row(
            modifier = Modifier.weight(.42f)
        ) {
            SeatBox(
                seatNo = row + "C",
                modifier = Modifier.weight(.45f),
                status = seatList.find { it.seatNumber == row + "C" }?.status
            ) {
                viewModel.selectSeat(
                    seatNo = row + "C",
                    totalPassenger = passenger,
                    classType = classType
                )
            }
            Spacer(modifier = Modifier.weight(.1f))
            SeatBox(
                seatNo = row + "D",
                modifier = Modifier.weight(.45f),
                status = seatList.find { it.seatNumber == row + "D" }?.status
            ) {
                viewModel.selectSeat(
                    seatNo = row + "D",
                    totalPassenger = passenger,
                    classType = classType
                )
            }
        }
    }
}

@Composable
fun EconomyRow(
    row: String,
    viewModel: AddingInfoViewModel,
    passenger: Int,
    seatList: List<Seat>,
    classType: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {
        Row(
            modifier = Modifier.weight(.42f)
        ) {
            SeatBox(
                seatNo = row + "A",
                modifier = Modifier.weight(.3f),
                status = seatList.find { it.seatNumber == row + "A" }?.status
            ) {
                viewModel.selectSeat(
                    seatNo = row + "A",
                    totalPassenger = passenger,
                    classType = classType
                )
            }
            Spacer(modifier = Modifier.weight(.05f))
            SeatBox(
                seatNo = row + "B",
                modifier = Modifier.weight(.3f),
                status = seatList.find { it.seatNumber == row + "B" }?.status
            ) {
                viewModel.selectSeat(
                    seatNo = row + "B",
                    totalPassenger = passenger,
                    classType = classType
                )
            }
            Spacer(modifier = Modifier.weight(.05f))
            SeatBox(
                seatNo = row + "C",
                modifier = Modifier.weight(.3f),
                status = seatList.find { it.seatNumber == row + "C" }?.status
            ) {
                viewModel.selectSeat(
                    seatNo = row + "C",
                    totalPassenger = passenger,
                    classType = classType
                )
            }
        }
        Spacer(modifier = Modifier.weight(.16f))
        Row(
            modifier = Modifier.weight(.42f)
        ) {
            SeatBox(
                seatNo = row + "D",
                modifier = Modifier.weight(.3f),
                status = seatList.find { it.seatNumber == row + "D" }?.status
            ) {
                viewModel.selectSeat(
                    seatNo = row + "D",
                    totalPassenger = passenger,
                    classType = classType
                )
            }
            Spacer(modifier = Modifier.weight(.05f))
            SeatBox(
                seatNo = row + "E",
                modifier = Modifier.weight(.3f),
                status = seatList.find { it.seatNumber == row + "E" }?.status
            ) {
                viewModel.selectSeat(
                    seatNo = row + "E",
                    totalPassenger = passenger,
                    classType = classType
                )
            }
            Spacer(modifier = Modifier.weight(.05f))
            SeatBox(
                seatNo = row + "F",
                modifier = Modifier.weight(.3f),
                status = seatList.find { it.seatNumber == row + "F" }?.status
            ) {
                viewModel.selectSeat(
                    seatNo = row + "F",
                    totalPassenger = passenger,
                    classType = classType
                )
            }
        }
    }
}

@Composable
fun SeatBox(
    modifier: Modifier = Modifier,
    status: String? = "Available",
    seatNo: String = "5B",
    onSelect: () -> Unit
) {
    Box(
        modifier = modifier
            .size(32.dp)
            .background(
                color = if (status == "Booked") red80 else if (status == "Available") red10 else green40,
                shape = RoundedCornerShape(4.dp)
            )
            .clickable {
                onSelect()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = seatNo,
            color = if (status == "Booked") Color.White else red80,
            fontSize = 16.sp,
            fontFamily = Font_SFPro,
            fontWeight = FontWeight(500)
        )
    }
}

@Composable
fun IdentificationBox(
    color: Color,
    text: String
) {
    Row(
        modifier = Modifier.padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(20.dp)
                .background(color = color, shape = RoundedCornerShape(4.dp))
        )
        Text(
            text = text,
            fontSize = 12.sp,
            fontFamily = Font_SFPro,
            fontWeight = FontWeight(600)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Show() {
    SeatPlan(hiltViewModel(), 2,"")
}
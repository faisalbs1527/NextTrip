package com.example.nexttrip.presentation.hotelBooking

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.filled.AirlineSeatReclineNormal
import androidx.compose.material.icons.filled.BedroomParent
import androidx.compose.material.icons.filled.ContentPasteGo
import androidx.compose.material.icons.filled.House
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.People
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nexttrip.components.AddReturn
import com.example.nexttrip.components.ButtonRound
import com.example.nexttrip.components.DatePickerModel
import com.example.nexttrip.components.HorizontalLine
import com.example.nexttrip.components.PickerBox
import com.example.nexttrip.components.SelectBoxWithText
import com.example.nexttrip.components.formatDate
import com.example.nexttrip.components.formattedDateToMillis
import com.example.nexttrip.components.getNextDate
import com.example.nexttrip.ui.theme.Font_SFPro
import com.example.nexttrip.ui.theme.gray
import com.example.nexttrip.utils.currentDateMillis

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ReservationScreen(
    navController: NavController = rememberNavController()
) {

    val viewModel: ReservationViewModel = hiltViewModel()
    val checkIn by viewModel.checkIn.collectAsState()
    val checkOut by viewModel.checkOut.collectAsState()

    var showDatePicker by remember {
        mutableStateOf(false)
    }

    var selectedDatePicker by remember {
        mutableIntStateOf(0)
    }

    var showBottomSheet by remember {
        mutableStateOf(false)
    }

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
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(4.dp))
                        .padding(8.dp)
                ) {
                    Text(
                        text = "CITY/AREA/HOTEL NAME",
                        fontSize = 14.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight(400),
                        modifier = Modifier.padding(vertical = 8.dp),
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
                                .size(24.dp)
                        )

                        Text(
                            text = "Cox's Bazaar",
                            fontSize = 22.sp,
                            fontFamily = Font_SFPro,
                            fontWeight = FontWeight(500)
                        )
                    }
                    HorizontalLine()

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        PickerBox(
                            modifier = Modifier.weight(1f),
                            title = "CHECK IN",
                            contentText = checkIn,
                            icon = Icons.Default.ContentPasteGo
                        ) {
                            showDatePicker = true
                            selectedDatePicker = 1
                        }

                        PickerBox(
                            modifier = Modifier.weight(1f),
                            modifierIcon = Modifier.graphicsLayer(scaleX = -1f),
                            title = "CHECK OUT",
                            contentText = checkOut,
                            icon = Icons.Default.ContentPasteGo
                        ) {
                            showDatePicker = true
                            selectedDatePicker = 2
                        }
                    }

                    HorizontalLine()

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        PickerBox(
                            modifier = Modifier.weight(1f),
                            modifierIcon = Modifier.padding(end = 4.dp),
                            title = "GUESTS",
                            contentText = String.format("%02d", 4),
                            icon = Icons.Default.People
                        ) {
                            showBottomSheet = true
                        }
                        PickerBox(
                            modifier = Modifier.weight(1f),
                            title = "ROOMS",
                            contentText = "1",
                            icon = Icons.Default.House
                        ) {
                            showBottomSheet = true
                        }
                    }
                    HorizontalLine()
                    Text(
                        text = "Optional Filters",
                        fontSize = 16.sp,
                        fontFamily = Font_SFPro,
                        fontWeight = FontWeight(600),
                        modifier = Modifier.padding(top = 4.dp)
                    )
                    FlowRow(
                        modifier = Modifier.padding(top = 8.dp, bottom = 72.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        SelectBoxWithText(text = "Business") {

                        }
                        SelectBoxWithText(text = "Couples") {

                        }
                        SelectBoxWithText(text = "Families") {

                        }
                        SelectBoxWithText(text = "Friends") {

                        }
                    }
                }
                ButtonRound(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .offset(y = 45.dp)
                ) {

                }
            }
        }
    }
    if (showDatePicker) {
        DatePickerModel(
            initialDate = currentDateMillis,
            fromDate = if (selectedDatePicker == 1) currentDateMillis else formattedDateToMillis(
                checkIn
            ),
            onDateSelected = { selectedDate ->
                if (selectedDate != null) {
                    if (selectedDatePicker == 1) {
                        viewModel.onUpdateCheckIN(formatDate(selectedDate))
                        viewModel.onUpdateCheckOut(getNextDate(selectedDate))
                    } else {
                        viewModel.onUpdateCheckOut(formatDate(selectedDate))
                    }
                }
                showDatePicker = false
            },
            onDismiss = {
                showDatePicker = false
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ShowReservationScreen() {
    ReservationScreen()
}
package com.example.nexttrip.presentation.busTicketBooking

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nexttrip.components.ButtonRound
import com.example.nexttrip.components.CustomTextField
import com.example.nexttrip.components.DatePickerModel
import com.example.nexttrip.components.SelectBoxWithText
import com.example.nexttrip.components.formatDate
import com.example.nexttrip.ui.theme.Font_SFPro
import com.example.nexttrip.ui.theme.red80
import com.example.nexttrip.utils.cityList
import com.example.nexttrip.utils.currentDateMillis


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BusReservationScreen(
    navController: NavController = rememberNavController(),
    viewModel: BusReservationViewModel = hiltViewModel()
) {


    val citiesToShow by viewModel.citiesToShow.collectAsState()

    var fromText by remember { mutableStateOf("") }
    var toText by remember { mutableStateOf("") }
    var fromCity by remember { mutableStateOf("") }
    var toCity by remember { mutableStateOf("") }

    var travelDate by remember {
        mutableStateOf(formatDate(currentDateMillis))
    }

    var showDatePicker by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.getCities()
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Gray.copy(alpha = 0.2f))
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .height(220.dp)
                .background(color = red80)
                .padding(vertical = 30.dp, horizontal = 20.dp)
        ) {
            Text(
                text = "NextTrip",
                fontSize = 28.sp,
                fontFamily = Font_SFPro,
                fontWeight = FontWeight(500),
                color = Color.White
            )
            Text(
                text = "Book your bus!!",
                fontSize = 18.sp,
                color = Color.White.copy(.7f),
                fontFamily = Font_SFPro,
                fontWeight = FontWeight(400)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 94.dp, start = 20.dp, end = 20.dp)
                .verticalScroll(
                    state = rememberScrollState()
                )
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(8.dp))
                        .padding(8.dp),
                ) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp)
                    )
                    CustomTextField(
                        value = fromText,
                        placeholder = fromCity.ifEmpty { "From" },
                        onValueChange = {
                            fromText = it
                            viewModel.updateSuggestions(it)
                        },
                        cityList = citiesToShow,
                        onSelect = {
                            fromCity = it
                            viewModel.updateSuggestions("")
                            fromText = ""
                        },
                        iconRotation = 0f
                    )
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp)
                    )
                    CustomTextField(
                        value = toText,
                        placeholder = toCity.ifEmpty { "To" },
                        onValueChange = {
                            toText = it
                            viewModel.updateSuggestions(it)
                        },
                        cityList = citiesToShow,
                        onSelect = {
                            toCity = it
                            viewModel.updateSuggestions("")
                            toText = ""
                        },
                        iconRotation = 180f
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 24.dp)
                            .border(
                                width = 1.dp,
                                color = Color.Black.copy(.1f),
                                shape = RoundedCornerShape(4.dp)
                            )
                            .padding(12.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    showDatePicker = true
                                },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.CalendarMonth,
                                contentDescription = "",
                                modifier = Modifier
                                    .padding(end = 16.dp)
                                    .size(32.dp),
                                tint = Color.Black.copy(.6f)
                            )
                            Box(
                                modifier = Modifier
                                    .padding(horizontal = 8.dp)
                                    .height(36.dp)
                                    .width(1.dp)
                                    .background(color = Color.Black.copy(alpha = .2f))
                            )
                            Text(
                                text = travelDate,
                                fontSize = 16.sp,
                                color = Color.Black.copy(.6f),
                                fontFamily = Font_SFPro,
                                modifier = Modifier.padding(4.dp)
                            )
                        }
                    }
                    Text(
                        text = "Optional Filters",
                        fontSize = 16.sp,
                        fontFamily = Font_SFPro,
                        fontWeight = FontWeight(600),
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
                    )
                    FlowRow(
                        modifier = Modifier.padding(
                            start = 16.dp,
                            end = 16.dp,
                            top = 8.dp,
                            bottom = 72.dp
                        ),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        SelectBoxWithText(text = "AC") {

                        }
                        SelectBoxWithText(text = "Non AC") {

                        }
                        SelectBoxWithText(text = "Sleeper") {

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
            fromDate = currentDateMillis,
            onDateSelected = { selectedDate ->
                if (selectedDate != null) {
                    travelDate = formatDate(selectedDate)
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
private fun Show() {
    BusReservationScreen()
}
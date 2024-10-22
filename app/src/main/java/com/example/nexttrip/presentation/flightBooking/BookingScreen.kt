package com.example.nexttrip.presentation.flightBooking

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AirlineSeatReclineNormal
import androidx.compose.material.icons.filled.ContentPasteGo
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.People
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.nexttrip.R
import com.example.nexttrip.components.AddReturn
import com.example.nexttrip.components.BottomSheetModel
import com.example.nexttrip.components.ButtonRound
import com.example.nexttrip.components.DatePickerModel
import com.example.nexttrip.components.PickerBox
import com.example.nexttrip.components.TicketType
import com.example.nexttrip.components.formatDate
import com.example.nexttrip.components.formattedDateToMillis
import com.example.nexttrip.components.getNextDate
import com.example.nexttrip.navigation.Screen
import com.example.nexttrip.utils.from
import com.example.nexttrip.presentation.model.AirportsData
import com.example.nexttrip.presentation.model.FlightBookingData
import com.example.nexttrip.utils.to
import com.example.nexttrip.ui.theme.Font_Lato
import com.example.nexttrip.ui.theme.Font_LatoBold
import com.example.nexttrip.ui.theme.NextTripTheme
import com.example.nexttrip.utils.currentDateMillis
import com.google.gson.Gson

@Composable
fun BookingScreen(navController: NavController, innerPadding: PaddingValues) {
    BookingScreenSkeleton(navController, innerPadding)
}


@SuppressLint("DefaultLocale")
@Composable
fun BookingScreenSkeleton(navController: NavController, innerPadding: PaddingValues) {

    val viewModel: BookingViewModel = hiltViewModel()

    val selectedItem by viewModel.selectedItem.collectAsState()

    val departureDate by viewModel.departureDate.collectAsState()
    val returnDate by viewModel.returnDate.collectAsState()

    val totalTraveler by viewModel.totalTraveler.collectAsState()
    val adult by viewModel.adult.collectAsState()
    val children by viewModel.children.collectAsState()
    val infant by viewModel.infant.collectAsState()
    val selectedClass by viewModel.selectedClass.collectAsState()


    var showDatePicker by remember {
        mutableStateOf(false)
    }

    var selectedDatePicker by remember {
        mutableStateOf(0)
    }

    var showBottomSheet by remember {
        mutableStateOf(false)
    }

    //get the "from/to" data from search result

    val backStackEntry by navController.currentBackStackEntryAsState()
    val savedStateHandle = backStackEntry?.savedStateHandle

    val fromJsonSate = savedStateHandle?.getStateFlow("fromData", initialValue = "")
        ?.collectAsState()
    val toJsonState =
        savedStateHandle?.getStateFlow("toData", initialValue = "")?.collectAsState()

    val fromData =
        remember { mutableStateOf(from) }
    val toData =
        remember { mutableStateOf(to) }

    val fromJson = fromJsonSate?.value.orEmpty()
    val toJson = toJsonState?.value.orEmpty()

    if (fromJson.isNotEmpty()) fromData.value = Gson().fromJson(
        fromJson,
        AirportsData::class.java
    )

    if (toJson.isNotEmpty()) toData.value = Gson().fromJson(toJson, AirportsData::class.java)


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
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
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "NextTrip",
                    fontSize = 24.sp,
                    color = Color(0xFF8A1C40)
                )
                Icon(
                    imageVector = Icons.Default.Menu, contentDescription = "",
                    modifier = Modifier.size(36.dp)
                )
            }
            Text(
                text = "Book Your Flights",
                fontSize = 26.sp,
                fontFamily = Font_LatoBold,
                fontWeight = FontWeight(500),
                modifier = Modifier.padding(top = 30.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(16.dp)),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TicketType(text = "One Way", selected = selectedItem == 0) {
                    viewModel.onUpdateSelectedItem(0)
                }
                TicketType(text = "Round Way", selected = selectedItem == 1) {
                    viewModel.onUpdateSelectedItem(1)
                }
                TicketType(text = "Multi City", selected = selectedItem == 2) {
                    viewModel.onUpdateSelectedItem(2)
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(4.dp))
            ) {
                Text(
                    text = "Departure City",
                    fontSize = 14.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight(400),
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
                    color = Color.Black.copy(alpha = .6f)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(
                                Screen.SearchScreen.createRoute(
                                    text = "From Where?",
                                    selected = 1,
                                    from = fromData.value,
                                    to = toData.value
                                )
                            )
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.departure),
                        contentDescription = "Departure",
                        tint = Color.Black.copy(alpha = .6f),
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .size(24.dp)
                    )
                    Column(
                        modifier = Modifier.padding(start = 4.dp),
                        verticalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        Text(
                            text = fromData.value.city,
                            fontSize = 20.sp,
                            fontFamily = Font_LatoBold,
                            fontWeight = FontWeight(400)
                        )
                        Text(
                            text = fromData.value.name,
                            fontSize = 12.sp,
                            fontFamily = Font_Lato,
                            fontWeight = FontWeight(400),
                            color = Color.Black.copy(alpha = .6f)
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 16.dp)
                        .height(1.dp)
                        .background(color = Color.Black.copy(alpha = .2f))
                )
                Text(
                    text = "Arrival City",
                    fontSize = 14.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight(400),
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
                    color = Color.Black.copy(alpha = .6f)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                        .clickable {
                            navController.navigate(
                                Screen.SearchScreen.createRoute(
                                    text = "Where to?",
                                    selected = 2,
                                    from = fromData.value,
                                    to = toData.value
                                )
                            )
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrival),
                        contentDescription = "Departure",
                        tint = Color.Black.copy(alpha = .6f),
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .size(24.dp)
                    )
                    Column(
                        modifier = Modifier.padding(start = 4.dp),
                        verticalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        Text(
                            text = toData.value.city,
                            fontSize = 20.sp,
                            fontFamily = Font_LatoBold,
                            fontWeight = FontWeight(400)
                        )
                        Text(
                            text = toData.value.name,
                            fontSize = 12.sp,
                            fontFamily = Font_Lato,
                            fontWeight = FontWeight(400),
                            color = Color.Black.copy(alpha = .6f)
                        )
                    }
                }
            }
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(4.dp))
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        PickerBox(
                            modifier = Modifier.weight(1f),
                            title = "Departure Date",
                            contentText = departureDate,
                            icon = Icons.Default.ContentPasteGo
                        ) {
                            showDatePicker = true
                            selectedDatePicker = 1
                        }
                        if (selectedItem == 0) {
                            AddReturn(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(start = 8.dp)
                            ) {
                                viewModel.onUpdateSelectedItem(1)
                            }
                        } else {
                            PickerBox(
                                modifier = Modifier.weight(1f),
                                modifierIcon = Modifier.graphicsLayer(scaleX = -1f),
                                title = "Return Date",
                                contentText = returnDate,
                                icon = Icons.Default.ContentPasteGo
                            ) {
                                showDatePicker = true
                                selectedDatePicker = 2
                            }
                        }
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 16.dp)
                            .height(1.dp)
                            .background(color = Color.Black.copy(alpha = .2f))
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 84.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        PickerBox(
                            modifier = Modifier.weight(1f),
                            modifierIcon = Modifier.padding(end = 4.dp),
                            title = "Travelers",
                            contentText = String.format("%02d", totalTraveler),
                            icon = Icons.Default.People
                        ) {
                            showBottomSheet = true
                        }
                        PickerBox(
                            modifier = Modifier.weight(1f),
                            title = "Class",
                            contentText = if (selectedClass == 2) "Business" else "Economy",
                            icon = Icons.Default.AirlineSeatReclineNormal
                        ) {
                            showBottomSheet = true
                        }
                    }
                }
                ButtonRound(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
//                        .offset(y = 45.dp)
                        .padding(top = 235.dp, bottom = 16.dp)
                ) {
                    navController.navigate(
                        Screen.ResultsScreen.createRoute(
                            data = FlightBookingData(
                                departureCity = fromData.value.city,
                                departureCode = fromData.value.code,
                                departureDate = departureDate,
                                departureAirport = fromData.value.name,
                                arrivalCity = toData.value.city,
                                arrivalCode = toData.value.code,
                                arrivalDate = returnDate,
                                arrivalAirport = toData.value.name,
                                totalTravelers = totalTraveler.toString(),
                                adults = adult.toString(),
                                childs = children.toString(),
                                infants = infant.toString(),
                                type = if (selectedClass == 2) "Business" else "Economy",
                                roundway = selectedItem == 1
                            )
                        )
                    )
                }
            }
        }
    }
    if (showDatePicker) {
        DatePickerModel(
            initialDate = currentDateMillis,
            fromDate = if (selectedDatePicker == 1) currentDateMillis else formattedDateToMillis(
                departureDate
            ),
            onDateSelected = { selectedDate ->
                if (selectedDate != null) {
                    if (selectedDatePicker == 1) {
                        viewModel.onUpdateDepartureDate(formatDate(selectedDate))
                        viewModel.onUpdateReturnDate(getNextDate(selectedDate))
                    } else {
                        viewModel.onUpdateReturnDate(getNextDate(selectedDate))
                    }
                }
                showDatePicker = false
            },
            onDismiss = {
                showDatePicker = false
            }
        )
    }

    if (showBottomSheet) {
        BottomSheetModel(
            onDismiss = {
                showBottomSheet = false
            },
            curAdult = adult,
            curChild = children,
            curInfant = infant,
            curClass = selectedClass,
            onDone = { adultT, child, infantT, classType ->
                viewModel.onUpdateAdultCount(adultT)
                viewModel.onUpdateChildrenCount(child)
                viewModel.onUpdateInfantCount(infantT)
                viewModel.onUpdateClass(classType)
                viewModel.onUpdateTotalTravelerCount(count = (adultT + child + infantT))
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ShowBooking() {
    NextTripTheme {
        BookingScreenSkeleton(rememberNavController(), PaddingValues(0.dp))
    }
}

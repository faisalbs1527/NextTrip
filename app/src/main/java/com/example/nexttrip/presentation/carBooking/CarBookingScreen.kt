package com.example.nexttrip.presentation.carBooking

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddLocationAlt
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.LocationSearching
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SheetValue.Expanded
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nexttrip.components.ButtonCustom
import com.example.nexttrip.components.HorizontalLine
import com.example.nexttrip.components.OsmdroidMapView
import com.example.nexttrip.domain.model.carBooking.LocationDhakaItem
import com.example.nexttrip.navigation.Screen
import com.example.nexttrip.ui.theme.Font_SFPro
import com.example.nexttrip.ui.theme.gray
import com.example.nexttrip.ui.theme.green80
import com.example.nexttrip.ui.theme.red40
import com.example.nexttrip.ui.theme.red80
import com.example.nexttrip.utils.MapUtils
import com.example.nexttrip.utils.RequestLocationPermission
import kotlinx.coroutines.launch

@Composable
fun CarBookingScreen(
    navController: NavController = rememberNavController(),
    viewModel: CarBookingViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val locations by viewModel.locationsToShow.collectAsState()
    val carLocations by viewModel.carLocations.collectAsState()
    val pickUp by viewModel.pickUp.collectAsState()
    val destination by viewModel.destination.collectAsState()
    val currLocation by viewModel.currLocation.collectAsState()

    var pickUpText by remember { mutableStateOf("") }
    var destinationText by remember { mutableStateOf("") }
    var curLat by remember { mutableDoubleStateOf(0.0) }
    var curLong by remember { mutableDoubleStateOf(0.0) }
    var curAddress by remember { mutableStateOf("") }
    var loadCurrLocation by remember { mutableStateOf(true) }


    LaunchedEffect(key1 = curLat) {
        if (curLat != 0.0) {
            curAddress = MapUtils.getLocationDetails(curLat, curLong)
            viewModel.updateCurrLocation(curLat, curLong, curAddress)
        }
    }
    LaunchedEffect(key1 = Unit) {
        viewModel.getLocationsInDhaka()
        viewModel.getCurrCarLocations()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(.4f)
            ) {
                OsmdroidMapView(
                    context = context,
                    carLocations = carLocations,
                    onLocationUpdate = { _, _ -> },
                    onBackPress = {
                        viewModel.clearState()
                        navController.popBackStack()
                    })
            }
            BottomSection(
                modifier = Modifier.weight(.6f),
                fromLoc = pickUpText,
                toLoc = destinationText,
                locationToShow = locations,
                currAddress = currLocation.name,
                pickUp = pickUp.name,
                destination = destination.name,
                onLocationLoad = {
                    loadCurrLocation = true
                },
                onPickUpChange = {
                    pickUpText = it
                    viewModel.updateSuggestions(it)
                },
                onDestinationChange = {
                    destinationText = it
                    viewModel.updateSuggestions(it)
                },
                onPickUpSelect = {
                    viewModel.updatePickUp(it)
                    pickUpText = ""
                    viewModel.updateSuggestions("")
                },
                onDestinationSelect = {
                    viewModel.updateDestination(it)
                    destinationText = ""
                    viewModel.updateSuggestions("")
                },
                onCurrentLocClick = {
                    viewModel.updatePickUp(LocationDhakaItem(curLat, curLong, curAddress))
                },
                onSelectMap = {
                    navController.navigate(Screen.SelectLocationScreen.route)
                }
            )
        }
    }
    if (loadCurrLocation) {
        RequestLocationPermission(
            onPermissionGranted = {
                MapUtils.getLastUserLocation(
                    onGetLastLocationSuccess = {
                        curLat = it.first
                        curLong = it.second
                    },
                    onGetLastLocationFailed = {
                    },
                    onGetLastLocationIsNull = {
                        MapUtils.getCurrentLocation(
                            onGetCurrentLocationSuccess = {
                                curLat = it.first
                                curLong = it.second
                            },
                            onGetCurrentLocationFailed = {},
                            context = context
                        )
                    },
                    context = context
                )
            },
            onPermissionDenied = {

            }) {

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ShowScreen() {
    BottomSection(
        modifier = Modifier.padding(16.dp),
        fromLoc = "",
        toLoc = "",
        locationToShow = emptyList(),
        currAddress = "",
        onPickUpChange = {},
        onDestinationChange = {},
        onLocationLoad = {},
        onPickUpSelect = {},
        onDestinationSelect = {},
        onCurrentLocClick = {},
        onSelectMap = {}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSection(
    modifier: Modifier = Modifier,
    fromLoc: String,
    toLoc: String,
    pickUp: String = "",
    destination: String = "",
    locationToShow: List<LocationDhakaItem>,
    currAddress: String,
    onLocationLoad: () -> Unit,
    onPickUpChange: (String) -> Unit,
    onDestinationChange: (String) -> Unit,
    onPickUpSelect: (LocationDhakaItem) -> Unit,
    onDestinationSelect: (LocationDhakaItem) -> Unit,
    onCurrentLocClick: () -> Unit,
    onSelectMap: () -> Unit
) {

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        onDismissRequest = {
            scope.launch { sheetState.show() }
        },
        sheetState = sheetState
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Select Your Route",
                fontSize = 20.sp,
                fontFamily = Font_SFPro,
                fontWeight = FontWeight(700),
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White, shape = RoundedCornerShape(4.dp))
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            LocationBox(
                value = fromLoc,
                placeholder = pickUp.ifEmpty { "Pick Up" },
                iconColor = green80,
                onValueChange = {
                    onPickUpChange(it)
                },
                locations = locationToShow,
                onSelect = {
                    onPickUpSelect(it)
                }
            )
            LocationBox(
                value = toLoc,
                placeholder = destination.ifEmpty { "Destination" },
                iconColor = red40,
                onValueChange = {
                    onDestinationChange(it)
                },
                locations = locationToShow,
                onSelect = {
                    onDestinationSelect(it)
                }
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .border(
                    width = 1.dp,
                    color = Color.Black.copy(.4f),
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(8.dp)
                .clickable {
                    onCurrentLocClick()
                },
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.MyLocation, contentDescription = "",
                tint = red80,
                modifier = Modifier.size(24.dp)
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Current Location",
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontFamily = Font_SFPro
                )
                Text(
                    text = currAddress,
                    fontSize = 14.sp,
                    color = Color.Black.copy(.5f),
                    fontFamily = Font_SFPro
                )
            }
        }
        ButtonCustom(
            modifier = Modifier.padding(horizontal = 40.dp, vertical = 8.dp),
            text = "Find a driver"
        ) {

        }
    }
}

@Composable
fun LocationBox(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    iconColor: Color,
    locations: List<LocationDhakaItem>,
    onSelect: (LocationDhakaItem) -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Column {
        OutlinedTextField(
            value = value,
            onValueChange = {
                onValueChange(it)
                isExpanded = true
            },
            placeholder = {
                Text(
                    text = placeholder,
                    fontSize = 16.sp,
                    color = Color.Black.copy(.6f),
                    fontFamily = Font_SFPro
                )
            },
            leadingIcon = {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .border(width = 4.dp, color = iconColor, shape = CircleShape)
                        .background(color = Color.White, shape = CircleShape)
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = red80
            ),
            singleLine = true,
            textStyle = TextStyle(
                fontSize = 16.sp,
                color = Color.Black,
                fontFamily = Font_SFPro
            ),
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .clickable {
                    focusRequester.requestFocus()
                    isExpanded = true
                }
        )
        if (value.isNotEmpty() && isExpanded) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 1.dp)
                    .shadow(
                        elevation = 4.dp,
                        shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
                    )
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
                    )
                    .padding(vertical = 8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    IconTextButton(icon = Icons.Default.AddLocationAlt, text = "Set on map") {

                    }
                    Spacer(modifier = Modifier.size(12.dp))
                    IconTextButton(icon = Icons.Default.MyLocation, text = "Current Location") {

                    }
                }
                if (locations.isNotEmpty()) {
                    HorizontalLine()
                }
                locations.forEach { location ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "",
                            tint = Color.Black.copy(.6f)
                        )
                        Text(
                            text = location.name,
                            modifier = Modifier
                                .clickable {
                                    onSelect(location)
                                    focusManager.clearFocus()
                                    isExpanded = false
                                }
                                .padding(8.dp)
                                .fillMaxWidth(),
                            fontSize = 16.sp,
                            fontFamily = Font_SFPro,
                            color = Color.Black.copy(.6f)
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun IconTextButton(
    icon: ImageVector,
    text: String,
    onCLick: () -> Unit
) {
    Row(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = Color.Black.copy(.4f),
                shape = RoundedCornerShape(4.dp)
            )
            .padding(8.dp)
            .clickable {
                onCLick()
            },
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon, contentDescription = "",
            modifier = Modifier.size(24.dp),
            tint = red80
        )
        Text(
            text = text,
            fontSize = 16.sp,
            fontFamily = Font_SFPro,
            color = Color.Black
        )
    }
}
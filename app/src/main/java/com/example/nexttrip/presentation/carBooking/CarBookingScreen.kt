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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddLocationAlt
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetDefaults
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SheetValue
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
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.SecureFlagPolicy
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nexttrip.components.ButtonCustom
import com.example.nexttrip.components.HorizontalLine
import com.example.nexttrip.components.OsmdroidMapView
import com.example.nexttrip.domain.model.carBooking.LocationDetails
import com.example.nexttrip.navigation.Screen
import com.example.nexttrip.ui.theme.Font_SFPro
import com.example.nexttrip.ui.theme.green80
import com.example.nexttrip.ui.theme.red40
import com.example.nexttrip.ui.theme.red80
import com.example.nexttrip.utils.MapUtils
import com.example.nexttrip.utils.RequestLocationPermission

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
    val loadCurrLocation by remember { mutableStateOf(true) }


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
                pickUp = pickUp.name,
                destination = destination.name,
                onPickUpChange = {
                    pickUpText = it
                    viewModel.updateSuggestions(it)
                },
                onDestinationChange = {
                    destinationText = it
                    viewModel.updateSuggestions(it)
                },
                onLocationSelect = { id, location ->
                    viewModel.updateCurrLocationId(id)
                    viewModel.updateLocation(location)
                    if (id == 1) {
                        pickUpText = ""
                    } else {
                        destinationText = ""
                    }
                    viewModel.updateSuggestions("")
                },
                onCurrentLocClick = {
                    pickUpText = ""
                    viewModel.updateSuggestions("")
                    viewModel.updateCurrLocationId(1)
                    viewModel.updateLocation(currLocation)
                },
                onSelectMap = {
                    viewModel.updateCurrLocationId(it)
                    navController.navigate(Screen.SelectLocationScreen.route)
                },
                onChangeFocus = {
                    if (it == 1) pickUpText = "" else destinationText = ""
                },
                onFindDriver = {
                    navController.navigate(Screen.AvailableCarScreen.route)
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
        onPickUpChange = {},
        onDestinationChange = {},
        onLocationSelect = { id, location -> },
        onCurrentLocClick = {},
        onSelectMap = {},
        onChangeFocus = {},
        onFindDriver = {}
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
    locationToShow: List<LocationDetails>,
    onPickUpChange: (String) -> Unit,
    onDestinationChange: (String) -> Unit,
    onLocationSelect: (Int, LocationDetails) -> Unit,
    onCurrentLocClick: () -> Unit,
    onSelectMap: (Int) -> Unit,
    onChangeFocus: (Int) -> Unit,
    onFindDriver: () -> Unit
) {

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
        confirmValueChange = {
            it != SheetValue.Hidden // Prevent dismissing the sheet by swipe or tap outside
        }
    )
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        onDismissRequest = {
//            scope.launch { sheetState.show() }
        },
        sheetState = sheetState,
        properties = ModalBottomSheetProperties(
            securePolicy = SecureFlagPolicy.Inherit,
            isFocusable = true,
            shouldDismissOnBackPress = false
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Select Your Route",
                fontSize = 24.sp,
                fontFamily = Font_SFPro,
                fontWeight = FontWeight(700),
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            LocationBox(
                value = fromLoc,
                placeholder = pickUp.ifEmpty { "Pick Up" },
                iconColor = green80,
                locationId = 1,
                onValueChange = {
                    onPickUpChange(it)
                },
                locations = locationToShow,
                onSelect = { id, location ->
                    onLocationSelect(id, location)
                },
                onSelectMap = onSelectMap,
                onCurrentLocClick = onCurrentLocClick,
                onChangeFocus = onChangeFocus
            )
            LocationBox(
                value = toLoc,
                placeholder = destination.ifEmpty { "Destination" },
                iconColor = red40,
                locationId = 2,
                onValueChange = {
                    onDestinationChange(it)
                },
                locations = locationToShow,
                onSelect = { id, location ->
                    onLocationSelect(id, location)
                },
                onSelectMap = onSelectMap,
                onCurrentLocClick = {},
                onChangeFocus = onChangeFocus
            )
        }
        ButtonCustom(
            modifier = Modifier.padding(horizontal = 48.dp, vertical = 20.dp),
            text = "Find a driver"
        ) {
            onFindDriver()
        }
    }
}

@Composable
fun LocationBox(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    locationId: Int,
    iconColor: Color,
    locations: List<LocationDetails>,
    onSelect: (Int, LocationDetails) -> Unit,
    onSelectMap: (Int) -> Unit,
    onCurrentLocClick: () -> Unit,
    onChangeFocus: (Int) -> Unit
) {
    var isFocused by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Column {
        OutlinedTextField(
            value = value,
            onValueChange = {
                onValueChange(it)
            },
            placeholder = {
                Text(
                    text = placeholder,
                    fontSize = 16.sp,
                    color = Color.Black.copy(.7f),
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
                focusedBorderColor = red80,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            singleLine = true,
            textStyle = TextStyle(
                fontSize = 16.sp,
                color = Color.Black.copy(.7f),
                fontFamily = Font_SFPro
            ),
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    focusRequester.requestFocus()
                }
                .focusRequester(focusRequester)
                .onFocusChanged {
                    isFocused = it.isFocused
                    if (!isFocused) {
                        onChangeFocus(locationId)
                    }
                }
        )
        if (isFocused) {
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
                        onSelectMap(locationId)
                        focusManager.clearFocus()
                    }
                    if (locationId == 1) {
                        Spacer(modifier = Modifier.size(12.dp))
                        IconTextButton(icon = Icons.Default.MyLocation, text = "Current Location") {
                            onCurrentLocClick()
                            focusManager.clearFocus()
                        }
                    }
                }
                if (value.isNotEmpty()) {
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
                                        onSelect(locationId, location)
                                        focusManager.clearFocus()
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
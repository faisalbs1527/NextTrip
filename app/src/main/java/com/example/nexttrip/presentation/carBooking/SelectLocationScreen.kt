package com.example.nexttrip.presentation.carBooking

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nexttrip.components.ButtonCustom
import com.example.nexttrip.components.OsmdroidMapView
import com.example.nexttrip.domain.model.carBooking.LocationDetails
import com.example.nexttrip.ui.theme.Font_SFPro
import com.example.nexttrip.ui.theme.red80

@Composable
fun SelectLocationScreen(
    navController: NavController = rememberNavController(),
    viewModel: CarBookingViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val carLocations by viewModel.carLocations.collectAsState()
    val currLocation by viewModel.currLocation.collectAsState()

    var selectedLocation by remember {
        mutableStateOf(currLocation)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(.8f)
            ) {
                OsmdroidMapView(
                    context = context,
                    showBackButton = true,
                    carLocations = carLocations,
                    onLocationUpdate = { geoPoint, geoLocation ->
                        selectedLocation =
                            LocationDetails(geoPoint.latitude, geoPoint.longitude, geoLocation)
                    },
                    onBackPress = {
                        navController.popBackStack()
                    })
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                    )
                    .padding(vertical = 20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .border(
                            width = 1.dp,
                            color = Color.Black.copy(.4f),
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn, contentDescription = "",
                        modifier = Modifier.size(24.dp),
                        tint = red80
                    )
                    Text(
                        text = selectedLocation.name,
                        fontSize = 16.sp,
                        fontFamily = Font_SFPro,
                        color = Color.Black
                    )
                }
                ButtonCustom(
                    text = "Confirm Destination",
                    modifier = Modifier.padding(horizontal = 20.dp)
                ) {
                    viewModel.updateLocation(selectedLocation)
                    navController.popBackStack()
                }
            }
        }
    }
}
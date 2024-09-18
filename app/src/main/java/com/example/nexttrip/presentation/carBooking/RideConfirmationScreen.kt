package com.example.nexttrip.presentation.carBooking

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nexttrip.components.ButtonCustom
import com.example.nexttrip.components.ConfirmationStatus
import com.example.nexttrip.components.OsmdroidMapView
import com.example.nexttrip.components.appBar.SimpleTopBar
import com.example.nexttrip.navigation.Screen
import com.example.nexttrip.presentation.model.AvailableCarData
import org.osmdroid.util.GeoPoint

@Composable
fun RideConfirmationScreen(
    navController: NavController = rememberNavController(),
    viewModel: CarBookingViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val selectedCar by viewModel.selectedCar.collectAsState()
    val pickUpPosition by viewModel.pickUp.collectAsState()

    var pageStatus by remember { mutableIntStateOf(1) }

    Scaffold(
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            if (pageStatus == 1) {
                ButtonCustom(
                    text = "Next",
                    modifier = Modifier.padding(
                        start = 20.dp,
                        end = 20.dp
                    )
                ) {
                    if (pageStatus == 1) {
                        pageStatus = 2
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (pageStatus) {
                1 -> ConfirmationPage(
                    riderName = selectedCar.riderName
                ) {
                    navController.navigate(Screen.HomeScreen.route) {
                        popUpTo(Screen.HomeScreen.route) { inclusive = true }
                    }
                }

                2 -> RiderPositionPage(
                    context = context,
                    pickUpPoint = GeoPoint(pickUpPosition.latitude, pickUpPosition.longitude),
                    selectedCar = selectedCar
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun Show() {
    RideConfirmationScreen()
}

@Composable
fun ConfirmationPage(
    riderName: String,
    onBackPress: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(color = Color.Gray.copy(alpha = 0.2f))
            .fillMaxSize()
            .padding(vertical = 30.dp, horizontal = 20.dp)
    ) {
        SimpleTopBar(pageTitle = "Confirmation") {
            onBackPress()
        }
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 40.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ConfirmationStatus(
                title = "Thank you!",
                message = "Your booking has been placed and sent to $riderName"
            )
        }
    }
}

@Composable
fun RiderPositionPage(context: Context, pickUpPoint: GeoPoint, selectedCar: AvailableCarData) {
    OsmdroidMapView(
        context = context,
        showRoute = true,
        defaultScroll = .4,
        currGeoPoint = pickUpPoint,
        carLocations = listOf(selectedCar)
    )
}
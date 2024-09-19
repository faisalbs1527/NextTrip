package com.example.nexttrip.presentation.carBooking

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Sms
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.nexttrip.R
import com.example.nexttrip.components.ButtonCustom
import com.example.nexttrip.components.ConfirmationStatus
import com.example.nexttrip.components.HorizontalLine
import com.example.nexttrip.components.IconWithText
import com.example.nexttrip.components.OsmdroidMapView
import com.example.nexttrip.components.appBar.SimpleTopBar
import com.example.nexttrip.components.button.IconRoundButton
import com.example.nexttrip.components.button.TextButton
import com.example.nexttrip.navigation.Screen
import com.example.nexttrip.presentation.model.AvailableCarData
import com.example.nexttrip.ui.theme.Font_SFPro
import com.example.nexttrip.ui.theme.red40
import com.example.nexttrip.utils.formatTime
import kotlinx.coroutines.delay
import org.osmdroid.util.GeoPoint

@Composable
fun RideConfirmationScreen(
    navController: NavController = rememberNavController(),
    viewModel: CarBookingViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val selectedCar by viewModel.selectedCar.collectAsState()
    val pickUpPosition by viewModel.pickUp.collectAsState()
    val routePoints by viewModel.routePoints.collectAsState()

    var pageStatus by remember { mutableIntStateOf(1) }
    var remainingTime by remember { mutableIntStateOf(selectedCar.routeInfo.duration * 60) }

    LaunchedEffect(Unit) {
        while (remainingTime > 0) {
            delay(1000) // Wait for 1 second
            remainingTime -= 1
            if (remainingTime == 0) {
                remainingTime = 60
            }
        }
    }
    LaunchedEffect(Unit) {
        viewModel.getRoutePoints(
            GeoPoint(pickUpPosition.latitude, pickUpPosition.longitude),
            GeoPoint(selectedCar.latitude, selectedCar.longitude)
        )
    }

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
                    selectedCar = selectedCar,
                    remainingTime = remainingTime,
                    routePoints = routePoints,
                    onCancel = {
                        navController.navigate(Screen.CarBookingScreen.route) {
                            popUpTo(Screen.CarBookingScreen.route) { inclusive = true }
                        }
                    },
                    onBackHome = {
                        viewModel.updateBookingPageState(1)
                        navController.navigate(Screen.HomeScreen.route) {
                            popUpTo(Screen.HomeScreen.route) { inclusive = true }
                        }
                    }
                )
            }
        }
    }

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RiderPositionPage(
    context: Context,
    pickUpPoint: GeoPoint,
    selectedCar: AvailableCarData,
    routePoints: List<GeoPoint>?,
    remainingTime: Int,
    onCancel: () -> Unit,
    onBackHome: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
        confirmValueChange = {
            it != SheetValue.Hidden // Prevent dismissing the sheet by swipe or tap outside
        }
    )
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        OsmdroidMapView(
            context = context,
            showRoute = true,
            defaultScroll = .4,
            zoomLevel = 15.0,
            routePoints = routePoints,
            currGeoPoint = pickUpPoint,
            carLocations = listOf(selectedCar)
        )
        ModalBottomSheet(
            onDismissRequest = {},
            sheetState = sheetState,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Your driver is coming in " + formatTime(remainingTime),
                    fontSize = 18.sp,
                    fontFamily = Font_SFPro,
                    fontWeight = FontWeight(500),
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                TextButton(paddingVertical = 8, buttonText = "Cancel Ride") {
                    onCancel()
                }
            }
            HorizontalLine()
            RiderInfoCard(selectedCar)
            HorizontalLine()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    IconRoundButton(
                        imageVector = Icons.Default.Phone,
                        borderWidth = 1.dp,
                        borderColor = red40,
                        iconColor = red40,
                        size = 24.dp
                    )
                    IconRoundButton(
                        imageVector = Icons.Default.Sms,
                        borderWidth = 1.dp,
                        borderColor = red40,
                        iconColor = red40,
                        size = 24.dp
                    )
                }
                ButtonCustom(modifier = Modifier.width(180.dp), text = "Back Home") {
                    onBackHome()
                }
            }
        }
    }
}

@Composable
fun RiderInfoCard(
    carData: AvailableCarData
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(.7f),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .width(70.dp)
                    .height(80.dp)
                    .background(color = Color.Cyan.copy(.1f), shape = RoundedCornerShape(4.dp))
                    .padding(8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.driver),
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                modifier = Modifier.height(80.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = carData.riderName,
                    fontSize = 18.sp,
                    fontFamily = Font_SFPro,
                    fontWeight = FontWeight(700),
                    modifier = Modifier.padding(start = 4.dp)
                )
                IconWithText(
                    imageVector = Icons.Default.LocationOn,
                    text = "${carData.routeInfo.distance}km(${carData.routeInfo.duration}mins away)"
                )
                IconWithText(
                    imageVector = Icons.Default.Star,
                    text = "${carData.rating}(${carData.reviews} Reviews)"
                )
            }
        }
        AsyncImage(
            model = carData.image,
            contentDescription = "",
            modifier = Modifier
                .weight(.3f)
                .padding(start = 12.dp)
                .height(80.dp)
        )
    }
}
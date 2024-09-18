package com.example.nexttrip.presentation.carBooking

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.nexttrip.R
import com.example.nexttrip.components.ButtonCustom
import com.example.nexttrip.components.HorizontalLine
import com.example.nexttrip.components.InfoRow
import com.example.nexttrip.components.appBar.SimpleTopBar
import com.example.nexttrip.presentation.model.AvailableCarData
import com.example.nexttrip.ui.theme.Font_SFPro
import com.example.nexttrip.ui.theme.gray
import com.example.nexttrip.ui.theme.red80

@Composable
fun CarDetailsScreen(
    navController: NavController = rememberNavController(),
    viewModel: CarBookingViewModel = hiltViewModel()
) {

    val selectedCar by viewModel.selectedCar.collectAsState()

    Scaffold(
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            ButtonCustom(
                text = "Ride Now",
                modifier = Modifier.padding(
                    horizontal = 20.dp
                )
            ) {

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
                    .padding(horizontal = 20.dp, vertical = 30.dp)
            ) {
                SimpleTopBar(pageTitle = "Car Details") {
                    viewModel.updateCarSelection(AvailableCarData())
                    navController.popBackStack()
                }
                HorizontalLine()
                Text(
                    text = selectedCar.carName,
                    fontSize = 24.sp,
                    fontFamily = Font_SFPro,
                    fontWeight = FontWeight(700),
                    modifier = Modifier.padding(vertical = 4.dp)
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Star, contentDescription = "",
                        modifier = Modifier.size(20.dp),
                        tint = red80
                    )
                    Text(
                        text = "${selectedCar.rating}(${selectedCar.reviews} Reviews)",
                        fontSize = 14.sp,
                        fontFamily = Font_SFPro,
                        color = gray
                    )
                }
                AsyncImage(
                    model = selectedCar.image,
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = "Car features",
                    fontSize = 22.sp,
                    fontFamily = Font_SFPro,
                    fontWeight = FontWeight(500),
                )
                HorizontalLine()
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.White, shape = RoundedCornerShape(4.dp))
                        .padding(vertical = 16.dp, horizontal = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    InfoRow(title = "Rider Name", text = selectedCar.riderName)
                    InfoRow(title = "Model", text = selectedCar.model)
                    InfoRow(title = "Color", text = selectedCar.color)
                    InfoRow(title = "Fuel Type", text = selectedCar.fuelType)
                    InfoRow(title = "Gear Type", text = selectedCar.gearType)
                    InfoRow(
                        title = "Successful Rides",
                        text = selectedCar.successfulRides.toString()
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Show() {
    CarDetailsScreen()
}
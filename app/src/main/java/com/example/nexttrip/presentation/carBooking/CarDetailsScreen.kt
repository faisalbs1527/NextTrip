package com.example.nexttrip.presentation.carBooking

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nexttrip.components.appBar.SimpleTopBar
import com.example.nexttrip.ui.theme.Font_SFPro

@Composable
fun CarDetailsScreen(
    navController: NavController = rememberNavController(),
//    viewModel: CarBookingViewModel = hiltViewModel()
) {


    Column(
        modifier = Modifier
            .background(color = Color.Gray.copy(0.2f))
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 30.dp)
    ) {
        SimpleTopBar(pageTitle = "Car Details") {
            navController.popBackStack()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Show() {
    CarDetailsScreen()
}
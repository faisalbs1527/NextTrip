package com.example.nexttrip.presentation.hotelBooking

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nexttrip.components.ButtonCustom
import com.example.nexttrip.components.ConfirmationStatus
import com.example.nexttrip.navigation.Screen
import com.example.nexttrip.ui.theme.Font_SFPro

@Composable
fun BookingConfirmationScreen(
    navController: NavController = rememberNavController(),
    viewModel: ReservationViewModel = hiltViewModel()
) {

    Scaffold(
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            ButtonCustom(
                text = "Go Home",
                modifier = Modifier.padding(
                    start = 20.dp,
                    end = 20.dp
                )
            ) {
                navController.navigate(Screen.HomeScreen.route) {
                    popUpTo(Screen.HomeScreen.route) { inclusive = true }
                }
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
                    .background(color = Color.Gray.copy(alpha = 0.2f))
                    .fillMaxSize()
                    .padding(vertical = 30.dp, horizontal = 20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
                        contentDescription = "",
                        modifier = Modifier
                            .weight(.1f)
                            .size(30.dp)
                            .clickable {
                                navController.navigate(Screen.HomeScreen.route) {
                                    popUpTo(Screen.HomeScreen.route) { inclusive = true }
                                }
                            }
                    )
                    Text(
                        text = "Confirmation",
                        fontSize = 28.sp,
                        fontFamily = Font_SFPro,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF8A1C40),
                        modifier = Modifier.weight(.8f),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.weight(.1f))
                }
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 40.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ConfirmationStatus(
                        title = "Congratulations!!",
                        message = "Your hotel stay is secured.\nCounting down to your dream vacation"
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Show() {
    BookingConfirmationScreen()

}
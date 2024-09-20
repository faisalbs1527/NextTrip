package com.example.nexttrip.presentation.flightBooking.confirmation

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nexttrip.components.ButtonCustom
import com.example.nexttrip.components.ConfirmationStatus
import com.example.nexttrip.components.ViewTicket
import com.example.nexttrip.navigation.Screen
import com.example.nexttrip.presentation.flightBooking.SharedViewModel
import com.example.nexttrip.ui.theme.Font_SFPro
import com.example.nexttrip.ui.theme.red40
import com.example.nexttrip.utils.createPdfFromBitmap

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ConfirmationScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    val departureFlight by sharedViewModel.departureFlight.collectAsState()
    val returnFlight by sharedViewModel.returnFlight.collectAsState()

    val ticketBitmap by sharedViewModel.ticketBitmap.collectAsState()
    val fileName by sharedViewModel.ticketName.collectAsState()

    var titleText by remember {
        mutableStateOf("Payment Confirmation")
    }
    var pageStatus by remember {
        mutableIntStateOf(1)
    }
    val totalPrice = departureFlight.price + returnFlight.price

    Scaffold(
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            if (pageStatus == 1) {
                ButtonCustom(
                    text = "View Ticket",
                    modifier = Modifier.padding(
                        start = 20.dp,
                        end = 20.dp
                    )
                ) {
                    if (pageStatus == 1) {
                        pageStatus = 2
                        titleText = "Ticket"
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
            if (pageStatus == 1) {
                Column(
                    modifier = Modifier
                        .background(color = Color.Gray.copy(0.2f))
                        .fillMaxSize()
                        .padding(vertical = 30.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
                            contentDescription = "",
                            modifier = Modifier
                                .weight(.1f)
                                .size(36.dp)
                                .clickable {
                                    navController.navigate(Screen.HomeScreen.route) {
                                        popUpTo(Screen.HomeScreen.route) { inclusive = true }
                                    }
                                }
                        )
                        Row(
                            modifier = Modifier
                                .weight(.8f),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = titleText,
                                fontFamily = Font_SFPro,
                                fontSize = 20.sp,
                                color = red40,
                                fontWeight = FontWeight(600)
                            )
                        }
                        Spacer(modifier = Modifier.weight(.1f))
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 20.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        ConfirmationStatus(
                            title = "Wohoo! Your flight has been booked",
                            message = "Payment of $$totalPrice has been received."
                        )
                    }
                }
            } else {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    ViewTicket(
                        ticket = ticketBitmap?.asImageBitmap(),
                        ticketName = fileName,
                        onBackPress = {
                            navController.navigate(Screen.HomeScreen.route) {
                                popUpTo(Screen.HomeScreen.route) { inclusive = true }
                            }
                        }) {
                        ticketBitmap?.let { createPdfFromBitmap(context, it, fileName) }
                        navController.navigate(Screen.HomeScreen.route) {
                            popUpTo(Screen.HomeScreen.route) { inclusive = true }
                        }
                        Toast.makeText(context, "Ticket saved!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
private fun Show() {
    ConfirmationScreen(rememberNavController())
}
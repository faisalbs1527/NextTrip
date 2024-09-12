package com.example.nexttrip.presentation.busTicketBooking

import android.graphics.Bitmap
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
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
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nexttrip.components.BusTicket
import com.example.nexttrip.components.ButtonCustom
import com.example.nexttrip.components.ConfirmationMessage
import com.example.nexttrip.components.PaymentSection
import com.example.nexttrip.components.ViewTicket
import com.example.nexttrip.navigation.Screen
import com.example.nexttrip.ui.theme.Font_SFPro
import com.example.nexttrip.utils.createBitmapFromComposable
import com.example.nexttrip.utils.createPdfFromBitmap
import com.example.nexttrip.utils.createPdfFromComposable
import com.example.nexttrip.utils.ticketDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PaymentScreen(
    navController: NavController = rememberNavController(),
    viewModel: BusReservationViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    var pageStatus by remember {
        mutableIntStateOf(1)
    }
    var pageTitle by remember {
        mutableStateOf("Payment")
    }
    var buttonText by remember {
        mutableStateOf("Confirm")
    }
    var barcodeWidth by remember {
        mutableIntStateOf(200)
    }

    var ticketBitmap by remember {
        mutableStateOf<Bitmap?>(null)
    }

    val totalPayment by viewModel.totalPrice.collectAsState()
    val travelDate by viewModel.travelDate.collectAsState()
    val fromLoc by viewModel.fromLoc.collectAsState()
    val toLoc by viewModel.toLoc.collectAsState()
    val seats = viewModel.getSeats()
    val selectedBus = viewModel.getSelectedBus()
    val currentDateTime = ticketDate()
    val fileName = "busTicket_$currentDateTime.pdf"

    Scaffold(
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            if (pageStatus < 3) {
                ButtonCustom(
                    text = buttonText,
                    modifier = Modifier.padding(
                        start = 20.dp,
                        end = 20.dp
                    )
                ) {
                    when (pageStatus) {
                        1 -> {
                            pageStatus = 2
                            pageTitle = "Confirmation"
                            buttonText = "View Ticket"
                            ticketBitmap = createBitmapFromComposable(context) {
                                BusTicket(
                                    busData = selectedBus,
                                    fromLoc = fromLoc,
                                    toLoc = toLoc,
                                    travelDate = travelDate,
                                    seats = seats,
                                    totalFare = totalPayment.toString(),
                                    barcodeWidth = barcodeWidth
                                )
                            }
                        }

                        2 -> {
                            pageStatus = 3
                            pageTitle = "Ticket"
                            buttonText = "Download Ticket"
                        }
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
            if (pageStatus < 3) {
                Column(
                    modifier = Modifier
                        .background(color = Color.Gray.copy(alpha = 0.2f))
                        .fillMaxSize()
                        .padding(vertical = 30.dp, horizontal = 20.dp)
                ) {
                    TopBar(pageTitle = pageTitle) {
                        if (pageStatus == 1) {
                            navController.popBackStack()
                        } else {
                            navController.navigate(Screen.HomeScreen.route) {
                                popUpTo(Screen.HomeScreen.route) { inclusive = true }
                            }
                        }
                    }
                    when (pageStatus) {
                        1 -> Column {
                            Spacer(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .onGloballyPositioned {
                                        barcodeWidth = it.size.width
                                    }
                                    .padding(top = 12.dp)
                            )
                            PaymentSection(payment = totalPayment.toString())
                        }

                        2 -> ConfirmationMessage(message = "Your payment is successful.\n A nice journey is waiting for you")
                    }
                }
            } else {
                Box(modifier = Modifier.fillMaxSize()) {
                    ViewTicket(
                        ticket = ticketBitmap?.asImageBitmap(),
                        ticketName = fileName,
                        onBackPress = {
                            navController.navigate(Screen.HomeScreen.route) {
                                popUpTo(Screen.HomeScreen.route) { inclusive = true }
                            }
                        },
                        onDownloadClick = {
                            ticketBitmap?.let { createPdfFromBitmap(context, it, fileName) }
                            navController.navigate(Screen.HomeScreen.route) {
                                popUpTo(Screen.HomeScreen.route) { inclusive = true }
                            }
                            Toast.makeText(context, "Ticket saved!", Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    pageTitle: String,
    onBackPress: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
            contentDescription = "",
            modifier = Modifier
                .weight(.1f)
                .size(30.dp)
                .clickable {
                    onBackPress()
                }
        )
        Text(
            text = pageTitle,
            fontSize = 28.sp,
            fontFamily = Font_SFPro,
            fontWeight = FontWeight(500),
            color = Color(0xFF8A1C40),
            modifier = Modifier.weight(.8f),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.weight(.1f))
    }
}
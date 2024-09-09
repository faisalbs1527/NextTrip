package com.example.nexttrip.presentation.myBooking

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.nexttrip.components.ViewTicket
import com.example.nexttrip.utils.createPdfFromBitmap

@Composable
fun ViewPdfScreen(
    navController: NavController,
    viewModel: MyBookingViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val ticket by viewModel.currTicket.collectAsState()
    val ticketName by viewModel.currTicketName.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        ViewTicket(
            ticket = ticket,
            ticketName = ticketName,
            onBackPress = {
                navController.popBackStack()
            },
            onDownloadClick = {
                val bitmap = ticket?.asAndroidBitmap()
                bitmap?.let { ticketName?.let { filename -> createPdfFromBitmap(context, it, filename) } }

                Toast.makeText(context, "Ticket Downloaded!!", Toast.LENGTH_SHORT).show()
            }
        )
    }
}
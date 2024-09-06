package com.example.nexttrip.presentation.myBooking

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.filled.AddToDrive
import androidx.compose.material.icons.filled.SaveAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.nexttrip.components.ViewTicket
import com.example.nexttrip.ui.theme.Font_SFPro
import com.example.nexttrip.ui.theme.gray
import com.example.nexttrip.utils.createPdfFromBitmap

@Composable
fun ViewPdfScreen(
    navController: NavController,
    viewModel: MyBookingViewModel = hiltViewModel()
) {
    val ticket by viewModel.currTicket.collectAsState()
    val ticketName by viewModel.currTicketName.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        ViewTicket(
            ticket = ticket,
            ticketName = ticketName,
            onBackPress = {
                navController.popBackStack() },
            onDownloadClick = {

            }
        )
    }
}
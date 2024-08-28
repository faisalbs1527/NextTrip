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
import com.example.nexttrip.ui.theme.Font_SFPro
import com.example.nexttrip.ui.theme.gray

@Composable
fun ViewPdfScreen(
    navController: NavController,
    viewModel: MyBookingViewModel = hiltViewModel()
) {
    val ticket by viewModel.currTicket.collectAsState()
    val ticketName by viewModel.currTicketName.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 30.dp, bottom = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
                contentDescription = "",
                modifier = Modifier
                    .weight(.1f)
                    .size(28.dp)
                    .clickable {
                        navController.popBackStack()
                    }
            )
            Row(
                modifier = Modifier
                    .weight(.56f),
                horizontalArrangement = Arrangement.Center
            ) {
                ticketName?.let {
                    Text(
                        text = it,
                        fontFamily = Font_SFPro,
                        fontSize = 16.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight(400)
                    )
                }
            }
            Icon(
                imageVector = Icons.Filled.AddToDrive,
                contentDescription = "",
                modifier = Modifier
                    .weight(.12f)
                    .size(28.dp)
                    .clickable {

                    }
            )
            Icon(
                imageVector = Icons.Filled.SaveAlt,
                contentDescription = "",
                modifier = Modifier
                    .weight(.12f)
                    .size(28.dp)
                    .clickable {

                    }
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = gray.copy(.2f))
                .padding(horizontal = 20.dp, vertical = 12.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(
                        state = rememberScrollState()
                    )
            ) {
                ticket?.let {
                    Image(
                        bitmap = it,
                        contentDescription = "",
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}
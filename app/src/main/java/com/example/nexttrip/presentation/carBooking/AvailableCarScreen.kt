package com.example.nexttrip.presentation.carBooking

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nexttrip.components.AvailableCarCard
import com.example.nexttrip.ui.theme.Font_SFPro

@Composable
fun AvailableCarScreen(
    navController: NavController = rememberNavController(),
    viewModel: CarBookingViewModel = hiltViewModel()
) {

    val availableCars by viewModel.availableCars.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.findAvailableCars()
    }
    Column(
        modifier = Modifier
            .background(color = Color.Gray.copy(0.2f))
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 30.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp)
                .clickable {
                    navController.popBackStack()
                },
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
                contentDescription = "",
                modifier = Modifier
                    .size(26.dp)
                    .clickable {
                        navController.popBackStack()
                    }
            )
            Text(
                text = "Back",
                fontSize = 20.sp,
                fontFamily = Font_SFPro
            )
        }
        Text(
            text = "Available Cars for ride",
            fontFamily = Font_SFPro,
            fontSize = 20.sp,
            fontWeight = FontWeight(600),
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = "${availableCars.size} cars found",
            fontFamily = Font_SFPro,
            fontSize = 16.sp,
            color = Color.Black.copy(.5f),
            fontWeight = FontWeight(600)
        )
        LazyColumn(
            modifier = Modifier.padding(top = 12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(availableCars) {
                AvailableCarCard(it)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Show() {
    AvailableCarScreen()
}
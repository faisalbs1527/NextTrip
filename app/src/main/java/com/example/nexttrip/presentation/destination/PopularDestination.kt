package com.example.nexttrip.presentation.destination

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.nexttrip.components.PlanCard
import com.example.nexttrip.presentation.home.HomeViewModel
import com.example.nexttrip.ui.theme.NextTripTheme

@Composable
fun PopularDestinationScreen(navController: NavController) {
    PopularDestinationSkeleton(navController)
}

@Composable
fun PopularDestinationSkeleton(navController: NavController) {

    val viewModel: HomeViewModel = hiltViewModel()

    val destinationList by viewModel.destinationList.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getDestinations()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 30.dp, horizontal = 20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIos, contentDescription = "",
                    modifier = Modifier
                        .size(26.dp)
                        .clickable {
                            navController.popBackStack()
                        }
                )
                Text(
                    text = "Popular Destination",
                    fontSize = 20.sp,
                    color = Color(0xFF8A1C40)
                )
                Icon(
                    imageVector = Icons.Default.Menu, contentDescription = "",
                    modifier = Modifier.size(30.dp)
                )
            }
            LazyColumn(
                modifier = Modifier.padding(top = 20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(destinationList) { data ->
                    PlanCard(modifier = Modifier.fillMaxWidth(), item = data)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ShowPopularDestination() {
    NextTripTheme {
//        PopularDestinationSkeleton()
    }
}
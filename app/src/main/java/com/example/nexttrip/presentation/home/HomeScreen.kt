package com.example.nexttrip.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
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
import com.example.nexttrip.navigation.Screen
import com.example.nexttrip.presentation.components.ButtonCustom
import com.example.nexttrip.presentation.components.PlanCard
import com.example.nexttrip.presentation.components.ServiceItem
import com.example.nexttrip.presentation.itemsList
import com.example.nexttrip.ui.theme.NextTripTheme

@Composable
fun HomeScreen(navController: NavController) {
    HomeScreenSkeleton(navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenSkeleton(navController: NavController) {

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
                .verticalScroll(
                    state = rememberScrollState()
                )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Hello, Traveler",
                    fontSize = 24.sp,
                    color = Color(0xFF8A1C40)
                )
                Icon(
                    imageVector = Icons.Default.Menu, contentDescription = "",
                    modifier = Modifier.size(36.dp)
                )
            }
            Text(
                text = "Explore Over 100+ luxurious gateway worldwide",
                fontSize = 26.sp,
                fontWeight = FontWeight(500),
                modifier = Modifier.padding(top = 30.dp)
            )

            Text(
                text = "Destination",
                fontSize = 18.sp,
                color = Color.Gray,
                fontWeight = FontWeight(400),
                modifier = Modifier.padding(top = 20.dp)
            )
            SearchBar(
                query = "",
                onQueryChange = {},
                onSearch = {},
                active = false,
                onActiveChange = {},
                modifier = Modifier
                    .height(56.dp),
                shape = RoundedCornerShape(4.dp),
                placeholder = {
                    Text(
                        text = "Select your destination",
                        fontSize = 16.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight(400)
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.LocationOn,
                        contentDescription = "",
                        modifier = Modifier.size(24.dp),
                        tint = Color.Gray
                    )
                }) {

            }
            ButtonCustom(
                modifier = Modifier.padding(top = 20.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Popular Destination",
                    fontSize = 18.sp,
                    fontWeight = FontWeight(500)
                )
                Text(
                    text = "See all",
                    color = Color(0xFF8A1C40),
                    modifier = Modifier.clickable {
                        navController.navigate(Screen.PopularDestination.route)
                    }
                )
            }
            LazyRow(
                modifier = Modifier.padding(top = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(destinationList) { data ->
                    PlanCard(item = data)
                }
            }
            Text(
                modifier = Modifier.padding(vertical = 16.dp),
                text = "Services",
                fontSize = 18.sp,
                fontWeight = FontWeight(500)
            )
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                items(itemsList) { item ->
                    ServiceItem(title = item.title, image = item.image) {
                        navController.navigate(Screen.BookingScreen.route)
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun ShowHomeScreen() {
    NextTripTheme {
//        HomeScreenSkeleton()
    }
}
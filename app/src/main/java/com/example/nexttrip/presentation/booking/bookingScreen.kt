package com.example.nexttrip.presentation.booking

import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nexttrip.R
import com.example.nexttrip.presentation.components.TicketType
import com.example.nexttrip.ui.theme.NextTripTheme

@Composable
fun BookingScreen() {
    BookingScreenSkeleton()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingScreenSkeleton() {

    val selectedItem = remember {
        mutableIntStateOf(0)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .background(color = Color.Gray.copy(alpha = 0.2f))
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
                    text = "NextTrip",
                    fontSize = 24.sp,
                    color = Color(0xFF8A1C40)
                )
                Icon(
                    imageVector = Icons.Default.Menu, contentDescription = "",
                    modifier = Modifier.size(36.dp)
                )
            }
            Text(
                text = "Book Your Flights",
                fontSize = 26.sp,
                fontWeight = FontWeight(500),
                modifier = Modifier.padding(top = 30.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(16.dp)),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TicketType(text = "One Way", selected = selectedItem.intValue == 0) {
                    selectedItem.intValue = 0
                }
                TicketType(text = "Round Way", selected = selectedItem.intValue == 1) {
                    selectedItem.intValue = 1
                }
                TicketType(text = "Multi City", selected = selectedItem.intValue == 2) {
                    selectedItem.intValue = 2
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .background(color = Color.White)
            ) {
                Text(
                    text = "Departure City",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(400),
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
                    color = Color.Black.copy(alpha = .6f)
                )
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .clickable {

                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.departure),
                        contentDescription = "Departure",
                        tint = Color.Black.copy(alpha = .6f),
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .size(24.dp)
                    )
                    Column(
                        modifier = Modifier.padding(start = 4.dp),
                        verticalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        Text(
                            text = "Dhaka",
                            fontSize = 24.sp,
                            fontWeight = FontWeight(400)
                        )
                        Text(
                            text = "Hazzrat Shahjalal Int. Airport",
                            fontSize = 14.sp,
                            fontWeight = FontWeight(400),
                            color = Color.Black.copy(alpha = .6f)
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 16.dp)
                        .height(1.dp)
                        .background(color = Color.Black.copy(alpha = .5f))
                )
                Text(
                    text = "Arrival City",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(400),
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
                    color = Color.Black.copy(alpha = .6f)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                        .clickable {
                                   
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrival),
                        contentDescription = "Departure",
                        tint = Color.Black.copy(alpha = .6f),
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .size(24.dp)
                    )
                    Column(
                        modifier = Modifier.padding(start = 4.dp),
                        verticalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        Text(
                            text = "Cox's Bazar",
                            fontSize = 24.sp,
                            fontWeight = FontWeight(400)
                        )
                        Text(
                            text = "Cox's Bazar Airport",
                            fontSize = 14.sp,
                            fontWeight = FontWeight(400),
                            color = Color.Black.copy(alpha = .6f)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ShowBooking() {
    NextTripTheme {
        BookingScreenSkeleton()
    }
}

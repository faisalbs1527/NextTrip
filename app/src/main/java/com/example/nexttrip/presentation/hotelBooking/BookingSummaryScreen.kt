package com.example.nexttrip.presentation.hotelBooking

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.nexttrip.R
import com.example.nexttrip.components.ButtonCustom
import com.example.nexttrip.components.HorizontalLine
import com.example.nexttrip.components.InfoRow
import com.example.nexttrip.components.PaymentMethod
import com.example.nexttrip.components.PaymentSection
import com.example.nexttrip.components.formatNumber
import com.example.nexttrip.domain.model.hotelbooking.Hotel
import com.example.nexttrip.navigation.Screen
import com.example.nexttrip.presentation.model.AvailableHotelData
import com.example.nexttrip.ui.theme.Font_SFPro
import com.example.nexttrip.ui.theme.gray
import com.example.nexttrip.ui.theme.red80
import com.example.nexttrip.utils.currentDate

@Composable
fun BookingSummaryScreen(
    navController: NavController = rememberNavController(),
    viewModel: ReservationViewModel = hiltViewModel()
) {

    val hotel by viewModel.selectedHotel.collectAsState()
    val checkIn by viewModel.checkIn.collectAsState()
    val checkOut by viewModel.checkOut.collectAsState()
    val roomList by viewModel.roomList.collectAsState()

    val totalGuests = viewModel.getTotalGuests()
    val bookingDate = currentDate

    Scaffold(
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            ButtonCustom(
                text = "Confirm",
                modifier = Modifier.padding(
                    start = 20.dp,
                    end = 20.dp
                )
            ) {
                viewModel.saveBookingData()
                navController.navigate(Screen.HotelConfirmation.route)
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
                    .padding(top = 30.dp, bottom = 56.dp, start = 20.dp,end = 20.dp)
                    .verticalScroll(
                        state = rememberScrollState()
                    )
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
                                viewModel.clearSelectedRooms()
                                navController.popBackStack()
                            }
                    )
                    Text(
                        text = "Booking Summary",
                        fontSize = 28.sp,
                        fontFamily = Font_SFPro,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF8A1C40),
                        modifier = Modifier.weight(.8f),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.weight(.1f))
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(4.dp))
                        .padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    HotelInfoSection(hotel)
                    HorizontalLine()
                    BookingInfoSection(
                        bookingDate = bookingDate,
                        checkIn = checkIn,
                        checkOut = checkOut,
                        guests = totalGuests,
                        room = roomList.size.toString()
                    )
                }
                PaymentSection(
                    actualPayment = viewModel.getTotalActualPrice(),
                    payment = viewModel.getTotalDiscountPrice(),
                    isIncludeDiscount = true
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ShowBookingSummary() {
    BookingSummaryScreen()
}

@Composable
fun HotelInfoSection(
    hotel: AvailableHotelData
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = hotel.image,
            contentDescription = "",
            modifier = Modifier
                .weight(.4f)
                .height(100.dp)
                .clip(shape = RoundedCornerShape(4.dp)),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .weight(.6f)
                .padding(start = 4.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = hotel.name,
                fontFamily = Font_SFPro,
                fontSize = 18.sp,
                fontWeight = FontWeight(600)
            )
            Text(
                text = hotel.location,
                fontSize = 12.sp,
                color = Color.Black.copy(0.5f),
                fontFamily = Font_SFPro
            )
            Row(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = gray.copy(.4f),
                        shape = RoundedCornerShape(4.dp)
                    )
                    .padding(4.dp),
                horizontalArrangement = Arrangement.spacedBy(2.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "",
                    tint = red80,
                    modifier = Modifier.size(14.dp)
                )
                Text(
                    text = "${hotel.rating} Star",
                    fontSize = 12.sp,
                    color = Color.Black.copy(0.5f),
                    fontFamily = Font_SFPro
                )
            }
        }
    }
}


@Composable
fun BookingInfoSection(
    bookingDate: String,
    checkIn: String,
    checkOut: String,
    guests: String,
    room: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        InfoRow(title = "Booking Date", text = bookingDate)
        InfoRow(title = "Check In", text = checkIn)
        InfoRow(title = "Check Out", text = checkOut)
        InfoRow(title = "Guests", text = guests)
        InfoRow(title = "Room(s)", text = room)
    }
}